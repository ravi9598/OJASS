package in.nitjsr.ojass19.Activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import in.nitjsr.ojass19.R;
import in.nitjsr.ojass19.Utils.SharedPrefManager;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_SCREEN_TIMER =  1600;
    private ImageView ivSplashIcon, ivSplashName;
    private Button btnContinue;
    private SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        init();

        sharedPrefManager = new SharedPrefManager(this);

        if(sharedPrefManager.isLoggedIn())
            moveToHome();

        animate();

        doTheDelayStuff();

    }

    private void doTheDelayStuff() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                btnContinue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        moveToLogin();
                    }
                });
            }
        }, SPLASH_SCREEN_TIMER);
    }

    private void animate() {
        ObjectAnimator scaleXAnimationIcon = ObjectAnimator.ofFloat(ivSplashIcon, "scaleX", 5.0F, 1.0F);
        ObjectAnimator scaleXAnimationName = ObjectAnimator.ofFloat(ivSplashName, "scaleX", 5.0F, 1.0F);
        scaleXAnimationIcon.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleXAnimationName.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleXAnimationIcon.setDuration(1500);
        scaleXAnimationName.setDuration(1500);

        ObjectAnimator scaleYAnimationIcon = ObjectAnimator.ofFloat(ivSplashIcon, "scaleY", 5.0F, 1.0F);
        ObjectAnimator scaleYAnimationName = ObjectAnimator.ofFloat(ivSplashIcon, "scaleY", 5.0F, 1.0F);
        scaleYAnimationIcon.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleYAnimationName.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleYAnimationIcon.setDuration(1500);
        scaleYAnimationName.setDuration(1500);

        ObjectAnimator alphaAnimationIcon = ObjectAnimator.ofFloat(ivSplashIcon, "alpha", 0.0F, 1.0F);
        ObjectAnimator alphaAnimationName = ObjectAnimator.ofFloat(ivSplashName, "alpha", 0.0F, 1.0F);
        alphaAnimationIcon.setInterpolator(new AccelerateDecelerateInterpolator());
        alphaAnimationName.setInterpolator(new AccelerateDecelerateInterpolator());
        alphaAnimationIcon.setDuration(1500);
        alphaAnimationName.setDuration(1500);

        AnimatorSet animatorSetIcon = new AnimatorSet();
        AnimatorSet animatorSetName=new AnimatorSet();
        animatorSetIcon.play(scaleXAnimationIcon).with(scaleYAnimationIcon).with(alphaAnimationIcon);
        animatorSetName.play(scaleXAnimationName).with(scaleYAnimationName).with(alphaAnimationName);
        animatorSetIcon.start();
        animatorSetName.start();

        btnContinue.setVisibility(View.VISIBLE);
    }

    private void init() {
        ivSplashIcon=findViewById(R.id.img_splash_icon);
        ivSplashName=findViewById(R.id.img_splash_name);
        btnContinue=findViewById(R.id.btn_continue);

    }

    private void moveToLogin() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    private void moveToHome() {
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }

}
