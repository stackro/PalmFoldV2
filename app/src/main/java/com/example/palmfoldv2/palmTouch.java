package com.example.palmfoldv2;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;

import static com.example.palmfoldv2.homeFold.getScreenHeight;
import static com.example.palmfoldv2.homeFold.getScreenWidth;

public class palmTouch extends MainActivity{

    Button launchPalmTest;
    Button nextTest;
    TextView taskTitle;
    TextView taskDescription;
    TextView taskInstruction;
    static GridView palmTouchTestGrid;
    GridView remainingIcons;
    ImageView holdingPhone;
    ImageView touchingPhone;
    ImageView rightFold;
    ImageView leftFold;
    Integer instructions;
    Integer reachabilityAid;
    //userPreferences pref;
    //userSettings settings;
    String str;
    ////////////
    Button testButton;
    boolean foldActive;
    boolean folded;
    float palmSize;

    AlertDialog.Builder dialogBuilder;
    AlertDialog alertDialog;
    public static String side = "";
    public static Integer animationSide;
    final testIconAdapter adapter = new testIconAdapter(this);



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.palm_touch_test);

        //final testIconAdapter adapter = new testIconAdapter(this);
        final userSettings settings = new userSettings();
        final ConstraintLayout.LayoutParams params;


        //final userPreferences pref = new userPreferences();
        palmTouchTestGrid = (GridView) findViewById(R.id.testIcons);
        remainingIcons = (GridView) findViewById(R.id.remainingIcons);
        taskTitle = (TextView) findViewById(R.id.palm_touch);
        taskDescription = (TextView) findViewById(R.id.task_description);
        taskInstruction = (TextView) findViewById(R.id.task_instructions);
        launchPalmTest = (Button) findViewById(R.id.launch_palm_test);
        nextTest = (Button) findViewById(R.id.next_test);
        holdingPhone =  (ImageView) findViewById(R.id.holding_phone);
        touchingPhone = (ImageView) findViewById(R.id.touching_phone);
        rightFold = (ImageView) findViewById(R.id.right_fold);
        leftFold = (ImageView) findViewById(R.id.left_fold);

        params = (ConstraintLayout.LayoutParams) nextTest.getLayoutParams();


        palmSize = settings.getPalmSize();

        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);
        fromtop = AnimationUtils.loadAnimation(this, R.anim.fromtop);

        //palmTouchTestGrid.setAdapter(new testIconAdapter(this));
        palmTouchTestGrid.setAdapter(adapter);
        ////////////////////////////////////////////////
        remainingIcons.setAdapter(adapter);

        palmTouchTestGrid.setVisibility(View.INVISIBLE);
        remainingIcons.setVisibility(View.INVISIBLE);
        taskDescription.setVisibility(View.VISIBLE);
        taskInstruction.setVisibility(View.INVISIBLE);
        touchingPhone.setVisibility(View.INVISIBLE);
        rightFold.setVisibility(View.INVISIBLE);
        leftFold.setVisibility(View.INVISIBLE);
        nextTest.setVisibility(View.GONE);
        rightFold.setX(-300);
        rightFold.setY(-300);

        leftFold.setX(100);
        leftFold.setY(-300);
        //directTouchTestGrid.setAlpha(0.5f);

        instructions = 0;

        //frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);
       // fromtop = AnimationUtils.loadAnimation(this, R.anim.fromtop);


        launchPalmTest.setAnimation(frombottom);
        holdingPhone.setAnimation(fromtop);

        if(settings.getInstance().getReachable().equals("max")){

            reachabilityAid = 200;
        } else {
            reachabilityAid = 0;
        }

        //logo.setAnimation(fromtop);

        palmTouchTestGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                adapter.onItemSelect(arg0, arg1, arg2, arg3);

            }});


        launchPalmTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(instructions.equals(0)){

                    holdingPhone.setVisibility(View.INVISIBLE);
                    taskDescription.setVisibility(View.INVISIBLE);

                    //nextTest.setAnimation(frombottom);
                    //@android:anim/slide_in_left
                    touchingPhone.setVisibility(View.VISIBLE);
                    taskInstruction.setVisibility(View.VISIBLE);

                    instructions = 1;

                } else if(instructions.equals(1)) {

                    palmTouchTestGrid.setAnimation(fromtop);
                    palmTouchTestGrid.setVisibility(View.VISIBLE);
                    /////////////////////////////////////////////////////

                    //holdingPhone.setVisibility(View.INVISIBLE);


                    touchingPhone.setVisibility(View.INVISIBLE);
                    taskInstruction.setVisibility(View.INVISIBLE);
                    launchPalmTest.setVisibility(View.INVISIBLE);
                    taskTitle.setVisibility(View.INVISIBLE);

                    nextTest.setAnimation(frombottom);
                    nextTest.setVisibility(View.VISIBLE);




                    if(userSettings.getInstance().getDominant().equals("left")){

                        params.horizontalBias = 0.9f;
                        params.width = 500;
                        nextTest.setLayoutParams(params);

                    }else {



                        params.horizontalBias = 0.1f;
                        params.width = 500;
                        nextTest.setLayoutParams(params);
                    }




                }
            }}
            );

            nextTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //pref = new userPreferences(directTouch.this);


                Integer totalSelected = adapter.getSelectedCount();
                Integer lenTestTargets = adapter.getCount();

                Integer totalSelectedPercentage = calculatePercentage(totalSelected,lenTestTargets);

                settings.getInstance().setPalmTouch(totalSelectedPercentage);
                //settings.setOneHanded(totalSelectedPercentage);
                //settings.setDirect(totalSelected);

                Integer backback = settings.getInstance().getPalmTouch();

                //String str = String.valueOf(totalSelectedPercentage);

                str = String.valueOf(totalSelectedPercentage);

                //int user = 1;

                //String testString = pref.updatePreferences(1, "palm", str);
                // pref.getInstance().updatePreferences(1, "palm", str);

                Toast.makeText(palmTouch.this, String.valueOf(backback), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(palmTouch.this, testResults.class));
            }
        });
}

    public void setSide(String s) {
        side = s;
    }

    public static String getSide() {
        return side;
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

                //if (event.getSize() > 0.11) {
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
                           // palmTouchTestGrid.invalidateViews();
                           // palmTouchTestGrid.setAdapter(adapter);
                           // palmTouchTestGrid.setY(0);
                            //palmTouchTestGrid.setX(0);

                            palmTouchTestGrid.animate().x(0).y(0);
                            rightFold.animate().x(-300).y(-300);

                            rightFold.setVisibility(View.INVISIBLE);
                            nextTest.setVisibility(View.VISIBLE);
                            remainingIcons.setVisibility(View.INVISIBLE);

                            for (int element : outOfReach) {

                                adapter.highlightSelection(palmTouchTestGrid.getChildAt(element), false);
                                //conLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));

                            }

                            foldActive = false;
                        }
                        else {

                            //palmTouchTestGrid.invalidateViews();
                           // palmTouchTestGrid.setAdapter(adapter);
                            //palmTouchTestGrid.setY(500);
                            //palmTouchTestGrid.setX(290);

                            palmTouchTestGrid.animate().x(250).y(500 + reachabilityAid);
                            rightFold.setVisibility(View.VISIBLE);
                            rightFold.animate().x(0).y(200 + reachabilityAid);
                            nextTest.setVisibility(View.INVISIBLE);

                            remainingIcons.setVisibility(View.VISIBLE);
                            remainingIcons.setAlpha(0.2f);


                            for (int element : outOfReach) {

                                adapter.highlightSelection(palmTouchTestGrid.getChildAt(element), true);
                                //conLayout.setBackgroundResource(R.drawable.fullfold);

                            }

                            for (int element : inReach) {

                                adapter.hideRemaining(remainingIcons.getChildAt(element), true);
                                //conLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));

                            }
                            foldActive = true;

                        }

                        //setFolded(true);

                        //TextView view = (TextView) findViewById(R.id.textView);
                        //view.setText("Palm Fold");
                        // view.setVisibility(View.VISIBLE);



                        /// ViewGroup.LayoutParams layoutParams=new ViewGroup.LayoutParams(int width, int height);
                        /// layoutParams.setMargins(int left, int top, int right, int bottom);
                        /// imageView.setLayoutParams(layoutParams);


                        //Intent i = new Intent(getApplicationContext(), SingleViewActivity.class);
                        // i.putExtra("id", 0);

                        // startActivity(i);


                        // System.out.println(event.getPressure());
                        // System.out.println(event.getSize());
                        //Toast.makeText(this, "Right Palm " + event.getSize() + " x: " + event.getX() + "y: " + event.getY(), Toast.LENGTH_SHORT).show();
                        //Log.i("TAG", "onLongClick: x = " + event.getX() + ", y = " + event.getY());


                    } else if (getScreenWidth() / 2 > event.getX() && getScreenHeight() / 2 < event.getY()) {

                        //showAlertDialog(R.layout.left_palm_fold, 200, "left");

                        Integer [] inReach = new Integer[]{ 0,4,5,8,9,10,12,13,14,15};
                        Integer [] outOfReach = new Integer[]{1,2,3,6,7,11};

                        if(foldActive)
                        {
                            // palmTouchTestGrid.invalidateViews();
                            // palmTouchTestGrid.setAdapter(adapter);
                            //palmTouchTestGrid.setY(0);
                            //palmTouchTestGrid.setX(0);

                            palmTouchTestGrid.animate().x(0).y(0);
                            leftFold.animate().x(100).y(-300);
                           // leftFold.animate().alpha(0);

                            leftFold.setVisibility(View.INVISIBLE);
                            nextTest.setVisibility(View.VISIBLE);
                            remainingIcons.setVisibility(View.INVISIBLE);

                            for (int element : inReach) {

                                adapter.highlightSelection(palmTouchTestGrid.getChildAt(element), false);
                            }

                            foldActive = false;

                        }
                        else {

                            //palmTouchTestGrid.invalidateViews();
                            // palmTouchTestGrid.setAdapter(adapter);
                            //palmTouchTestGrid.setY(450);
                            //palmTouchTestGrid.setX(-250);

                            palmTouchTestGrid.animate().x(-250).y(500 + reachabilityAid);
                            leftFold.setVisibility(View.VISIBLE);
                            //leftFold.animate().x(-125).y(210 + reachabilityAid);
                            leftFold.animate().alpha(1);
                            leftFold.animate().x(-125f).y(210f + reachabilityAid);
                            nextTest.setVisibility(View.INVISIBLE);

                            //leftFold.setElevation(2);

                            //ViewCompat.SetElevation(View, int)


                            remainingIcons.setVisibility(View.VISIBLE);
                            remainingIcons.setAlpha(0.2f);


                            for (int element : inReach) {

                                adapter.highlightSelection(palmTouchTestGrid.getChildAt(element), true);
                            }

                            for (int element : outOfReach) {

                                adapter.hideRemaining(remainingIcons.getChildAt(element), true);
                                //conLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));

                            }

                            foldActive = true;

                        }


                        //  System.out.println(event.getPressure());
                        //  System.out.println(event.getSize());
                       // Toast.makeText(this, "Left Palm " + event.getSize() + " x: " + event.getX() + "y: " + event.getY(), Toast.LENGTH_SHORT).show();
                        Log.i("TAG", "onLongClick: x = " + event.getX() + ", y = " + event.getY());

                    }

                } else {
                    // System.out.println(event.getPressure());
                    // System.out.println(event.getSize());
                   // Toast.makeText(this, "Finger " + event.getSize()+ " x: " + event.getX() + " y: " + event.getY(), Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return true;
    }


    public int calculatePercentage(int selected, int len) {
        return selected * 100 / len;
    }


}





