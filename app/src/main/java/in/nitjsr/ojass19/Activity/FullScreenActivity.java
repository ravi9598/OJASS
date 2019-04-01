package in.nitjsr.ojass19.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import in.nitjsr.ojass19.R;
import in.nitjsr.ojass19.Utils.Constants;
import in.nitjsr.ojass19.Utils.Utilities;

import static in.nitjsr.ojass19.Fragments.ItinaryFragment.INTENT_PARAM_DAY;
import static in.nitjsr.ojass19.Utils.Constants.ITINARY_IMAGES;

public class FullScreenActivity extends AppCompatActivity{

    private int currPos = 0;
    private ImageView iv_itinary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);

        iv_itinary=findViewById(R.id.iv_itinary);

        currPos = getIntent().getIntExtra(INTENT_PARAM_DAY, 0);
        Utilities.setPicassoImage(this, ITINARY_IMAGES[currPos], iv_itinary, Constants.RECT_PLACEHOLDER);

    }
}
