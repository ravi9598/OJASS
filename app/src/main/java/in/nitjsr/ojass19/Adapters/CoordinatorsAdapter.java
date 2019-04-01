package in.nitjsr.ojass19.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import in.nitjsr.ojass19.Modals.CoordinatorsModel;
import in.nitjsr.ojass19.R;

public class CoordinatorsAdapter extends RecyclerView.Adapter<CoordinatorsAdapter.MyHolder> {
    List<CoordinatorsModel> list;
    Context mCtx;
    public CoordinatorsAdapter(List<CoordinatorsModel> list){
        this.list = list;
    }
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_coordinator,viewGroup,false);
        mCtx = viewGroup.getContext();
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder myHolder, int i) {
        final CoordinatorsModel model = list.get(i);
        myHolder.name.setText(model.getName());
        myHolder.phone.setText(String.valueOf(model.getPhone()));

        myHolder.phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+91"+model.phone));
                mCtx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        TextView name,phone;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.coord_name);
            phone = itemView.findViewById(R.id.coord_phone);
        }
    }
}