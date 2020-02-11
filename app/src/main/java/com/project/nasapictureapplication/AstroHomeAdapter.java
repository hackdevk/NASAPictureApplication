package com.project.nasapictureapplication;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

public class AstroHomeAdapter extends RecyclerView.Adapter<AstroHomeAdapter.PicturesHolder>{

    Context context;
    ArrayList<String> picsList;

    public AstroHomeAdapter(Context context, ArrayList<String> picsList) {
        this.context = context;
        this.picsList = picsList;
    }

    @NonNull
    @Override
    public PicturesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View picsView = LayoutInflater.from(parent.getContext()).inflate(R.layout.astro_pics_grid_layout,parent,false);
        return new PicturesHolder(picsView);
    }

    @Override
    public void onBindViewHolder(@NonNull PicturesHolder holder, int position) {
//        PicturesHolder picturesHolder = holder;
//        String currentImage = picsList.get(position);
        ImageView imageView = holder.imageView;
        final ProgressBar progressBar = holder.progressBar;
        Glide.with(context).load(picsList.get(position))
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }
                }).into(imageView);
    }

    @Override
    public int getItemCount() {
        if (picsList != null)
            return picsList.size();
        else
            return 0;
    }

    public class PicturesHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        ProgressBar progressBar;

        public PicturesHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.astro_pics_grid_imageView);
            progressBar = itemView.findViewById(R.id.astro__pics_grid_progress_bar);
        }
    }
}
