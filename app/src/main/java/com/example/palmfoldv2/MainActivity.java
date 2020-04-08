package com.example.palmfoldv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button launchTest;
    Button launchDemo;
    //Button calibrate;
    Animation frombottom;
    Animation fromtop;
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final userSettings settings = new userSettings();


        launchTest = (Button) findViewById(R.id.launchTest);
        launchDemo = (Button) findViewById(R.id.launchDemo);

        logo =  (ImageView) findViewById(R.id.logo);

        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);
        fromtop = AnimationUtils.loadAnimation(this, R.anim.fromtop);

        launchTest.setAnimation(frombottom);
        launchDemo.setAnimation(frombottom);

        settings.getInstance().setId(1);

        logo.setAnimation(fromtop);

        launchTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, directTouch.class));
                finish();
            }
        });

        launchDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                startActivity(new Intent(MainActivity.this, homeFold.class));
            }
        });

    }
}
