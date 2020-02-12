package com.project.nasapictureapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

//this is the home activity or home screen of our app
//which will show the grid image view of our app
public class FullScreenAstroImageActivity extends AppCompatActivity {

    ArrayList<String> astroPics; //for the image links
    List<AstroModalClass> astroPicDetailsList ; //for the other details list
    ViewPager imageViewPager ;
    int position;
    ScrollView pagerScrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_astro_image);

//        getSupportActionBar().hide(); //hiding thr action bar only for this activity

        imageViewPager = findViewById(R.id.astro_image_view_pager); //getting the view pager
        pagerScrollView = findViewById(R.id.astro_pager_scroll_view); //getting the scroll view

        astroPicDetailsList = new ArrayList<>();  //for the other details list
        if(savedInstanceState == null) {
            Intent intent = getIntent();
            //passing the images and the posiiton for the images clicked
            astroPics = intent.getStringArrayListExtra("IMAGES");
            position = intent.getIntExtra("POSITION",0);
        }

        getJsonPicDetails(); //calling the method for getting the JSON data

        FullScreenAstroImageAdapter fullScreenAstroAdapter = new FullScreenAstroImageAdapter(this, astroPicDetailsList);
        imageViewPager.setAdapter(fullScreenAstroAdapter);
        imageViewPager.setCurrentItem(position,true);

        imageViewPager.setOnTouchListener(new View.OnTouchListener() {

            int dragthreshold = 30;
            int downX;
            int downY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        downX = (int) event.getRawX();
                        downY = (int) event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int distanceX = Math.abs((int) event.getRawX() - downX);
                        int distanceY = Math.abs((int) event.getRawY() - downY);

                        if (distanceY > distanceX && distanceY > dragthreshold) {
                            imageViewPager.getParent().requestDisallowInterceptTouchEvent(false);
                            pagerScrollView.getParent().requestDisallowInterceptTouchEvent(true);
                        } else if (distanceX > distanceY && distanceX > dragthreshold) {
                            imageViewPager.getParent().requestDisallowInterceptTouchEvent(true);
                            pagerScrollView.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        pagerScrollView.getParent().requestDisallowInterceptTouchEvent(false);
                        imageViewPager.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }
                return false;
            }
        });

    }

    //method for getting the pics url from the Json file
    private void getJsonPicDetails() {
        String jsonPicDetails;
//        can generate IOException ,so adding try and catchh
        try {
            InputStream inputStream = getAssets().open("data.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
//            inputStream.read();
            inputStream.read(buffer);
            inputStream.close();

            jsonPicDetails = new String(buffer,"UTF-8");
            JSONArray picDetailsArray = new JSONArray(jsonPicDetails);
            for (int i = 0; i < picDetailsArray.length()  ; i++) {
                JSONObject obj = picDetailsArray.getJSONObject(i);
                //adding the values which have a key "url" which is the image url ,"copyright" for the copyright info,
                // "title" for the image title, etc
                astroPicDetailsList.add(new AstroModalClass(obj.getString("copyright"),obj.getString("url"),obj.getString("date"),obj.getString("explanation")
                        ,obj.getString("media_type"),obj.getString("title"),obj.getString("service_version")));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Log.i("TAG",astroPicsList.get());
    }
}
