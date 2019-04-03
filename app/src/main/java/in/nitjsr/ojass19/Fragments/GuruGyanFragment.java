package in.nitjsr.ojass19.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import in.nitjsr.ojass19.Adapters.PosterAdapter;
import in.nitjsr.ojass19.R;
import in.nitjsr.ojass19.Utils.BlurCallback;
import jp.wasabeef.blurry.Blurry;
import me.relex.circleindicator.CircleIndicator;

import static android.os.Looper.getMainLooper;
import static in.nitjsr.ojass19.Fragments.HomeFragment.POSITION_PARAM;
import static in.nitjsr.ojass19.Utils.Constants.FIREBASE_REF_GURU_GYAN;
import static in.nitjsr.ojass19.Utils.Constants.FIREBASE_REF_GURU_GYAN_DATE;
import static in.nitjsr.ojass19.Utils.Constants.FIREBASE_REF_GURU_GYAN_IMAGE;
import static in.nitjsr.ojass19.Utils.Constants.FIREBASE_REF_GURU_GYAN_LONG_DESC;
import static in.nitjsr.ojass19.Utils.Constants.FIREBASE_REF_GURU_GYAN_SHORT_DESC;
import static in.nitjsr.ojass19.Utils.Constants.FIREBASE_REF_GURU_GYAN_TITLE;

public class GuruGyanFragment extends Fragment implements View.OnClickListener,ViewPager.OnPageChangeListener,BlurCallback {
    private Handler handler,mHandler;
    private RelativeLayout gurugyan_layout;
    private CardView card;
    private TextView tvShortDesc, tvLongDesc, tvTitle, tvDate;
    private ViewPager viewPager;
    private List<String> images=new ArrayList<>();
    private String[]  title, shortDesc, longDesc, dates;
    private PosterAdapter posterAdapter;
    private boolean firstTimeOpen = true;
    private int previousPos = 0;
    private boolean isSmallVisible = true;
    private BlurCallback mCallback;
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if (isSmallVisible) card.performClick();
        }
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gurugyan_layout,container,false);
        init(view);
        prepareViewPager(view);

        mHandler = new Handler(getMainLooper());
        mHandler.postDelayed(mRunnable, 1500);

        card.setOnClickListener(this);
        view.findViewById(R.id.ib_back_guru_gyan).setOnClickListener(this);

        return view;
    }
    void init(View view){
        gurugyan_layout = view.findViewById(R.id.gurugyan_layout);
        card = view.findViewById(R.id.ll_info);
        tvShortDesc = view.findViewById(R.id.tv_guru_gyan_short_desc);
        tvLongDesc = view.findViewById(R.id.tv_guru_gyan_long_desc);
        tvTitle = view.findViewById(R.id.tv_guru_gyan_celeb_name);
        tvDate = view.findViewById(R.id.tv_gg_date);
        viewPager = view.findViewById(R.id.vp_guru_gyan);

    }
    private void prepareViewPager(final View view) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(FIREBASE_REF_GURU_GYAN);
        ref.keepSynced(true);


        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    try{

                        int childCount = (int) dataSnapshot.getChildrenCount();
                        title = new String[childCount];
                        shortDesc = new String[childCount];
                        longDesc = new String[childCount];
                        dates = new String[childCount];
                        int currIndex = 0;
                        for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                            images.add(dataSnapshot1.child(FIREBASE_REF_GURU_GYAN_IMAGE).getValue().toString());
                            title[currIndex] = dataSnapshot1.child(FIREBASE_REF_GURU_GYAN_TITLE).getValue().toString();
                            shortDesc[currIndex] = dataSnapshot1.child(FIREBASE_REF_GURU_GYAN_SHORT_DESC).getValue().toString();
                            longDesc[currIndex] = dataSnapshot1.child(FIREBASE_REF_GURU_GYAN_LONG_DESC).getValue().toString();
                            dates[currIndex] = dataSnapshot1.child(FIREBASE_REF_GURU_GYAN_DATE).getValue().toString();

                            currIndex++;

                        }
                        posterAdapter = new PosterAdapter(GuruGyanFragment.this,images,null);
                       viewPager.setAdapter(posterAdapter);
                        viewPager.setCurrentItem(0);
                        ((CircleIndicator)view.findViewById(R.id.ci_guru_gyan)).setViewPager(viewPager);
                        viewPager.addOnPageChangeListener(GuruGyanFragment.this);
                        tvDate.setText(dates[0]);
                        tvTitle.setText(title[0]);
                        tvShortDesc.setText(shortDesc[0]);
                        tvLongDesc.setText(longDesc[0]);

                    } catch (Exception e){

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Error",databaseError.getMessage());
            }
        });

    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (firstTimeOpen) firstTimeOpen = false;
        else mHandler.removeCallbacks(mRunnable);
    }

    @Override
    public void onPageSelected(int position) {
        Animation animLeft = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_left);
        Animation animRight = AnimationUtils.loadAnimation(getContext(), R.anim.slide_out_right);
        if (position > previousPos) {
            if (!isSmallVisible)
                tvLongDesc.startAnimation(animRight);
            tvShortDesc.startAnimation(animRight);
            tvDate.startAnimation(animRight);
            tvTitle.startAnimation(animRight);
        } else {
            if (!isSmallVisible)
                tvLongDesc.startAnimation(animLeft);
            tvShortDesc.startAnimation(animLeft);
            tvDate.startAnimation(animLeft);
            tvTitle.startAnimation(animLeft);
        }
        previousPos = position;

        tvDate.setText(dates[position]);
        tvTitle.setText(title[position]);
        tvShortDesc.setText(shortDesc[position]);
        tvLongDesc.setText(longDesc[position]);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == ViewPager.SCROLL_STATE_IDLE){
            mHandler.postDelayed(mRunnable, 1500);
        }
    }

    @Override
    public void onClick(View view) {
        if (view == card){
            Log.e("TLO","LOL");
            if (isSmallVisible){
                tvLongDesc.setVisibility(View.VISIBLE);
                isSmallVisible = false;
            } else {
                tvLongDesc.setVisibility(View.GONE);
                isSmallVisible = true;
            }
            mHandler.removeCallbacks(mRunnable);
        } else if (view.getId() == R.id.ib_back_guru_gyan){

        }
    }

    @Override
    public void onBlurCallback() {
        if(mCallback!=null)
            mCallback.onBlurCallback();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallback = (BlurCallback) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback=null;
    }
}