package com.example.inmodroid.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.inmodroid.R;

import java.util.List;

public class PhotoAdapter extends PagerAdapter {
    private Context cxt;
    private List<String> urls;
    private ImageView[] photos;
    private LayoutInflater layoutInflater;


    public PhotoAdapter(Context cxt, List<String> urls) {
        this.cxt = cxt;
        this.urls = urls;
    }

    public PhotoAdapter(Context cxt, List<String> urls, ImageView[] photos, LayoutInflater layoutInflater) {
        this.cxt = cxt;
        this.urls = urls;
        this.photos = photos;
        this.layoutInflater = layoutInflater;
    }

    @Override
    public int getCount() {
        return urls.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) cxt.getSystemService(cxt.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.random_imager_layout, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        Glide
                .with(this.cxt)
                .load(urls.get(position))
                .into(imageView);


        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }
}
