package in.nitjsr.ojass19.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import in.nitjsr.ojass19.Activity.HomeActivity;
import in.nitjsr.ojass19.Activity.SubEventsActivity;
import in.nitjsr.ojass19.Adapters.CoordinatorsAdapter;
import in.nitjsr.ojass19.Modals.CoordinatorsModel;
import in.nitjsr.ojass19.Modals.EventModel;
import in.nitjsr.ojass19.R;
import in.nitjsr.ojass19.Utils.BtmNavVisCallback;
import in.nitjsr.ojass19.Utils.OnSwipeTouchListener;

public class CoordianatorFragment extends Fragment {
    //widgets
    private RecyclerView rView;
    private BtmNavVisCallback mCallback;
    private CoordinatorsAdapter mAdapter;
    private RelativeLayout pLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coordinator,container,false);
        init(view);
        rView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onCallback();
            }
        });
        rView.setOnTouchListener(new OnSwipeTouchListener(getContext()){
            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                mCallback.onCallback();
            }

            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                mCallback.onCallback();
            }

            @Override
            public void onSwipeTop() {
                super.onSwipeTop();
                mCallback.onCallback();
            }

            @Override
            public void onSwipeBottom() {
                super.onSwipeBottom();
                mCallback.onCallback();
            }


        });
        pLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onCallback();
            }
        });
        mAdapter = new CoordinatorsAdapter(getData());
        rView.setAdapter(mAdapter);
        return view;
    }
    void init(View view){
        rView = view.findViewById(R.id.coord_rview);
        pLayout = view.findViewById(R.id.coord_layout);
        rView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
    List<CoordinatorsModel> getData(){
        List<CoordinatorsModel> list = new ArrayList<>();
        for(EventModel em: HomeActivity.data){
            if(em.getName()!=null) {
                if (em.getName().compareToIgnoreCase(SubEventsActivity.event_name) == 0) {
                    list = em.getCoordinatorsModelArrayList();
                    break;

                }
            }
        }
        return list;
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