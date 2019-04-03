package in.nitjsr.ojass19.Activity;

import android.animation.LayoutTransition;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import in.nitjsr.ojass19.Adapters.FAQAdapter;
import in.nitjsr.ojass19.Modals.FaqModel;
import in.nitjsr.ojass19.R;

public class FAQsActivity extends AppCompatActivity {

    private ListView listView;
    private FaqAdapter faqAdapter;
    private ImageView back;

    FAQAdapter adapter;
    DatabaseReference ref;
    ProgressDialog p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqs);

        Picasso.with(this).load(R.mipmap.notifi_bg).fit().into((ImageView)findViewById(R.id.iv_faq));

        ref= FirebaseDatabase.getInstance().getReference().child("Faq");
        ref.keepSynced(true);
        p=new ProgressDialog(this);
        p.setMessage("Loading FAQs....");
        p.setCancelable(false);
        p.show();

        listView = (ListView)findViewById(R.id.list);
        faqAdapter = new FaqAdapter(this, 0, new ArrayList<FaqModel>());
        listView.setAdapter(faqAdapter);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                p.dismiss();
                ArrayList<FaqModel> questions = new ArrayList<>();
                if (dataSnapshot.exists()){
                    try {
                        for(DataSnapshot ds: dataSnapshot.getChildren())
                        {
                            FaqModel q=ds.getValue(FaqModel.class);
                            questions.add(q);
                        }

                        addFAQs(questions);

                    } catch (Exception e){
                        Toast.makeText(FAQsActivity.this, "Oops! Something went wrong",
                                Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void addFAQs(ArrayList<FaqModel> questions){
        faqAdapter.clear();
        faqAdapter.addAll(questions);
    }

    public class FaqAdapter extends ArrayAdapter<FaqModel>{

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
