package com.project.nasapictureapplication;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

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

        final ProgressBar astroImageProgressBar =v.findViewById(R.id.astro_progress_bar);
        //getting the text view fileds o the layout for setting them with the data received from the JSON data
        TextView astroImageDescription = v.findViewById(R.id.astro_image_description);
        TextView astroImageTitle = v.findViewById(R.id.astro_full_image_title);
        TextView astroImageDate = v.findViewById(R.id.astro_image_date);
        TextView astroImageCopyright = v.findViewById(R.id.astro_image_copyright);
        //getting the image view
        ImageView imageAstroView = v.findViewById(R.id.astro_full_screen_image);

        //showing the image
        Glide.with(context).load(imageDetailsList.get(position).getImageUrl()).apply(new RequestOptions().centerInside())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        astroImageProgressBar.setVisibility(View.GONE);
                        return false;
                    }
                }) .into(imageAstroView);
        //adding the meta details
        astroImageDescription.setText(imageDetailsList.get(position).getExplanation()); //setting the description
        astroImageTitle.setText(imageDetailsList.get(position).getTitle()); //setting the title
        astroImageDate.setText(imageDetailsList.get(position).getDate()); //setting the date
        astroImageCopyright.setText(imageDetailsList.get(position).getCopyright()); //setting the copyright of the image

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
