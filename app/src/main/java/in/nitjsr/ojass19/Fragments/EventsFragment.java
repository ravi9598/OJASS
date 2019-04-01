package in.nitjsr.ojass19.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import in.nitjsr.ojass19.Activity.HomeActivity;
import in.nitjsr.ojass19.Adapters.BranchHeadAdapter;
import in.nitjsr.ojass19.Adapters.EventsPagerAdaptor;
import in.nitjsr.ojass19.Fragments.MajorEventsFragments.Aakriti;
import in.nitjsr.ojass19.Fragments.MajorEventsFragments.Armageddon;
import in.nitjsr.ojass19.Fragments.MajorEventsFragments.ArthaShastra;
import in.nitjsr.ojass19.Fragments.MajorEventsFragments.Avartan;
import in.nitjsr.ojass19.Fragments.MajorEventsFragments.CircuitHouse;
import in.nitjsr.ojass19.Fragments.MajorEventsFragments.DeusXMachina;
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
import in.nitjsr.ojass19.Modals.BranchHeadModel;
import in.nitjsr.ojass19.Modals.CoordinatorsModel;
import in.nitjsr.ojass19.Modals.EventModel;
import in.nitjsr.ojass19.Modals.PrizeModel1;
import in.nitjsr.ojass19.Modals.PrizeModel2;
import in.nitjsr.ojass19.Modals.RulesModel;
import in.nitjsr.ojass19.R;
import in.nitjsr.ojass19.Utils.BtmNavVisCallback;
import in.nitjsr.ojass19.Utils.Constants;
import in.nitjsr.ojass19.Utils.DataLoadedInterface;

import static in.nitjsr.ojass19.Utils.Constants.eventImageName;
import static in.nitjsr.ojass19.Utils.Constants.eventNames;

public class EventsFragment extends Fragment  {

    private TabLayout mTab;
    private ViewPager mPager;

    private int current_tab = 0;
    private EventsPagerAdaptor mAdapter;
    private FloatingActionButton fab;
    private Dialog mDialog;

    private List<BranchHeadModel> data=new ArrayList<>();
    private RecyclerView rDView;
    private BranchHeadAdapter bhAdapter;
    public FrameLayout fl;
    private int fabFlag=1;
    private DataLoadedInterface mDataLoadedCallback;
    public FrameLayout vp_frame;
    private DatabaseReference mRef;
    AlertDialog alertDialog;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_events,container,false);
        init();
        mTab = view.findViewById(R.id.events_tab_layout);
        mPager = view.findViewById(R.id.events_vp);
        fab = view.findViewById(R.id.events_fab);
        fl = view.findViewById(R.id.progress_bar_layout);
        vp_frame = view.findViewById(R.id.vplayout);
        setVP();

        mTab.setupWithViewPager(mPager);
        mTab.setTabGravity(Gravity.CENTER);

        if(HomeActivity.data!=null){
            fl.setVisibility(View.GONE);
            vp_frame.setVisibility(View.VISIBLE);
        }
        createTabs();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog = new Dialog(getContext());
                mDialog.setContentView(R.layout.dialog_branch_head);
                RecyclerView rview = mDialog.findViewById(R.id.dialog_rview);
                RecyclerView.LayoutManager lm = new LinearLayoutManager(getContext());
                rview.setLayoutManager(lm);
                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),((LinearLayoutManager) lm).getOrientation());
                rview.addItemDecoration(dividerItemDecoration);
                bhAdapter = new BranchHeadAdapter(data);
                rview.setAdapter(bhAdapter);

                //first tym fab press
                if(fabFlag==1){
                    int lenBH = Constants.branchHeadName[0].length;
                    data.clear();
                    for(int i=0;i<lenBH;i++) {
                        BranchHeadModel model = new BranchHeadModel();
                        model.name = Constants.branchHeadName[0][i];
                        model.phone = Constants.branchHeadNum[0][i];
                        data.add(model);
                    }
                    bhAdapter.notifyDataSetChanged();
                    fabFlag=0;
                }
                mDialog.show();

            }
        });

        mPager.setCurrentItem(0);
        mTab.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mPager){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mPager.setCurrentItem(tab.getPosition(),false);
                fabFlag=0;
                //add Branch head
                int lenBH = Constants.branchHeadName[tab.getPosition()].length;
                data.clear();
                for(int i=0;i<lenBH;i++){
                    BranchHeadModel model=new BranchHeadModel();
                    model.name=Constants.branchHeadName[tab.getPosition()][i];
                    model.phone=Constants.branchHeadNum[tab.getPosition()][i];
                    data.add(model);
                }
                if(bhAdapter!=null)
                    bhAdapter.notifyDataSetChanged();

                for(int i=0;i<17;i++){
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
        for(int i=0;i<17;i++){
            mTab.getTabAt(i).setCustomView(R.layout.tab_icon);
            View view = mTab.getTabAt(i).getCustomView();
            //Change image and name
            ImageView iv = view.findViewById(R.id.events_tab_image);
            TextView tv = view.findViewById(R.id.events_tab_name);
            tv.setText(eventNames[i]);
            iv.setImageResource(eventImageName[i]);
            if(i==0){
                View v = view.findViewById(R.id.underline);
                v.setVisibility(View.VISIBLE);
                tv.measure(View.MeasureSpec.UNSPECIFIED,View.MeasureSpec.UNSPECIFIED);
                v.getLayoutParams().width = tv.getMeasuredWidth();
            }
        }
    }


    private void init() {
        mAdapter = new EventsPagerAdaptor(getFragmentManager());
    }

    private void setVP(){
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
        mAdapter.addFragment(new SchoolEvents(),eventNames[16]);

        mPager.setAdapter(mAdapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mDataLoadedCallback = (DataLoadedInterface) context;

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mDataLoadedCallback = null;
    }
}