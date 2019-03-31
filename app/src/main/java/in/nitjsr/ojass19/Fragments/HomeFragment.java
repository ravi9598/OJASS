package in.nitjsr.ojass19.Fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.joda.time.DateTime;
import org.joda.time.Days;

import de.hdodenhof.circleimageview.CircleImageView;
import in.nitjsr.ojass19.Activity.RegisterActivity;
import in.nitjsr.ojass19.R;
import in.nitjsr.ojass19.Utils.Constants;
import in.nitjsr.ojass19.Utils.Utilities;

import static in.nitjsr.ojass19.Utils.Constants.FIREBASE_REF_NAME;
import static in.nitjsr.ojass19.Utils.Constants.FIREBASE_REF_OJASS_ID;
import static in.nitjsr.ojass19.Utils.Constants.FIREBASE_REF_PARTICIPATED_EVENTS;
import static in.nitjsr.ojass19.Utils.Constants.FIREBASE_REF_TSHIRT_SIZE;
import static in.nitjsr.ojass19.Utils.Constants.FIREBASE_REF_USERS;

public class HomeFragment extends Fragment {

    private FirebaseUser mUser;
    private TextView tvUserName, tvUserOjId, tvDaysToGo;
    private CircleImageView userImage;
    private ProgressDialog pd;
    private DatabaseReference userRef;
    private FirebaseAuth mAuth;
    private Button btnRegister;
    private Handler handler;
    private Runnable runnable;
    private ProgressBar progressBar;
    private RelativeLayout rl;
    private LinearLayout llUserInfo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home,container,false);

        tvUserName=view.findViewById(R.id.user_name);
        tvUserOjId=view.findViewById(R.id.user_ojass_id);
        userImage=view.findViewById(R.id.user_image);
        btnRegister=view.findViewById(R.id.btn_register);
        tvDaysToGo=view.findViewById(R.id.tv_days_to_go);
        progressBar=view.findViewById(R.id.home_progressBar);
        rl=view.findViewById(R.id.home_progress_rl);
        llUserInfo=view.findViewById(R.id.ll_user_info);

        mAuth = FirebaseAuth.getInstance();
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        userRef = FirebaseDatabase.getInstance().getReference(FIREBASE_REF_USERS).child(mUser.getUid());
        userRef.keepSynced(true);
        Utilities.setPicassoImage(view.getContext(), mUser.getPhotoUrl().toString(), userImage, Constants.SQUA_PLACEHOLDER);
        pd = new ProgressDialog(getContext());
        pd.setTitle("Please Wait");
        pd.setMessage("Loading...");

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),RegisterActivity.class));
            }
        });


        fetchData(0);

        countDownStart();

        return view;
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
                    int mon = 3;
                    int day = Integer.parseInt(currentDate.substring(8,10));
                    Log.d("DAY", day + "");
                    DateTime start = new DateTime(yr,mon,day,0,0,0);
                    DateTime end = new DateTime(yr, mon+1, 5,0,0,0);
                    int days = Days.daysBetween(start, end).getDays();
                    progressBar.setProgress(days*10);
                    tvDaysToGo.setText(days+"");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 1 * 1000);
    }

    private void textViewGone() {
        rl.setVisibility(View.GONE);
    }

    private void fetchData(final int flag) {
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("NewApi")
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    try {
                        btnRegister.setVisibility(View.GONE);
                        tvUserName.setText(dataSnapshot.child(FIREBASE_REF_NAME).getValue().toString());
                        if (dataSnapshot.child(Constants.FIREBASE_REF_OJASS_ID).getValue() != null) {
                            tvUserOjId.setText(dataSnapshot.child(Constants.FIREBASE_REF_OJASS_ID).getValue().toString());
                            tvUserOjId.setTextColor(getResources().getColor(R.color.forest_green));
                        } else {
                            tvUserOjId.setText(Constants.PAYMENT_DUE);
                            tvUserOjId.setTextColor(Color.RED);
                        }
                        if (dataSnapshot.child(FIREBASE_REF_PARTICIPATED_EVENTS).exists()){
                            //prepareRecyclerView(dataSnapshot.child(FIREBASE_REF_PARTICIPATED_EVENTS));
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

}
