package in.nitjsr.ojass19.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class EventsPagerAdaptor extends FragmentStatePagerAdapter {
    private final List<Fragment> mFragList = new ArrayList<>();
    private final List<String> mFragTitle = new ArrayList<>();
    public EventsPagerAdaptor(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return mFragList.get(i);
    }

    @Override
    public int getCount() {
        return mFragList.size();
    }

    public void addFragment(Fragment fragment, String title){
        mFragList.add(fragment);
        mFragTitle.add(title);
    }
}