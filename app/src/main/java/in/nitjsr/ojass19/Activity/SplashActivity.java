package in.nitjsr.ojass19.Activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

import in.nitjsr.ojass19.R;
import in.nitjsr.ojass19.Utils.SharedPrefManager;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_SCREEN_TIMER = 3000;
    private SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sharedPrefManager = new SharedPrefManager(this);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (sharedPrefManager.isLoggedIn())
                    moveToHome();
                else
                    moveToLogin();
            }
        }, SPLASH_SCREEN_TIMER);
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
