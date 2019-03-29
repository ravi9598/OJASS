package in.nitjsr.ojass19.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import in.nitjsr.ojass19.Activity.SubEventsActivity;
import in.nitjsr.ojass19.R;

public class SubEventsAdapter extends RecyclerView.Adapter<SubEventsAdapter.MyHolder> {
    List<String> subEventList;
    Context context;
    public SubEventsAdapter(List<String> subEventList){
        this.subEventList=subEventList;
    }
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_sub_events,viewGroup,false);
        this.context = viewGroup.getContext();
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, final int i) {
        myHolder.ev_name.setText(subEventList.get(i));
        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,SubEventsActivity.class);
                intent.putExtra("event_name",subEventList.get(i));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return subEventList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        private TextView ev_name;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ev_name = itemView.findViewById(R.id.sub_event_name);
        }
    }
}
