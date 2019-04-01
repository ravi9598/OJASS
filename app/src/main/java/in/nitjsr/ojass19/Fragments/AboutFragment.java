package in.nitjsr.ojass19.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import in.nitjsr.ojass19.Activity.HomeActivity;
import in.nitjsr.ojass19.Activity.SubEventsActivity;
import in.nitjsr.ojass19.Modals.EventModel;
import in.nitjsr.ojass19.R;
import in.nitjsr.ojass19.Utils.BtmNavVisCallback;

public class AboutFragment extends Fragment {
    private TextView abt;
    private LinearLayout abt_layout;
    private BtmNavVisCallback mCallback;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about,container,false);
        init(view);
        abt_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onCallback();
            }
        });
        getData();
        return view;
    }
    void init(View view){
        abt = view.findViewById(R.id.text_about);
        abt_layout = view.findViewById(R.id.abt_layout);
    }
    void getData(){
        for(EventModel em: HomeActivity.data){
            if(em.getName()!=null) {
                if (em.getName().compareToIgnoreCase(SubEventsActivity.event_name) == 0) {
                    Log.e("ABOUT",em.getName());
                    abt.setText(Html.fromHtml(em.getAbout()));
                    break;
                }
            }
        }
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallback = (BtmNavVisCallback) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }
}