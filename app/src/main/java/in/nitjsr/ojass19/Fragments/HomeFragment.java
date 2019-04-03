package in.nitjsr.ojass19.Fragments;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import org.joda.time.DateTime;
import org.joda.time.Days;

import de.hdodenhof.circleimageview.CircleImageView;
import in.nitjsr.ojass19.Activity.HomeActivity;
import in.nitjsr.ojass19.Activity.RegisterActivity;
import in.nitjsr.ojass19.R;
import in.nitjsr.ojass19.Utils.BlurCallback;
import in.nitjsr.ojass19.Utils.Constants;
import in.nitjsr.ojass19.Utils.Utilities;
import jp.wasabeef.blurry.Blurry;

import static in.nitjsr.ojass19.Utils.Constants.FIREBASE_REF_NAME;
import static in.nitjsr.ojass19.Utils.Constants.FIREBASE_REF_PARTICIPATED_EVENTS;
import static in.nitjsr.ojass19.Utils.Constants.FIREBASE_REF_USERS;

public class HomeFragment extends Fragment implements BlurCallback {

    private FirebaseUser mUser;
    private TextView tvUserName, tvUserOjId, tvDaysToGo;
    private CircleImageView userImage;
    private ProgressDialog pd;
    private DatabaseReference userRef;
    private FirebaseAuth mAuth;
    private FloatingActionButton btnRegister;

    private DrawerLayout drawerLayout;
    private LinearLayout llUserInfo;
    private ImageView viewpuller;
    private FrameLayout fl;
    public static final String POSITION_PARAM = "position";
    private Handler handler;
    private Runnable runnable;
    private NavigationView mNavigationView;
    private CardView regCard;
    private ObjectAnimator alphaAnimator;
    private LinearLayout days_layout;
    private Button regBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home,container,false);
        tvDaysToGo =view.findViewById(R.id.tv_days_to_go);
        tvUserName=view.findViewById(R.id.user_name);
        tvUserOjId=view.findViewById(R.id.user_ojass_id);
        userImage=view.findViewById(R.id.user_image);
        btnRegister=view.findViewById(R.id.btn_register);
        llUserInfo=view.findViewById(R.id.ll_user_info);
        drawerLayout = view.findViewById(R.id.drawer_layout);
        viewpuller = view.findViewById(R.id.view_puller);
        fl= view.findViewById(R.id.gurugyan_fl);
        regCard = view.findViewById(R.id.register_card);
        regBtn = view.findViewById(R.id.reg_btn);
        days_layout = view.findViewById(R.id.home_progress_rl);
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.add(R.id.gurugyan_fl,new GuruGyanFragment()).commit();
        btnRegister.setVisibility(View.GONE);

        mAuth = FirebaseAuth.getInstance();
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        userRef = FirebaseDatabase.getInstance().getReference(FIREBASE_REF_USERS).child(mUser.getUid());
        userRef.keepSynced(true);
        Utilities.setPicassoImage(view.getContext(), mUser.getPhotoUrl().toString(), userImage, Constants.SQUA_PLACEHOLDER);
        pd = new ProgressDialog(getContext());
        pd.setTitle("Please Wait");
        pd.setMessage("Loading...");
        setAlphaAnimation(btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(regCard.getVisibility()==View.GONE) {
                    alphaAnimator.cancel();
                    regCard.setVisibility(View.VISIBLE);
                    Animation a1 = AnimationUtils.loadAnimation(getContext(), R.anim.scale_up);
                    regCard.startAnimation(a1);
                }else{
                    alphaAnimator.start();
                    regCard.setVisibility(View.GONE);
                    Animation a1 = AnimationUtils.loadAnimation(getContext(), R.anim.scale_down);
                    regCard.startAnimation(a1);
                }


            }
        });
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),RegisterActivity.class));
            }
        });

        viewpuller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.END);
            }
        });
        fetchData(0);

        countDownStart();
        manageDrawerLayout();

        mNavigationView = view.findViewById(R.id.nav_view);
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        DrawerLayout.LayoutParams params = (DrawerLayout.LayoutParams) mNavigationView.getLayoutParams();
        params.width = metrics.widthPixels;
        mNavigationView.setLayoutParams(params);

        return view;
    }
    void setAlphaAnimation(View view){
        alphaAnimator = ObjectAnimator.ofFloat(view,View.ALPHA, 0.7f, 1f);
        alphaAnimator.setDuration(400);
        alphaAnimator.setRepeatMode(ValueAnimator.REVERSE);
        alphaAnimator.setRepeatCount(ValueAnimator.INFINITE);
        alphaAnimator.start();
    }
    void manageDrawerLayout(){
        drawerLayout.openDrawer(Gravity.END);
        viewpuller.setVisibility(View.GONE);

        DrawerArrowDrawable arrowDrawable = new DrawerArrowDrawable(getContext());
        arrowDrawable.setDirection(DrawerArrowDrawable.ARROW_DIRECTION_END);
        HomeActivity activity = (HomeActivity)getActivity();
        activity.toolbar.setNavigationIcon(arrowDrawable);
        activity.getSupportActionBar().setHomeAsUpIndicator(arrowDrawable);
        activity.setSupportActionBar(activity.toolbar);
        activity.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                    drawerLayout.closeDrawer(Gravity.RIGHT);
                } else {
                    drawerLayout.openDrawer(Gravity.RIGHT);
                }
            }
        });
        ActionBarDrawerToggle mToggle = new ActionBarDrawerToggle(getActivity(),
                drawerLayout,
                R.string.drawer_open,
                R.string.drawer_close){
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                viewpuller.setTranslationX(drawerView.getX()-drawerView.getWidth());
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                viewpuller.setVisibility(View.VISIBLE);
                Blurry.delete(fl);
                Blurry.delete(fl);
                Log.e("TAG","Event");

            }
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                viewpuller.setVisibility(View.GONE);
                Blurry.with(getContext()).radius(20).sampling(2).onto(fl);


            }
        };
        mToggle.syncState();


        mToggle.setDrawerArrowDrawable(arrowDrawable);
        mToggle.setHomeAsUpIndicator(arrowDrawable);
        drawerLayout.addDrawerListener(mToggle);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void countDownStart() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 1000);
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Calendar calendar = Calendar.getInstance();
                    String currentDate = dateFormat.format(calendar.getTime());
                    int yr = 2019;
                    int mon = calendar.get(Calendar.MONTH)+1;
                    int day = Integer.parseInt(currentDate.substring(8,10));

                    DateTime start = new DateTime(yr,mon,day,0,0,0);
                    DateTime end = new DateTime(yr, 4, 5,0,0,0);
                    int days = Days.daysBetween(start, end).getDays();

                    tvDaysToGo.setText(days+" ");
                    if(days<=0){
                        days_layout.setVisibility(View.INVISIBLE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 1 * 1000);

    }


    private void fetchData(final int flag) {
        userRef.keepSynced(true);
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint({"NewApi", "RestrictedApi"})
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    try {
                        btnRegister.setVisibility(View.GONE);
                        String userName=dataSnapshot.child(FIREBASE_REF_NAME).getValue().toString();
                        tvUserName.setText(dataSnapshot.child(FIREBASE_REF_NAME).getValue().toString());
                        if (dataSnapshot.child(Constants.FIREBASE_REF_OJASS_ID).getValue() != null) {
                            tvUserOjId.setText(dataSnapshot.child(Constants.FIREBASE_REF_OJASS_ID).getValue().toString());
                            tvUserOjId.setTextColor(getResources().getColor(R.color.colorWhite));
                        } else {
                            tvUserOjId.setText(Constants.PAYMENT_DUE);
                            tvUserOjId.setTextColor(Color.RED);
                        }
                        if (dataSnapshot.child(FIREBASE_REF_PARTICIPATED_EVENTS).exists()){
                        }
                        if (pd.isShowing()) pd.dismiss();
                        if (flag == 1){
                            Toast.makeText(getContext(), "Profile updated!", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e){
                        if (pd.isShowing()) pd.dismiss();
                    }
                }
                else  // if not registered
                {
                    llUserInfo.setVisibility(View.GONE);
                    if (pd.isShowing()) pd.dismiss();
                    //tvUserOjId.setText(Constants.NOT_REGISTERED);
                    //tvUserOjId.setTextColor(Color.RED);
                    Toast.makeText(getContext(), Constants.NOT_REGISTERED, Toast.LENGTH_SHORT).show();
                    btnRegister.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBlurCallback() {


        if (drawerLayout.isDrawerOpen(Gravity.END)) {
            Blurry.with(getContext()).radius(20).sampling(2).onto(fl);
        } else {
            Blurry.delete(fl);
        }
    }
}