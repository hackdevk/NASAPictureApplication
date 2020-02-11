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
import java.util.List;

//this is the home activity or home screen of our app
//which will show the grid image view of our app
public class AstroHomeActivity extends AppCompatActivity {

    ArrayList<String> astroPicsList;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.astro_home_activity_layout);

        astroPicsList = new ArrayList<>(); //array list for holding all the image urls
        //getting the recycler view
        recyclerView = findViewById(R.id.astro_home_activity_recycler_view);
        //setting the layout manager as the gridd layout manager for getting the grid image view with two coloumns
        layoutManager = new GridLayoutManager(this,2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

    }


    }



