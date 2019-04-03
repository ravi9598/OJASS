package in.nitjsr.ojass19.Fragments;

import android.animation.LayoutTransition;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import in.nitjsr.ojass19.Modals.FaqModel;
import in.nitjsr.ojass19.R;

import static in.nitjsr.ojass19.Utils.Constants.FIREBASE_REF_NOTIFICATIONS;

public class NotificationFragment extends Fragment implements View.OnClickListener {

    ListView list;
    DatabaseReference ref;
    ProgressDialog p;
    FaqAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_notification,container,false);

        Picasso.with(getContext()).load(R.mipmap.notifi_bg).fit().into((ImageView)view.findViewById(R.id.iv_feed));

        list= (ListView) view. findViewById(R.id.list);
        adapter =new FaqAdapter(getContext(), 0, new ArrayList<FaqModel>());
        list.setAdapter(adapter);

        p=new ProgressDialog(getContext());

        onItemSelect();
        return view;
    }

    private void onItemSelect() {
        p.setMessage("Loading Feed....");
        p.setCancelable(true);
        p.show();
        ref= FirebaseDatabase.getInstance().getReference().child(FIREBASE_REF_NOTIFICATIONS).child("OJASS");
        ref.keepSynced(true);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                p.dismiss();
                ArrayList<FaqModel> data = new ArrayList<>();
                data.clear();
                for(DataSnapshot ds: dataSnapshot.getChildren())
                {
                    FaqModel q = ds.getValue(FaqModel.class);
                    data.add(q);
                }

                addNotification(data);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void addNotification(ArrayList<FaqModel> data) {
        adapter.clear();
        adapter.addAll(data);
    }

    @Override
    public void onClick(View view) {

    }

    public class FaqAdapter extends ArrayAdapter<FaqModel> {

        public FaqAdapter(@NonNull Context context, int resource, ArrayList<FaqModel> questions) {
            super(context, resource, questions);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if(convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.faq_item,
                        null, false);
            }

            ((TextView)convertView.findViewById(R.id.question)).setText(getItem(position).
                    getQues());
            ((TextView)convertView.findViewById(R.id.answer)).setText(getItem(position).
                    getAns());

            final View faq_view = convertView;
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TextView answer = (TextView)faq_view.findViewById(R.id.answer);
                    LinearLayout faq = (LinearLayout)faq_view.findViewById(R.id.faq);
                    if(answer.getVisibility() == View.GONE){
                        faq.setLayoutTransition(new LayoutTransition());
                        answer.setVisibility(View.VISIBLE);
                        answer.setAlpha(0.0f);
                        answer.animate().alpha(1.0f);
                    }
                    else {
                        faq.setLayoutTransition(null);
                        answer.setVisibility(View.GONE);
                    }
                }
            });

            return convertView;
        }
    }

}
