package in.nitjsr.ojass19.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import in.nitjsr.ojass19.Activity.DeveloperView;
import in.nitjsr.ojass19.Activity.member_view;
import in.nitjsr.ojass19.Modals.developer;
import in.nitjsr.ojass19.R;

public class DeveloperAdapter extends RecyclerView.Adapter<DeveloperAdapter.MyViewHolder> {

    private Context mContext ;
    private List<developer> mData ;

    public DeveloperAdapter(Context mContext, List<developer> mData) {
        this.mContext = mContext;
        this.mData = mData;

    }

    @Override
    public DeveloperAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.developer_card_format,parent,false);
        return new DeveloperAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DeveloperAdapter.MyViewHolder holder, final int position) {

        Glide.with(mContext)
                .load(mData.get(position).getDeveloperImage())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.placeholder).fitCenter()
                )
                .into(holder.developerImage);

        holder.developerName.setText(mData.get(position).getDeveloperName());
        holder.developerBranch.setText(mData.get(position).getDeveloperBranch());
        holder.developerSession.setText(mData.get(position).getDeveloperSession());
        holder.developerWhatsapp.setText(mData.get(position).getDeveloperWhatsapp());
        holder.developerFacebook.setText(mData.get(position).getDeveloperFacebook());
        holder.developerLinkedn.setText(mData.get(position).getDeveloperLinkedn());
    }
    @Override
    public int getItemCount() {
        return mData.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView developerName,developerBranch , developerSession;
        ImageView developerImage;
        CardView developerCardView ;
        Button developerWhatsapp, developerFacebook ,developerLinkedn ;

        public MyViewHolder(final View itemView) {
            super(itemView);

            developerName = (TextView) itemView.findViewById(R.id.developerName) ;
            developerImage = (ImageView) itemView.findViewById(R.id.developerImage);
            developerCardView = (CardView) itemView.findViewById(R.id.developerCardview);
            developerSession = (TextView)itemView.findViewById(R.id.developerSession);
            developerBranch=(TextView)itemView.findViewById(R.id.developerBranch);
            developerFacebook=(Button)itemView.findViewById(R.id.developerFacebook);
            developerLinkedn=(Button)itemView.findViewById(R.id.developerLinkedn);
            developerWhatsapp=(Button)itemView.findViewById(R.id.developerWhatsapp);

            itemView.setTag(getAdapterPosition());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });

        }
    }
}
