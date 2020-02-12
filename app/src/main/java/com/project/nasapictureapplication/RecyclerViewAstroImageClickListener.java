package com.project.nasapictureapplication;

import android.view.View;

//Interface for the click listener
//for the clicking of the images in the grid view i.e
//for the opening of the view pager
public interface RecyclerViewAstroImageClickListener {
    void onAstroImageClick(View view, int position);
}
