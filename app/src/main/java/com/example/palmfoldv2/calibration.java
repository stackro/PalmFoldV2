package com.example.palmfoldv2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.palmfoldv2.homeFold.getScreenHeight;
import static com.example.palmfoldv2.homeFold.getScreenWidth;

public class calibration extends MainActivity {

    TextView touchesView;
    TextView calibrationInformation;
    ImageView calibrationComplete;
    int touchesCount;
    Button returnHome;
    Vibrator vb;
    userSettings settings;
    List<Float> touchSizes = new ArrayList<Float>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.palm_calibration);


        settings = new userSettings();

         vb = (Vibrator)  getSystemService(Context.VIBRATOR_SERVICE);
         touchSizes = new ArrayList<Float>();

        touchesView = (TextView) findViewById(R.id.touches);
        calibrationInformation =(TextView) findViewById(R.id.calibration_information);
        calibrationComplete = (ImageView) findViewById(R.id.calibration_complete);
        returnHome = (Button) findViewById(R.id.return_home);
        returnHome.setVisibility(View.INVISIBLE);
        calibrationComplete.setVisibility(View.INVISIBLE);
        touchesCount = 0;


        returnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(calibration.this, homeFold.class));
            }
        });


    }


    public boolean onTouchEvent(MotionEvent event) {


        calibrationInformation.setVisibility(View.INVISIBLE);

        if (touchesCount == 10) {
            returnHome.setVisibility(View.VISIBLE);
            calibrationComplete.setVisibility(View.VISIBLE);
            touchesView.setVisibility(View.VISIBLE);

            Float total = sum(touchSizes);
            Float touchesAverage = total/touchSizes.size();

            settings.setPalmSize(touchesAverage);
            touchesView.setText("Palm Size Updated");

        } else if (touchesCount < 10) {

            switch (event.getAction()) {

                case MotionEvent.ACTION_UP:

                    if (event.getSize() > 0.11) {
                        if (getScreenWidth() / 2 < event.getX() && getScreenHeight() / 2 < event.getY()) {

                            touchesCount++;
                            touchesView.setText(String.valueOf(touchesCount));
                            vb.vibrate(50);
                            touchSizes.add(event.getSize());


                        } else if (getScreenWidth() / 2 > event.getX() && getScreenHeight() / 2 < event.getY()) {

                            touchesCount++;
                            touchesView.setText(String.valueOf(touchesCount));
                            vb.vibrate(50);
                            touchSizes.add(event.getSize());

                        }

                    } else {

                        calibrationInformation.setVisibility(View.VISIBLE);

                        calibrationInformation.setText("Make sure your are pressing your palm against the screen");


                    }
                    break;
            }
        }
            return true;
        }


    public static Float sum(List<Float> list) {
        Float sum = 0f;
        for (Float i: list) {
            sum += i;
        }
        return sum;
    }

}


