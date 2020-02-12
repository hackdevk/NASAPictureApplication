package com.project.nasapictureapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class FullScreenAstroImageActivity extends AppCompatActivity {

    ArrayList<String> astroPics; //for the image links
    List<AstroModalClass> astroPicDetailsList ; //for the other details list
    ViewPager imageViewPager ;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_astro_image);
//        astroPicDetailsList = new ArrayList<>();

        imageViewPager = findViewById(R.id.astro_image_view_pager); //getting the view pager
        astroPicDetailsList = new ArrayList<>();  //for the other details list
        if(savedInstanceState == null) {
            Intent intent = getIntent();
            //passing the images and the posiiton for the images clicked
            astroPics = intent.getStringArrayListExtra("IMAGES");
            position = intent.getIntExtra("POSITION",0);
        }

        FullScreenAstroImageAdapter fullScreenAstroAdapter = new FullScreenAstroImageAdapter(this, astroPicDetailsList);
        imageViewPager.setAdapter(fullScreenAstroAdapter);
        imageViewPager.setCurrentItem(position,true);

    }


}
