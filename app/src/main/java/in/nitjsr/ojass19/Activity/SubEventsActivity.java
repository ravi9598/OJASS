package in.nitjsr.ojass19.Activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import in.nitjsr.ojass19.Fragments.AboutFragment;
import in.nitjsr.ojass19.Fragments.CoordianatorFragment;
import in.nitjsr.ojass19.Fragments.DetailsFragment;
import in.nitjsr.ojass19.Fragments.PrizeFragment;
import in.nitjsr.ojass19.Fragments.RulesFragment;
import in.nitjsr.ojass19.R;
import in.nitjsr.ojass19.Utils.BtmNavVisCallback;

public class SubEventsActivity extends AppCompatActivity implements View.OnClickListener, BtmNavVisCallback {
    private BottomNavigationView bottom_bar;
    private FrameLayout fl;
    private RelativeLayout mLayout;
    private Handler handler;
    private FragmentTransaction ft;
    public static String event_name;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subevents);
        init();
        hideBottomBar();

        mLayout.setOnClickListener(this);
        fl.setOnClickListener(this);
        setInitialFragment();
        bottom_bar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment f = getSupportFragmentManager().findFragmentById(R.id.sub_events_fl);
                handler.removeMessages(0);
                hideBottomBar();
                switch (menuItem.getItemId()){
                    case R.id.about:

                        if(!(f instanceof AboutFragment)){
                            ft = getSupportFragmentManager().beginTransaction();
                            ft.replace(R.id.sub_events_fl,new AboutFragment()).commit();
                            return true;
                        }
                        break;
                    case R.id.details:
                        if(!(f instanceof DetailsFragment)){
                            ft = getSupportFragmentManager().beginTransaction();
                            ft.replace(R.id.sub_events_fl,new DetailsFragment()).commit();
                            return true;
                        }
                        break;
                    case R.id.rules:
                        if(!(f instanceof RulesFragment)){
                            ft = getSupportFragmentManager().beginTransaction();
                            ft.replace(R.id.sub_events_fl,new RulesFragment()).commit();
                            return true;
                        }
                        break;
                    case R.id.prize:
                        if(!(f instanceof PrizeFragment)){
                            ft = getSupportFragmentManager().beginTransaction();
                            ft.replace(R.id.sub_events_fl,new PrizeFragment()).commit();
                            return true;
                        }
                        break;
                    case R.id.coord:
                        if(!(f instanceof CoordianatorFragment)){
                            ft = getSupportFragmentManager().beginTransaction();
                            ft.replace(R.id.sub_events_fl,new CoordianatorFragment()).commit();
                            return true;
                        }
                }
                return false;
            }
        });
    }
    void init(){
        bottom_bar = findViewById(R.id.bottom_bar);
        fl = findViewById(R.id.sub_events_fl);
        mLayout = findViewById(R.id.sub_events_layout);
        handler = new Handler();
        event_name = getIntent().getStringExtra("event_name");
    }
    void setInitialFragment(){
        ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.sub_events_fl,new AboutFragment()).commit();
    }
    void showBottomBar(){
        bottom_bar.setVisibility(View.VISIBLE);
        AnimationSet set = new AnimationSet(true);
        Animation tranimate = new TranslateAnimation(0,0,+bottom_bar.getHeight()+convertDpToPixel(10),0);
        tranimate.setDuration(500);
        set.addAnimation(tranimate);

        Animation alphaanimate = new AlphaAnimation(0.0f,1.0f);
        alphaanimate.setDuration(500);
        set.addAnimation(alphaanimate);
        bottom_bar.startAnimation(set);
    }
    void hideBottomBar(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                AnimationSet set = new AnimationSet(true);
                TranslateAnimation tranimate = new TranslateAnimation(0,0,0,+bottom_bar.getHeight()+convertDpToPixel(10));
                tranimate.setDuration(500);
                set.addAnimation(tranimate);
                Animation alphaanimate = new AlphaAnimation(1.0f,0.0f);
                alphaanimate.setDuration(500);
                set.addAnimation(alphaanimate);

                bottom_bar.startAnimation(set);
                bottom_bar.setVisibility(View.GONE);
            }
        },3000);
    }
    public static float convertDpToPixel(float dp){
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return Math.round(px);
    }

    @Override
    public void onClick(View v) {
        if(bottom_bar.getVisibility()==View.GONE) {
            showBottomBar();
            hideBottomBar();
        }else{
            handler.removeMessages(0);
            hideBottomBar();
        }
    }

    @Override
    public void onCallback() {
        if(bottom_bar.getVisibility()==View.GONE) {
            showBottomBar();
            hideBottomBar();
        }else{
            handler.removeMessages(0);
            hideBottomBar();
        }
    }
}
