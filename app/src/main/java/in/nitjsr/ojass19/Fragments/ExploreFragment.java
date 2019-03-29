package in.nitjsr.ojass19.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import in.nitjsr.ojass19.Activity.FAQActivity;
import in.nitjsr.ojass19.R;

public class ExploreFragment extends Fragment {

    private LinearLayout llFAQ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fragment_explore,container,false);


        llFAQ=view.findViewById(R.id.ll_faq);


        llFAQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),FAQActivity.class));
            }
        });

        return view;
    }

}
