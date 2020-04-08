package com.example.palmfoldv2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class oneHanded extends MainActivity {

    Button launchOneHandedTest;
    Button nextTest;
    TextView taskTitle;
    TextView taskDescription;
    TextView taskInstruction;
    GridView oneHandedTestGrid;
    ImageView holdingPhone;
    ImageView touchingPhone;
    Integer instructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.one_handed_test);

        final testIconAdapter adapter = new testIconAdapter(this);
        final userSettings settings = new userSettings();

        oneHandedTestGrid = (GridView) findViewById(R.id.testIcons);
        taskTitle = (TextView) findViewById(R.id.one_handed_title);
        taskDescription = (TextView) findViewById(R.id.task_description);
        taskInstruction = (TextView) findViewById(R.id.task_instructions);
        launchOneHandedTest = (Button) findViewById(R.id.launch_direct_test);
        nextTest = (Button) findViewById(R.id.next_test);
        holdingPhone =  (ImageView) findViewById(R.id.holding_phone);
        touchingPhone = (ImageView) findViewById(R.id.touching_phone);

        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);
        fromtop = AnimationUtils.loadAnimation(this, R.anim.fromtop);


        oneHandedTestGrid.setAdapter(new testIconAdapter(this));
        oneHandedTestGrid.setVisibility(View.INVISIBLE);
        taskDescription.setVisibility(View.VISIBLE);
        taskInstruction.setVisibility(View.INVISIBLE);
        touchingPhone.setVisibility(View.INVISIBLE);
        nextTest.setVisibility(View.GONE);
        //directTouchTestGrid.setAlpha(0.5f);

        instructions = 0;


        //frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);
        //fromtop = AnimationUtils.loadAnimation(this, R.anim.fromtop);


        launchOneHandedTest.setAnimation(frombottom);
        holdingPhone.setAnimation(fromtop);





        oneHandedTestGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                adapter.onItemSelect(arg0, arg1, arg2, arg3);

            }});

        //logo.setAnimation(fromtop);

        launchOneHandedTest.setOnClickListener(new View.OnClickListener() {
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

                    oneHandedTestGrid.setAnimation(fromtop);
                    oneHandedTestGrid.setVisibility(View.VISIBLE);
                    //holdingPhone.setVisibility(View.INVISIBLE);

                    taskTitle.setVisibility(View.INVISIBLE);
                    touchingPhone.setVisibility(View.INVISIBLE);
                    taskInstruction.setVisibility(View.INVISIBLE);
                    launchOneHandedTest.setVisibility(View.INVISIBLE);

                    nextTest.setAnimation(frombottom);
                    nextTest.setVisibility(View.VISIBLE);


                }
            }}
        );
        nextTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer totalSelected = adapter.getSelectedCount();
                Integer lenTestTargets = adapter.getCount();

                Integer totalSelectedPercentage = calculatePercentage(totalSelected,lenTestTargets);

                settings.getInstance().setOneHanded(totalSelectedPercentage);


                Integer backback = settings.getInstance().getOneHanded();



                Toast.makeText(oneHanded.this, String.valueOf(backback), Toast.LENGTH_SHORT).show();



                startActivity(new Intent(oneHanded.this, palmTouch.class));
            }
        });



    }
    public int calculatePercentage(int selected, int len) {
        return selected * 100 / len;
    }


}
