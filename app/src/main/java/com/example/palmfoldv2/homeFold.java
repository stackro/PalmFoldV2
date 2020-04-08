package com.example.palmfoldv2;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

public class homeFold extends MainActivity{

    GridView homeView;
    GridView remainingIcons;
    Integer reachabilityAid;
    ImageView rightFold;
    ImageView leftFold;
    boolean foldActive;
    Float palmSize;

    final ImageAdapter appIcons = new ImageAdapter(this);
    final userSettings settings = new userSettings();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreendemo);

        homeView = (GridView) findViewById(R.id.home_icons);
        remainingIcons = (GridView) findViewById(R.id.remaining_icons);
        rightFold = (ImageView) findViewById(R.id.right_fold);
        leftFold = (ImageView) findViewById(R.id.left_fold);


        homeView.setAdapter(appIcons);
        homeView.setVisibility(View.VISIBLE);

        remainingIcons.setAdapter(appIcons);
        remainingIcons.setVisibility(View.INVISIBLE);

        rightFold.setVisibility(View.INVISIBLE);
        leftFold.setVisibility(View.INVISIBLE);

        palmSize = settings.getPalmSize();

        rightFold.setX(-300);
        rightFold.setY(-300);
        leftFold.setX(100);
        leftFold.setY(-300);



        homeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                switch(position) {

                    case 0:
                        startActivity(new Intent(homeFold.this, userProfile.class));
                        break;

                    case 3:
                        startActivity(new Intent(homeFold.this, testResults.class));
                        break;

                    case 5:
                        startActivity(new Intent(homeFold.this, calibration.class));
                        break;


                    case 8:
                        startActivity(new Intent(homeFold.this, gallery.class));
                        break;

                    default: {
                        Intent intent = new Intent(homeFold.this, demoActivity.class);
                        intent.putExtra("image", appIcons.getIcons()[position]);
                        intent.putExtra("title", appIcons.getTitle()[position]);
                        startActivity(intent);
                    }
                }
            }
        });


        if(settings.getInstance().getReachable().equals("max")){
            reachabilityAid = 200;
        } else {
            reachabilityAid = 0;
        }
    }


    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {

            case MotionEvent.ACTION_UP:

                if (event.getSize() > palmSize) {
                    if (getScreenWidth() / 2 < event.getX() && getScreenHeight() / 2 < event.getY()) {


                        Integer [] outOfReach = new Integer[]{3,6,7,9,10,11,12,13,14,15};
                        Integer [] inReach = new Integer[]{0,1,2,4,5,8};

                        if(foldActive)
                        {

                            homeView.animate().x(0).y(0);
                            rightFold.animate().x(-300).y(-300);

                            rightFold.setVisibility(View.INVISIBLE);

                            remainingIcons.setVisibility(View.INVISIBLE);

                            for (int element : outOfReach) {

                                appIcons.highlighSelection(homeView.getChildAt(element), false);

                            }

                            foldActive = false;
                        }
                        else {



                            homeView.animate().x(250).y(500 + reachabilityAid);
                            rightFold.setVisibility(View.VISIBLE);
                            rightFold.animate().x(0).y(200 + reachabilityAid);


                            remainingIcons.setVisibility(View.VISIBLE);
                            remainingIcons.setAlpha(0.2f);


                            for (int element : outOfReach) {

                                appIcons.highlighSelection(homeView.getChildAt(element), true);


                            }

                            for (int element : inReach) {

                                appIcons.hideRemaining(remainingIcons.getChildAt(element), true);


                            }
                            foldActive = true;

                        }


                    } else if (getScreenWidth() / 2 > event.getX() && getScreenHeight() / 2 < event.getY()) {



                        Integer [] outOfReach = new Integer[]{ 0,4,5,8,9,10,12,13,14,15};
                        Integer [] inReach = new Integer[]{1,2,3,6,7,11};

                        if(foldActive)
                        {

                            homeView.animate().x(0).y(0);
                            leftFold.animate().x(100).y(-300);
                            leftFold.animate().alpha(0);


                            remainingIcons.setVisibility(View.INVISIBLE);

                            for (int element : outOfReach) {

                                appIcons.highlighSelection(homeView.getChildAt(element), false);
                            }

                            foldActive = false;

                        }
                        else {

                            homeView.animate().x(-200).y(450 + reachabilityAid);
                            leftFold.setVisibility(View.VISIBLE);
                            leftFold.animate().x(0).y(260 + reachabilityAid);
                            leftFold.animate().alpha(1);

                            remainingIcons.setVisibility(View.VISIBLE);
                            remainingIcons.setAlpha(0.2f);


                            for (int element : outOfReach) {

                                appIcons.highlighSelection(homeView.getChildAt(element), true);
                            }

                            for (int element : inReach) {

                                appIcons.hideRemaining(remainingIcons.getChildAt(element), true);


                            }

                            foldActive = true;

                        }



                    }



                } else {

                    Toast.makeText(this, "Finger " + event.getSize()+ " x: " + event.getX() + " y: " + event.getY(), Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return true;
    }


}


