package com.example.easytutonotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import io.realm.Sort;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MaterialButton addNoteBtn;MaterialButton aboutbtn;
         addNoteBtn = findViewById(R.id.addnewnotebtn);
        aboutbtn = findViewById(R.id.aboutbtn);
        aboutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AboutApp.class));
            }
        });
        addNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AddNoteActivity.class));
            }
        });

        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();

        RealmResults<Note> notesList = realm.where(Note.class).sort("createdTime", Sort.DESCENDING).findAll();


        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MyAdapter myAdapter = new MyAdapter(getApplicationContext(),notesList);
        recyclerView.setAdapter(myAdapter);

        notesList.addChangeListener(new RealmChangeListener<RealmResults<Note>>() {
            @Override
            public void onChange(RealmResults<Note> notes) {
                myAdapter.notifyDataSetChanged();
            }
        });




        RelativeLayout RelativeLayout = findViewById(R.id.rlayout);

        // Create a GradientDrawable
        GradientDrawable gradientDrawable = new GradientDrawable();
        // Set the gradient colors
        int[] colors = {Color.parseColor("#3E5151"), Color.parseColor("#DECBA4")};
        gradientDrawable.setColors(colors);

        // Set the gradient type (linear or radial)
        gradientDrawable.setGradientType(GradientDrawable.LINEAR_GRADIENT);

        // Set the gradient orientation (you can choose from TOP_BOTTOM, LEFT_RIGHT, etc.)
        gradientDrawable.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);

        // Set corner radius if you want rounded corners (optional)
        gradientDrawable.setCornerRadius(0f);

        // Set the drawable to the view
        RelativeLayout.setBackground(gradientDrawable);
    }
}