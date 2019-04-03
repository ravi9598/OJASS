package in.nitjsr.ojass19.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.viven.imagezoom.ImageZoomHelper;

import org.joda.time.DateTime;
import org.joda.time.Days;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import in.nitjsr.ojass19.Activity.FullScreenActivity;
import in.nitjsr.ojass19.R;
import in.nitjsr.ojass19.Utils.Constants;
import in.nitjsr.ojass19.Utils.Utilities;

public class ItinaryFragment extends Fragment implements View.OnClickListener{

    private ImageView day1, day2, day3;
    public  ImageZoomHelper imageZoomHelper;
    private String itinary_images_demo[] = {
            "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/ItinaryImages%2Fday_1.jpg?alt=media&token=56006e0a-5b7c-4251-a8c0-077a69439e65",
            "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/ItinaryImages%2Fday_2.jpg?alt=media&token=546d098d-af9b-4db7-b081-53be3d039e8c",
            "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/ItinaryImages%2Fday_3.jpg?alt=media&token=d56bc6d9-01ca-4e93-83d6-5ba6705916f7"
    };

    private String itinary_images[] = {
            "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/ItinaryImages%2Fday_1_real.jpg?alt=media&token=df5677cd-9ad6-4539-948d-cede23d1573e",
            "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/ItinaryImages%2Fday_2_reaal.jpg?alt=media&token=3c63b221-0786-47a2-b56e-e369b51808d2",
            "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/ItinaryImages%2Fday_3_real.jpg?alt=media&token=c8232337-8df5-4518-8010-87e4cc8e855c"
    };

    public static final String INTENT_PARAM_DAY = "intentParamDay";

    public ItinaryFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_itinary,container,false);

        imageZoomHelper = new ImageZoomHelper(getActivity());
        day1 = view.findViewById(R.id.iv_day1);
        day2 = view.findViewById(R.id.iv_day2);
        day3 = view.findViewById(R.id.iv_day3);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        String currentDate = dateFormat.format(calendar.getTime());
        int yr = 2019;
        int mon = calendar.get(Calendar.MONTH)+1;
        int day = Integer.parseInt(currentDate.substring(8,10));

        DateTime start = new DateTime(yr,mon,day,0,0,0);
        DateTime end = new DateTime(yr, 4, 5,0,0,0);
        int days = Days.daysBetween(start, end).getDays();

        if(days>1){
            Utilities.setPicassoImage(getContext(), itinary_images_demo[0], day1, Constants.RECT_PLACEHOLDER);
            Utilities.setPicassoImage(getContext(), itinary_images_demo[1], day2, Constants.RECT_PLACEHOLDER);
            Utilities.setPicassoImage(getContext(), itinary_images_demo[2], day3, Constants.RECT_PLACEHOLDER);
        }
        else
        {
            Utilities.setPicassoImage(getContext(), itinary_images[0], day1, Constants.RECT_PLACEHOLDER);
            Utilities.setPicassoImage(getContext(), itinary_images[1], day2, Constants.RECT_PLACEHOLDER);
            Utilities.setPicassoImage(getContext(), itinary_images[2], day3, Constants.RECT_PLACEHOLDER);
        }


        ImageZoomHelper.setViewZoomable(view.findViewById(R.id.iv_day1));
        ImageZoomHelper.setViewZoomable(view.findViewById(R.id.iv_day2));
        ImageZoomHelper.setViewZoomable(view.findViewById(R.id.iv_day3));


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