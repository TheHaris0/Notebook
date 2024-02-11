package com.example.easytutonotes;

import androidx.appcompat.app.AppCompatActivity;
import io.realm.Realm;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class AddNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        EditText titleInput = findViewById(R.id.titleinput);
        EditText descriptionInput = findViewById(R.id.descriptioninput);
        MaterialButton saveBtn = findViewById(R.id.savebtn);


        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title=titleInput.getText().toString();
                if(title.equals("")){
                    Toast.makeText(AddNoteActivity.this,"Title is missing",Toast.LENGTH_LONG).show();
                }
                else {
                    String description = descriptionInput.getText().toString();
                    long createdTime = System.currentTimeMillis();
                    realm.beginTransaction();
                    Note note = realm.createObject(Note.class);
                    note.setTitle(title);
                    note.setDescription(description);
                    note.setCreatedTime(createdTime);
                    realm.commitTransaction();
                    Toast.makeText(getApplicationContext(),"Saved successfully",Toast.LENGTH_SHORT).show();
                    finish();
                }


            }
        });


    }
}