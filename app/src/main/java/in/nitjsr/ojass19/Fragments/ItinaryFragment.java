package in.nitjsr.ojass19.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import in.nitjsr.ojass19.Activity.FullScreenActivity;
import in.nitjsr.ojass19.R;
import in.nitjsr.ojass19.Utils.Constants;
import in.nitjsr.ojass19.Utils.Utilities;

public class ItinaryFragment extends Fragment implements View.OnClickListener{

    private ImageView day1, day2, day3;
    private String itinary_images[] = {
            "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/ItinaryImages%2Fplaceholder_rect.jpeg?alt=media&token=2eb01dca-2903-4a6e-acd5-7075597c2215",
            "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/ItinaryImages%2Fplaceholder_rect.jpeg?alt=media&token=2eb01dca-2903-4a6e-acd5-7075597c2215",
            "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/ItinaryImages%2Fplaceholder_rect.jpeg?alt=media&token=2eb01dca-2903-4a6e-acd5-7075597c2215"
    };

    public static final String INTENT_PARAM_DAY = "intentParamDay";

    public ItinaryFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_itinary,container,false);

        day1 = view.findViewById(R.id.iv_day1);
        day2 = view.findViewById(R.id.iv_day2);
        day3 = view.findViewById(R.id.iv_day3);

        Utilities.setPicassoImage(getContext(), itinary_images[0], day1, Constants.RECT_PLACEHOLDER);
        Utilities.setPicassoImage(getContext(), itinary_images[1], day2, Constants.RECT_PLACEHOLDER);
        Utilities.setPicassoImage(getContext(), itinary_images[2], day3, Constants.RECT_PLACEHOLDER);

        view.findViewById(R.id.cv_day1).setOnClickListener(this);
        view.findViewById(R.id.cv_day2).setOnClickListener(this);
        view.findViewById(R.id.cv_day3).setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getContext(), FullScreenActivity.class);
        if (view.getId() == R.id.cv_day1) intent.putExtra(INTENT_PARAM_DAY, 0);
        if (view.getId() == R.id.cv_day2) intent.putExtra(INTENT_PARAM_DAY, 1);
        if (view.getId() == R.id.cv_day3) intent.putExtra(INTENT_PARAM_DAY, 2);
        startActivity(intent);
    }
}
