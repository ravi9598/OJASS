package in.nitjsr.ojass19.Activity;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.viven.imagezoom.ImageZoomHelper;

import in.nitjsr.ojass19.Fragments.ItinaryFragment;
import in.nitjsr.ojass19.R;
import in.nitjsr.ojass19.Utils.Constants;
import in.nitjsr.ojass19.Utils.Utilities;

import static in.nitjsr.ojass19.Fragments.ItinaryFragment.INTENT_PARAM_DAY;
import static in.nitjsr.ojass19.Utils.Constants.ITINARY_IMAGES;

public class FullScreenActivity extends AppCompatActivity{

    private int currPos = 0;
    private ImageView iv_itinary;
    private ImageZoomHelper imageZoomHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);

        imageZoomHelper = new ImageZoomHelper(this);
        iv_itinary=findViewById(R.id.iv_itinary);
        ImageZoomHelper.setViewZoomable(iv_itinary);
        currPos = getIntent().getIntExtra(INTENT_PARAM_DAY, 0);
        Utilities.setPicassoImage(this, ITINARY_IMAGES[currPos], iv_itinary, Constants.RECT_PLACEHOLDER);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        return imageZoomHelper.onDispatchTouchEvent(ev) || super.dispatchTouchEvent(ev);
    }
}
