package in.nitjsr.ojass19.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Vector;

import in.nitjsr.ojass19.Modals.BranchHeadModel;
import in.nitjsr.ojass19.R;

public class BranchHeadAdapter extends RecyclerView.Adapter<BranchHeadAdapter.MyHolder> {
    List<BranchHeadModel> data;
    Context mCtx;
    public BranchHeadAdapter(List<BranchHeadModel> data){
        this.data = data;
    }
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_branch_head,viewGroup,false);
        mCtx = viewGroup.getContext();
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder myHolder, int i) {
        final BranchHeadModel model = data.get(i);
        myHolder.name.setText(model.name);

        myHolder.phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open phonebook
                Log.e("TAG",model.phone);
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+91"+model.phone));
                mCtx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private ImageView phone;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.bh_name);
            phone = itemView.findViewById(R.id.bh_phone);
        }
    }
}