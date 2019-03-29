package in.nitjsr.ojass19.Utils;

import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;

import in.nitjsr.ojass19.R;

public class TitleParentViewHolder extends ParentViewHolder {

    public TextView _textView;
    public TitleParentViewHolder(View itemView) {
        super(itemView);
        _textView=(TextView)itemView.findViewById(R.id.parentTitle);
    }
}

