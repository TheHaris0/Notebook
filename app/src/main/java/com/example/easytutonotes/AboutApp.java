package com.example.easytutonotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import io.realm.Realm;

public class AboutApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();

        setContentView(R.layout.activity_about_app);

        //String appName = getString(R.string.app_name);

// Accessing application description
        String appDescription = getString(R.string.app_description);
        TextView detail=findViewById(R.id.AboutDetail);
        detail.setText(appDescription);
    }
}