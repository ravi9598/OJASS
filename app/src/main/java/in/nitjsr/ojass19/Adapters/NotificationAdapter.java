package in.nitjsr.ojass19.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import in.nitjsr.ojass19.Modals.NotificationModal;

import in.nitjsr.ojass19.R;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    private Context context;
    private ArrayList<NotificationModal> notificationModals;

    public NotificationAdapter(Context context, ArrayList<NotificationModal> notificationModals){
        this.context = context;
        this.notificationModals = notificationModals;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_notification, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NotificationModal Noti = notificationModals.get(position);
        holder.tvTitle.setText(Noti.getTitle());
        holder.tvBody.setText(Noti.getBody());
    }

    @Override
    public int getItemCount() {
        return notificationModals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvBody;
        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_noti_title);
            tvBody = itemView.findViewById(R.id.tv_noti_body);
        }
    }
}
