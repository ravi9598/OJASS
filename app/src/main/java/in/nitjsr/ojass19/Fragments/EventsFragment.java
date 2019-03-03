package in.nitjsr.ojass19.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import in.nitjsr.ojass19.Adapters.EventsPagerAdaptor;
import in.nitjsr.ojass19.Fragments.MajorEventsFragments.Aakriti;
import in.nitjsr.ojass19.Fragments.MajorEventsFragments.Armageddon;
import in.nitjsr.ojass19.Fragments.MajorEventsFragments.ArthaShastra;
import in.nitjsr.ojass19.Fragments.MajorEventsFragments.Avartan;
import in.nitjsr.ojass19.Fragments.MajorEventsFragments.CircuitHouse;
import in.nitjsr.ojass19.Fragments.MajorEventsFragments.DeusXMachina;
import in.nitjsr.ojass19.Fragments.MajorEventsFragments.Exposicion;
import in.nitjsr.ojass19.Fragments.MajorEventsFragments.LiveCS;
import in.nitjsr.ojass19.Fragments.MajorEventsFragments.NSCET;
import in.nitjsr.ojass19.Fragments.MajorEventsFragments.NeoDrishti;
import in.nitjsr.ojass19.Fragments.MajorEventsFragments.NoGroundZero;
import in.nitjsr.ojass19.Fragments.MajorEventsFragments.Paraphernalia;
import in.nitjsr.ojass19.Fragments.MajorEventsFragments.Prayas;
import in.nitjsr.ojass19.Fragments.MajorEventsFragments.Produs;
import in.nitjsr.ojass19.Fragments.MajorEventsFragments.RiseOfMachines;
import in.nitjsr.ojass19.Fragments.MajorEventsFragments.SchoolEvents;
import in.nitjsr.ojass19.Fragments.MajorEventsFragments.SiliconValley;
import in.nitjsr.ojass19.Fragments.MajorEventsFragments.ViswaCodeGenesis;
import in.nitjsr.ojass19.R;

import static in.nitjsr.ojass19.Utils.Constants.eventNames;

public class EventsFragment extends Fragment {

    private TabLayout mTab;
    private ViewPager mPager;

    private int current_tab = 0;
    private EventsPagerAdaptor mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_events,container,false);

        mTab = view.findViewById(R.id.events_tab_layout);
        mPager = view.findViewById(R.id.events_vp);

        setVP();

        mTab.setupWithViewPager(mPager);
        mTab.setTabGravity(Gravity.CENTER);
        mTab.setSmoothScrollingEnabled(true);
        createTabs();

        mTab.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mPager){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mPager.setCurrentItem(tab.getPosition(),false);
                for(int i=0;i<18;i++){
                    View view = mTab.getTabAt(i).getCustomView();
                    TextView tv = view.findViewById(R.id.events_tab_name);
                    View v = view.findViewById(R.id.underline);
                    if(i==tab.getPosition()) {
                        v.setVisibility(View.VISIBLE);
                        v.getLayoutParams().width = tv.getWidth();
                        expand(v);
                    }
                    else {
                        v.setVisibility(View.GONE);
                    }

                }

            }

        });
        
        return view;
    }

    private void expand(View v) {
        Animation animation = AnimationUtils.loadAnimation(getActivity(),R.anim.expand);
        v.startAnimation(animation);
    }

    private void createTabs(){
            for(int i=0;i<18;i++){
                mTab.getTabAt(i).setCustomView(R.layout.tab_icon);
                View view = mTab.getTabAt(i).getCustomView();
                //Change image and name
                TextView tv = view.findViewById(R.id.events_tab_name);
                tv.setText(eventNames[i]);
                if(i==0){
                    View v = view.findViewById(R.id.underline);

                    v.setVisibility(View.VISIBLE);
                    tv.measure(View.MeasureSpec.UNSPECIFIED,View.MeasureSpec.UNSPECIFIED);
                    v.getLayoutParams().width = tv.getMeasuredWidth();
                }
            }
        }


    private void init() {

    }

    private void setVP(){
        mAdapter = new EventsPagerAdaptor(getFragmentManager());
        mAdapter.addFragment(new RiseOfMachines(),eventNames[0]);
        mAdapter.addFragment(new ViswaCodeGenesis(),eventNames[1]);
        mAdapter.addFragment(new CircuitHouse(),eventNames[2]);
        mAdapter.addFragment(new SiliconValley(),eventNames[3]);
        mAdapter.addFragment(new ArthaShastra(),eventNames[4]);
        mAdapter.addFragment(new Aakriti(),eventNames[5]);
        mAdapter.addFragment(new DeusXMachina(),eventNames[6]);
        mAdapter.addFragment(new Produs(),eventNames[7]);
        mAdapter.addFragment(new Paraphernalia(),eventNames[8]);
        mAdapter.addFragment(new NeoDrishti(),eventNames[9]);
        mAdapter.addFragment(new Avartan(),eventNames[10]);
        mAdapter.addFragment(new Armageddon(),eventNames[11]);
        mAdapter.addFragment(new Prayas(),eventNames[12]);
        mAdapter.addFragment(new NoGroundZero(),eventNames[13]);
        mAdapter.addFragment(new NSCET(),eventNames[14]);
        mAdapter.addFragment(new LiveCS(),eventNames[15]);
        mAdapter.addFragment(new Exposicion(),eventNames[16]);
        mAdapter.addFragment(new SchoolEvents(),eventNames[17]);

        mPager.setAdapter(mAdapter);
    }
}
