package in.nitjsr.ojass19.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import in.nitjsr.ojass19.R;
import in.nitjsr.ojass19.Utils.Constants;
import in.nitjsr.ojass19.Utils.Utilities;

import static in.nitjsr.ojass19.Fragments.ItinaryFragment.INTENT_PARAM_DAY;
import static in.nitjsr.ojass19.Utils.Constants.ITINARY_IMAGES;

public class FullScreenActivity extends AppCompatActivity/* implements View.OnClickListener*/ {

    //private ImageButton ibPrev, ibNext;
    private int currPos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);

//        ibPrev = findViewById(R.id.ib_prev);
//        ibNext = findViewById(R.id.ib_next);

        currPos = getIntent().getIntExtra(INTENT_PARAM_DAY, 0);
        Utilities.setPicassoImage(this, ITINARY_IMAGES[currPos], ((ImageView)findViewById(R.id.iv_itinary)), Constants.RECT_PLACEHOLDER);

        //if (currPos == 0) ibPrev.setVisibility(View.INVISIBLE);
        //else if (currPos == 2) ibNext.setVisibility(View.INVISIBLE);

        //ibPrev.setOnClickListener(this);
        //ibNext.setOnClickListener(this);

    }

//    @Override
//    public void onClick(View view) {
//        if (view == ibPrev){
//            if (currPos > 0) {
//                currPos--;
//                ibNext.setVisibility(View.VISIBLE);
//                if (currPos == 0) ibPrev.setVisibility(View.INVISIBLE);
//            }
//        } else if (view == ibNext) {
//            if (currPos < 2) {
//                currPos++;
//                ibPrev.setVisibility(View.VISIBLE);
//                if (currPos == 2) ibNext.setVisibility(View.INVISIBLE);
//            }
//        }
//        Utilities.setPicassoImage(this, ITINARY_IMAGES[currPos], ((ImageView)findViewById(R.id.iv_itinary)), Constants.RECT_PLACEHOLDER);
//    }
}
