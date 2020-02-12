package com.project.nasapictureapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class FullScreenAstroImageAdapter extends PagerAdapter {

    List<AstroModalClass> imageDetailsList;
    Context context;
    LayoutInflater layoutInflater;

    public FullScreenAstroImageAdapter(Context context, List<AstroModalClass> imageDetailsList) {
        this.context = context; //the context of our application
        this.imageDetailsList = imageDetailsList; //for the other details and images
    }

    @Override
    public int getCount() {
        return imageDetailsList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.full_screen_astro_image_layout,null);

        //getting the image view
        ImageView imageAstroView = v.findViewById(R.id.astro_full_screen_image);

        //showing the image
        Glide.with(context).load(imageDetailsList.get(position).getImageUrl()).apply(new RequestOptions().centerInside())
                .into(imageAstroView);

        ViewPager vp = (ViewPager) container;
        vp.addView(v,0);
        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);

        ViewPager viewPager = (ViewPager) container;
        View v = (View) object;
        viewPager.removeView(v);
    }
}
