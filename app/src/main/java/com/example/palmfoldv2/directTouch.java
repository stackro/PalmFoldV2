package com.example.palmfoldv2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class directTouch extends MainActivity{

    Button launchDirectTest;
    Button nextTest;
    TextView taskTitle;
    TextView taskDescription;
    TextView taskInstruction;
    TextView dominantHandSelection;
    TextView dominantHandInformation;
    GridView directTouchTestGrid;
    ImageView holdingPhone;
    ImageView touchingPhone;
    ImageView dominantHand;
    Integer instructions;
    userPreferences pref;
    //userSettings settings;
    final userSettings settings = new userSettings();
    String str;
    String palmSide;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.direct_touch_test);

        final testIconAdapter adapter = new testIconAdapter(this);
       // final userSettings settings = new userSettings();
        //final userPreferences pref = new userPreferences();

        directTouchTestGrid = (GridView) findViewById(R.id.testIcons);
        taskTitle = (TextView) findViewById(R.id.direct_touch_title);
        taskDescription = (TextView) findViewById(R.id.task_description);
        taskInstruction = (TextView) findViewById(R.id.task_instructions);
        dominantHandSelection = (TextView) findViewById(R.id.dominant_hand_selection);
        dominantHandInformation = (TextView) findViewById(R.id.dominant_hand_information);
        launchDirectTest = (Button) findViewById(R.id.launch_direct_test);
        nextTest = (Button) findViewById(R.id.next_test);
        holdingPhone =  (ImageView) findViewById(R.id.holding_phone);
        touchingPhone = (ImageView) findViewById(R.id.touching_phone);
        dominantHand = (ImageView) findViewById(R.id.dominant_hand);



        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);
        fromtop = AnimationUtils.loadAnimation(this, R.anim.fromtop);

        directTouchTestGrid.setAdapter(new testIconAdapter(this));
        directTouchTestGrid.setVisibility(View.INVISIBLE);
        taskDescription.setVisibility(View.VISIBLE);
        taskInstruction.setVisibility(View.INVISIBLE);
        touchingPhone.setVisibility(View.INVISIBLE);
        dominantHandSelection.setVisibility(View.INVISIBLE);
        dominantHand.setVisibility(View.INVISIBLE);
        dominantHandInformation.setVisibility(View.INVISIBLE);
        nextTest.setVisibility(View.GONE);
        //directTouchTestGrid.setAlpha(0.5f);

        instructions = 0;

        //frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);
        //fromtop = AnimationUtils.loadAnimation(this, R.anim.fromtop);


        launchDirectTest.setAnimation(frombottom);
        holdingPhone.setAnimation(fromtop);

        //logo.setAnimation(fromtop);

            directTouchTestGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                    adapter.onItemSelect(arg0, arg1, arg2, arg3);

            }});


        launchDirectTest.setOnClickListener(new View.OnClickListener() {
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

                    directTouchTestGrid.setAnimation(fromtop);
                    directTouchTestGrid.setVisibility(View.VISIBLE);
                    //holdingPhone.setVisibility(View.INVISIBLE);


                    touchingPhone.setVisibility(View.INVISIBLE);
                    taskInstruction.setVisibility(View.INVISIBLE);
                    //launchDirectTest.setVisibility(View.INVISIBLE);
                    taskTitle.setVisibility(View.INVISIBLE);

                    //nextTest.setAnimation(frombottom);
                    //nextTest.setVisibility(View.VISIBLE);

                    instructions = 2;


            } else if(instructions.equals(2)) {

                    Integer totalSelected = adapter.getSelectedCount();
                    Integer lenTestTargets = adapter.getCount();

                    Integer totalSelectedPercentage = calculatePercentage(totalSelected,lenTestTargets);

                    settings.getInstance().setDirect(totalSelectedPercentage);
                    //settings.setOneHanded(totalSelectedPercentage);
                    //settings.setDirect(totalSelected);

                    Integer backback = settings.getInstance().getDirect();

                    //String str = String.valueOf(totalSelectedPercentage);

                    str = String.valueOf(totalSelectedPercentage);
                    String userHand = setPalmSide(adapter.getSelected());

                    //int user = 1;

                    //String testString = pref.updatePreferences(1, "palm", str);
                    // pref.getInstance().updatePreferences(1, "palm", str);


                    //Toast.makeText(directTouch.this, String.valueOf(backback), Toast.LENGTH_SHORT).show();
                    Toast.makeText(directTouch.this, userHand, Toast.LENGTH_SHORT).show();

                    taskTitle.setVisibility(View.VISIBLE);

                    taskTitle.setText("Confirm Hand");

                    setPalmSide(adapter.getSelected());

                    directTouchTestGrid.setVisibility(View.INVISIBLE);
                    dominantHand.setVisibility(View.VISIBLE);
                    dominantHandSelection.setVisibility(View.VISIBLE);
                    dominantHandInformation.setVisibility(View.VISIBLE);
                    //String hand = settings.getDominant();
                    //String handCap = hand.substring(0, 1).toUpperCase() + hand.substring(1);
                    //dominantHandSelection.setText(handCap);

                    //settings.setDominant();

                    String hand = settings.getDominant();


                    if(hand.equals("left")){

                        dominantHand.setBackgroundResource(R.drawable.holding_phone_left);
                        String handCap = hand.substring(0, 1).toUpperCase() + hand.substring(1);
                        dominantHandSelection.setText(handCap);

                    } else if(hand.equals("right")){

                        dominantHand.setBackgroundResource(R.drawable.holding_phone_right);
                        String handCap = hand.substring(0, 1).toUpperCase() + hand.substring(1);
                        dominantHandSelection.setText(handCap);

                    } else if(hand.equals("") ){
                        settings.getInstance().setDominant("left");
                        dominantHand.setBackgroundResource(R.drawable.holding_phone_left);
                        String handCap = settings.getDominant().substring(0, 1).toUpperCase() + settings.getDominant().substring(1);
                        dominantHandSelection.setText(handCap);

                    }



                    //Toast.makeText(directTouch.this, hand, Toast.LENGTH_SHORT).show();

                    nextTest.setAnimation(frombottom);
                    nextTest.setVisibility(View.VISIBLE);

                }
        }}
        );

        dominantHand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(settings.getDominant().equals("left")) {
                    dominantHand.setBackgroundResource(R.drawable.holding_phone_right);
                    dominantHandSelection.setText("Right");
                    settings.setDominant("right");
                }else if(settings.getDominant().equals("right")){
                    dominantHand.setBackgroundResource(R.drawable.holding_phone_left);
                    dominantHandSelection.setText("Left");
                    settings.setDominant("left");

                }
            }
        });

        nextTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //pref = new userPreferences(directTouch.this);

                startActivity(new Intent(directTouch.this, oneHanded.class));
            }
        });
}

    public int calculatePercentage(int selected, int len) {
        return selected * 100 / len;
    }

    public String setPalmSide(ArrayList<Integer> selected){

         //palmSide = " ";

        if(selected.contains(0) && !selected.contains(3)){

            settings.getInstance().setDominant("left");
            //Toast.makeText(directTouch.this, "left", Toast.LENGTH_SHORT).show();

        }else if(selected.contains(3)  && !selected.contains(0)){

            settings.getInstance().setDominant("right");
            //Toast.makeText(directTouch.this, "right", Toast.LENGTH_SHORT).show();

        }else{
            palmSide = "";
        }

        if(selected.contains(0) && selected.contains(1) ||  selected.contains(2) && selected.contains(3) ){

            settings.getInstance().setReachable("min");
        } else{
            settings.getInstance().setReachable("max");
        }



        return palmSide;

    }

}