package com.project.nasapictureapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

//this is the home activity or home screen of our app
//which will show the grid image view of our app
public class AstroHomeActivity extends AppCompatActivity {

    ArrayList<String> astroPicsList;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    AstroHomeAdapter astroHomeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.astro_home_activity_layout);

//        getActionBar().hide();

        astroPicsList = new ArrayList<>(); //array list for holding all the image urls
        //getting the recycler view
        recyclerView = findViewById(R.id.astro_home_activity_recycler_view);
        //setting the layout manager as the gridd layout manager for getting the grid image view with two coloumns
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        final RecyclerViewAstroImageClickListener imageClickListener = new RecyclerViewAstroImageClickListener() {
            @Override
            public void onAstroImageClick(View view, int position) {
                //open full screen activity with image clicked
                Intent imageClickIntent  = new Intent(getApplicationContext(), FullScreenAstroImageActivity.class);
                imageClickIntent.putExtra("POSITION",position);
                imageClickIntent.putExtra("IMAGES",astroPicsList);
                startActivity(imageClickIntent);
            }
        };

        astroHomeAdapter = new AstroHomeAdapter(this,astroPicsList,imageClickListener);
        recyclerView.setAdapter(astroHomeAdapter);

        //calling the method for pics
        getPicUrlJson();
    }

    //method for getting the pics url from the Json file
    private void getPicUrlJson() {
        String jsonPicUrl;
//        can generate IOException ,so adding try and catchh
        try {
            InputStream inputStream = getAssets().open("data.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            jsonPicUrl = new String(buffer, "UTF-8");
            JSONArray picArray = new JSONArray(jsonPicUrl);
            for (int i = 0; i < picArray.length(); i++) {
                JSONObject obj = picArray.getJSONObject(i);
                //adding the values which have a key "url" which is the image url in this case to the array list
                astroPicsList.add(obj.getString("url"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Log.i("TAG",astroPicsList.get());
    }
}
