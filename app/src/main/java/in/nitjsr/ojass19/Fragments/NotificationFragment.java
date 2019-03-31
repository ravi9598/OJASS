package in.nitjsr.ojass19.Fragments;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import in.nitjsr.ojass19.Activity.NotificationsActivity;
import in.nitjsr.ojass19.Adapters.FAQAdapter;
import in.nitjsr.ojass19.Modals.FaqModel;
import in.nitjsr.ojass19.Modals.TitleChild;
import in.nitjsr.ojass19.Modals.TitleCreater;
import in.nitjsr.ojass19.Modals.TitleCreater1;
import in.nitjsr.ojass19.Modals.TitleParent;
import in.nitjsr.ojass19.R;

import static in.nitjsr.ojass19.Utils.Constants.FIREBASE_REF_NOTIFICATIONS;

public class NotificationFragment extends Fragment implements View.OnClickListener {
    RecyclerView recyclerView;
    FAQAdapter adapter;
    DatabaseReference ref;
    public static ArrayList<FaqModel> data;
    ProgressDialog p;
    Spinner spinner;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_notification,container,false);
        spinner = view.findViewById(R.id.spinner_feed);

        recyclerView=view.findViewById(R.id.myRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        data=new ArrayList<>();

        p=new ProgressDialog(getContext());

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView)adapterView.getChildAt(0)).setTextColor(Color.WHITE);
                onItemSelect();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        return view;
    }
    public void onItemSelect()
    {
        p.setMessage("Loading Feed....");
        p.setCancelable(true);
        p.show();
        ref= FirebaseDatabase.getInstance().getReference().child(FIREBASE_REF_NOTIFICATIONS).child(spinner.getSelectedItem().toString());
        ref.keepSynced(true);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                p.dismiss();
                data.clear();
                for(DataSnapshot ds: dataSnapshot.getChildren())
                {
                    FaqModel q = ds.getValue(FaqModel.class);
                    data.add(q);
                    //Toast.makeText(NotificationsActivity.this,"Q"+q.getQuestion()+"\nA:"+q.getAns(),Toast.LENGTH_SHORT).show();

                }

                adapter = new FAQAdapter(getContext(),initData());
                adapter.setParentClickableViewAnimationDefaultDuration();
                adapter.setParentAndIconExpandOnClick(true);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }
    private List<ParentObject> initData() {
        TitleCreater1 titleCreater= new TitleCreater1(getContext());
        //titleCreater= TitleCreater.get(this);
        List<TitleParent> titles= TitleCreater1._titleParents;
        List<ParentObject> parentObject = new ArrayList<>();
        //Toast.makeText(FeedActivity.this,"Title:"+titles.size(),Toast.LENGTH_SHORT).show();
        int i=data.size()-1;
        for(TitleParent title:titles)
        {
            List<Object> childList = new ArrayList<>();
            //childList.add(new TitleChild(("It is LSE web style to title a page of FAQs 'Frequently asked questions (FAQs)'. While the abbreviation is in quite common usage this ensures that there can be no mistaking what they are")));
            childList.add(new TitleChild(data.get(i--).getAns()));
            title.setChildObjectList(childList);
            parentObject.add(title);
        }
        return parentObject;
    }
    @Override
    public void onClick(View view) {
    }
}
