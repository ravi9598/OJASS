package in.nitjsr.ojass19.Adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

import in.nitjsr.ojass19.Fragments.GuruGyanFragment;
import in.nitjsr.ojass19.Fragments.HomeFragment;
import in.nitjsr.ojass19.R;
import in.nitjsr.ojass19.Utils.BlurCallback;
import in.nitjsr.ojass19.Utils.Constants;
import in.nitjsr.ojass19.Utils.Utilities;
import jp.wasabeef.blurry.Blurry;

public class PosterAdapter extends PagerAdapter {

    private GuruGyanFragment context;
    private String[]  clickUrls;
    List<String> imageUrls;
    private BlurCallback mCallback;
    public PosterAdapter(GuruGyanFragment context, List<String> imageUrls, String[] clickUrls){
        this.context = context;
        this.imageUrls = imageUrls;
        this.clickUrls = clickUrls;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        LayoutInflater layoutinflater = (LayoutInflater) container.getContext().getSystemService(container.getContext().LAYOUT_INFLATER_SERVICE);
        View view = layoutinflater.inflate(R.layout.item_poster, container, false);
        final ImageView iv = view.findViewById(R.id.iv_poster);

        // Utilities.setPicassoImage(context,imageUrls.get(position), iv, Constants.RECT_PLACEHOLDER);
        Picasso.with(container.getContext()).load(imageUrls.get(position)).placeholder(R.drawable.placeholder_square).fit().networkPolicy(NetworkPolicy.OFFLINE).into(iv, new Callback() {
            @Override
            public void onSuccess() {
                mCallback = context;
                mCallback.onBlurCallback();
            }

            @Override
            public void onError() {
                Picasso.with(container.getContext()).load(imageUrls.get(position)).placeholder(R.drawable.placeholder_square).fit().into(iv);
                mCallback =  context;
                mCallback.onBlurCallback();
            }
        });
        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        return imageUrls.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}