package in.nitjsr.ojass19.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

import in.nitjsr.ojass19.Activity.member_view;
import in.nitjsr.ojass19.Modals.Member;
import in.nitjsr.ojass19.R;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.MyViewHolder> {

    private Context mContext ;
    private List<Member> mData ;
    static  int memberCardPosition ;

    public MemberAdapter(Context mContext, List<Member> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.member_card_format,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        //loading image to Cardview from URI using glide
        Glide.with(mContext)
                .load(mData.get(position).getMemberImage())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.placeholder).fitCenter()
                )
                .into(holder.memberImage);

        holder.memberName.setText(mData.get(position).getMemberName());
        holder.memberDesignation.setText(mData.get(position).getMemberDesignation());
        holder.memberPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = mData.get(position).getMemberPhone();
                String phNum = "tel:" + url;
                Intent myIntent = new Intent(Intent.ACTION_DIAL ,Uri.parse(phNum));
                mContext.startActivity( myIntent ) ;
            }
        });
        holder.memberWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url =mData.get(position).getMemberWhatsapp();
                Uri uri = Uri.parse(url); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView memberName,memberDesignation;
        ImageView memberImage;
        CardView memberCardView ;
        ImageButton memberWhatsapp ,memberPhone;

        public MyViewHolder(final View itemView) {
            super(itemView);

            memberName = (TextView) itemView.findViewById(R.id.memberName) ;
            memberImage = (ImageView) itemView.findViewById(R.id.member_image);
            memberCardView = (CardView) itemView.findViewById(R.id.memberCardview);
            memberDesignation=(TextView)itemView.findViewById(R.id.memberDesignation);
            memberWhatsapp=(ImageButton)itemView.findViewById(R.id.memberWhatsapp);
            memberPhone=(ImageButton)this.itemView.findViewById(R.id.memberPhone);

            itemView.setTag(getAdapterPosition());
        }
    }
}