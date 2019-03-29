package in.nitjsr.ojass19.Utils;

import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;

import in.nitjsr.ojass19.R;

public class TitleChildViewHolder extends ChildViewHolder {

    public TextView option1;

    public TitleChildViewHolder(View itemView) {
        super(itemView);
        option1=(TextView)itemView.findViewById(R.id.option1);
    }
}

