package in.nitjsr.ojass19.Modals;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;

import java.util.List;
import java.util.UUID;

public class TitleParent implements ParentObject {

    private List<Object> mChildrenList;
    private UUID _id;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TitleParent(String title) {
        this.title = title;
        _id= UUID.randomUUID();
    }

    public UUID get_id() {
        return _id;
    }

    public void set_id(UUID _id) {
        this._id = _id;
    }

    public List<Object> getmChildrenList() {
        return mChildrenList;
    }

    public void setmChildrenList(List<Object> mChildrenList) {
        this.mChildrenList = mChildrenList;
    }

    @Override
    public List<Object> getChildObjectList() {
        return mChildrenList;
    }

    @Override
    public void setChildObjectList(List<Object> list) {
        mChildrenList=list;
    }
}

