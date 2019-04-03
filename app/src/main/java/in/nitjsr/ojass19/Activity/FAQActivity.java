package in.nitjsr.ojass19.Activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import in.nitjsr.ojass19.Adapters.FAQAdapter;
import in.nitjsr.ojass19.Modals.FaqModel;
import in.nitjsr.ojass19.Modals.TitleChild;
import in.nitjsr.ojass19.Modals.TitleCreater;
import in.nitjsr.ojass19.Modals.TitleParent;
import in.nitjsr.ojass19.R;
import in.nitjsr.ojass19.Utils.Utilities;

public class FAQActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FAQAdapter adapter;
    DatabaseReference ref;
    public static ArrayList<FaqModel> data;
    ProgressDialog p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        Picasso.with(this).load(R.mipmap.notifi_bg).fit().into((ImageView)findViewById(R.id.iv_faq));

        recyclerView=(RecyclerView)findViewById(R.id.myRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        data=new ArrayList<>();

        ref= FirebaseDatabase.getInstance().getReference().child("Faq");
        ref.keepSynced(true);
        p=new ProgressDialog(this);
        p.setMessage("Loading FAQs....");
        p.setCancelable(false);
        p.show();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                p.dismiss();
                data.clear();
                if (dataSnapshot.exists()){
                    try {
                        for(DataSnapshot ds: dataSnapshot.getChildren())
                        {
                            FaqModel q=ds.getValue(FaqModel.class);
                            data.add(q);

                        }

                        adapter = new FAQAdapter(FAQActivity.this,initData());
                        adapter.setParentClickableViewAnimationDefaultDuration();
                        adapter.setParentAndIconExpandOnClick(true);
                        recyclerView.setAdapter(adapter);

                    } catch (Exception e){

                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        adapter=new FAQAdapter(FAQActivity.this,initData());

        recyclerView.setAdapter(adapter);

        findViewById(R.id.ib_back_faq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private List<ParentObject> initData() {
        TitleCreater titleCreater= new TitleCreater(FAQActivity.this);
        List<TitleParent> titles= TitleCreater._titleParents;
        List<ParentObject> parentObject = new ArrayList<>();
        int i=0;
        for(TitleParent title:titles)
        {
            List<Object> childList = new ArrayList<>();
            childList.add(new TitleChild(data.get(i++).getAns()));
            title.setChildObjectList(childList);
            parentObject.add(title);
        }
        return parentObject;
    }

}
