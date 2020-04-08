package com.example.palmfoldv2;

import android.content.Intent;
import android.content.res.Resources;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

public class gallery extends MainActivity{

    GridView galleryImages;
    GridView remainingImages;
    Integer reachabilityAid;
    ImageView rightFold;
    ImageView leftFold;
    boolean foldActive;
    Float palmSize;

    final galleryAdapter images = new galleryAdapter(this);
    final userSettings settings = new userSettings();
    //final RelativeLayout relLay = (RelativeLayout) getLayoutInflater().inflate(R.layout.gallery_picture, null);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery);

        galleryImages = (GridView) findViewById(R.id.gallery_images);
        remainingImages = (GridView) findViewById(R.id.remaining_images);
        rightFold = (ImageView) findViewById(R.id.right_fold);
        leftFold = (ImageView) findViewById(R.id.left_fold);
        galleryImages.setAdapter(images);
        //galleryImages.
        remainingImages.setAdapter(images);
        remainingImages.setVisibility(View.INVISIBLE);

        rightFold.setVisibility(View.INVISIBLE);
        leftFold.setVisibility(View.INVISIBLE);

        palmSize = settings.getPalmSize();

        rightFold.setX(-300);
        rightFold.setY(-300);
        leftFold.setX(100);
        leftFold.setY(-300);



        galleryImages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                images.onItemSelect(arg0, arg1, arg2, arg3);

            }});


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

            //case MotionEvent.ACTION_DOWN:

            //Toast.makeText(this, "Touch on screen", Toast.LENGTH_SHORT).show();
            //  showPreFold(R.layout.left_pre_fold, "right");


            case MotionEvent.ACTION_UP:

                if (event.getSize() > palmSize) {
                    if (getScreenWidth() / 2 < event.getX() && getScreenHeight() / 2 < event.getY()) {


                        //showAlertDialog(R.layout.testfold, 200, "right");

                        Integer [] outOfReach = new Integer[]{3,6,7,9,10,11,12,13,14,15};
                        Integer [] inReach = new Integer[]{0,1,2,4,5,8};

                        ConstraintLayout conLayout = (ConstraintLayout) findViewById(R.id.entire_view);

                        //conLayout.setBackgroundResource(R.drawable.fullfold);
                        //conLayout.setBackgroundColor(Color.parseColor("#009999"));
                        //relLayout.setImageResource(R.drawable.ImageName);

                        if(foldActive)
                        {

                            galleryImages.animate().x(0).y(200);
                            rightFold.animate().x(-300).y(-300);

                            rightFold.setVisibility(View.INVISIBLE);

                            remainingImages.setVisibility(View.INVISIBLE);

                            for (int element : outOfReach) {

                                images.testHighlight(galleryImages.getChildAt(element), false);

                            }

                            foldActive = false;
                        }
                        else {

                            //palmTouchTestGrid.invalidateViews();
                            // palmTouchTestGrid.setAdapter(adapter);
                            //palmTouchTestGrid.setY(500);
                            //palmTouchTestGrid.setX(290);

                            galleryImages.animate().x(250).y(500 + reachabilityAid);
                            rightFold.setVisibility(View.VISIBLE);
                            rightFold.animate().x(0).y(200 + reachabilityAid);


                            remainingImages.setVisibility(View.VISIBLE);
                            remainingImages.setAlpha(0.2f);


                            for (int element : outOfReach) {

                                images.testHighlight(galleryImages.getChildAt(element), true);
                                //conLayout.setBackgroundResource(R.drawable.fullfold);

                            }

                            for (int element : inReach) {

                                images.testHide(remainingImages.getChildAt(element), true);
                                //conLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));

                            }
                            foldActive = true;

                        }




                    } else if (getScreenWidth() / 2 > event.getX() && getScreenHeight() / 2 < event.getY()) {

                        //showAlertDialog(R.layout.left_palm_fold, 200, "left");

                        Integer [] outOfReach = new Integer[]{ 0,4,5,8,9,10,12,13,14,15};
                        Integer [] inReach = new Integer[]{1,2,3,6,7,11};

                        if(foldActive)
                        {


                            galleryImages.animate().x(0).y(200);
                            leftFold.animate().x(100).y(-300);
                            leftFold.animate().alpha(0);

                           // leftFold.setVisibility(View.INVISIBLE);

                            remainingImages.setVisibility(View.INVISIBLE);

                            for (int element : outOfReach) {

                                images.testHighlight(galleryImages.getChildAt(element), false);
                            }

                            foldActive = false;

                        }
                        else {

                            //galleryImages.animate().x(-250).y(500 + reachabilityAid);
                            galleryImages.animate().x(-200).y(450 + reachabilityAid);
                            leftFold.setVisibility(View.VISIBLE);
                            leftFold.animate().x(-125).y(210 + reachabilityAid);
                            leftFold.animate().alpha(1);
                            leftFold.animate().x(-125f).y(210f + reachabilityAid);

                            remainingImages.setVisibility(View.VISIBLE);
                            remainingImages.setAlpha(0.2f);


                            for (int element : outOfReach) {

                                images.testHighlight(galleryImages.getChildAt(element), true);
                            }

                            for (int element : inReach) {

                                images.testHide(remainingImages.getChildAt(element), true);
                                //conLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));

                            }

                            foldActive = true;

                        }


                        Log.i("TAG", "onLongClick: x = " + event.getX() + ", y = " + event.getY());

                    }

                } else {

                }
                break;
        }
        return true;
    }

}
