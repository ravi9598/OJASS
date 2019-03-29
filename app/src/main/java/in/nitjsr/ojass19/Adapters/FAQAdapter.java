package in.nitjsr.ojass19.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentObject;

import java.util.List;

import in.nitjsr.ojass19.Modals.TitleChild;
import in.nitjsr.ojass19.Modals.TitleParent;
import in.nitjsr.ojass19.R;
import in.nitjsr.ojass19.Utils.TitleChildViewHolder;
import in.nitjsr.ojass19.Utils.TitleParentViewHolder;


public class FAQAdapter extends ExpandableRecyclerAdapter<TitleParentViewHolder, TitleChildViewHolder> {

    LayoutInflater inflater;
    Context context;

    public FAQAdapter(Context context, List<ParentObject> parentItemList) {
        super(context, parentItemList);
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public TitleParentViewHolder onCreateParentViewHolder(ViewGroup viewGroup) {
        View view=inflater.inflate(R.layout.faq_parent_layout,viewGroup,false);
        return new TitleParentViewHolder(view);
    }

    @Override
    public TitleChildViewHolder onCreateChildViewHolder(ViewGroup viewGroup) {
        View view=inflater.inflate(R.layout.faq_child_layout,viewGroup,false);
        return new TitleChildViewHolder(view);
    }

    @Override
    public void onBindParentViewHolder(TitleParentViewHolder titleParentViewHolder, int i, Object o) {
        TitleParent title = (TitleParent)o;
        Typeface typeface = Typeface.createFromAsset(context.getAssets(),"textfont.otf");
        titleParentViewHolder._textView.setText(title.getTitle());
        titleParentViewHolder._textView.setTypeface(typeface);

    }

    @Override
    public void onBindChildViewHolder(TitleChildViewHolder titleChildViewHolder, int i, Object o) {
        TitleChild title=(TitleChild)o;
        Typeface typeface = Typeface.createFromAsset(context.getAssets(),"textfont.otf");

        titleChildViewHolder.option1.setText(title.getOption1());
        titleChildViewHolder.option1.setTypeface(typeface);

    }
}
