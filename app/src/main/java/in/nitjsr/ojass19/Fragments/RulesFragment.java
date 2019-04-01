package in.nitjsr.ojass19.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
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

public class RulesFragment extends Fragment {
    private LinearLayout rules_layout;
    private BtmNavVisCallback mCallback;
    private TextView rules;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rules,container,false);
        init(view);
        rules_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onCallback();
            }
        });
        getData();
        return view;
    }
    private void init(View view){
        rules_layout = view.findViewById(R.id.rules_layout);
        rules = view.findViewById(R.id.text_rules);
    }
    void getData(){
        for(EventModel em: HomeActivity.data){
            if(em.getName()!=null) {
                if (em.getName().compareToIgnoreCase(SubEventsActivity.event_name) == 0) {
                    String rl = "";
                    for (int i = 0; i < em.getRulesModels().size(); i++) {
                        rl = rl + "<br>" + (i + 1) + ". " + em.getRulesModels().get(i).getText();

                    }
                    rules.setText(Html.fromHtml(rl));
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