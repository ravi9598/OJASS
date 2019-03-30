package in.nitjsr.ojass19.Modals;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import in.nitjsr.ojass19.Activity.FAQActivity;

public class TitleCreater {
    public static TitleCreater _titleCreator;

    public static List<TitleParent> _titleParents;

    public TitleCreater(Context context) {
        _titleParents=new ArrayList<>();
        _titleParents.clear();

        for(int i = 0; i< FAQActivity.data.size(); i++)
        {
            _titleParents.add(new TitleParent(FAQActivity.data.get(i).getQues()));

        }
    }

    public static TitleCreater get(Context context)
    {
        if(_titleCreator == null)
            _titleCreator=new TitleCreater(context);
        return _titleCreator;
    }

    public List<TitleParent> getAll() {
        return _titleParents;
    }
}
