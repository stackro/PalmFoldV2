package com.example.palmfoldv2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class demoActivity extends AppCompatActivity {

    ImageView selectedImage;
    TextView selectedTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_activity);
        selectedImage = (ImageView) findViewById(R.id.selectedImage); // init a ImageView
        selectedTitle = (TextView) findViewById(R.id.selectedTitle); // init a ImageView
        Intent intent = getIntent(); // get Intent which we set from Previous Activity
        selectedImage.setImageResource(intent.getIntExtra("image", 0)); // get image from Intent and set it in ImageView
        selectedTitle.setText(intent.getStringExtra(("title")));
    }
}

