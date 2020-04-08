package com.example.palmfoldv2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class userProfile extends MainActivity {

    ImageView userImage;
    ImageView handImage;
    ImageView reachImage;
    TextView userHand;
    TextView userID;
    TextView reachableView;
    Button returnHome;
    userPreferences pref;
    userSettings setings;
    Integer user;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        final userSettings settings = new userSettings();

        userImage =  (ImageView) findViewById(R.id.user_image);
        userID = (TextView) findViewById(R.id.user_id);
        handImage = (ImageView) findViewById(R.id.hand);
        userHand = (TextView) findViewById(R.id.dominant_hand);
        reachImage = (ImageView) findViewById(R.id.reach) ;
        reachableView = (TextView) findViewById(R.id.reachable);
        returnHome = (Button) findViewById(R.id.return_home);


        String id = String.valueOf(settings.getInstance().getId());
        String dominant = String.valueOf(settings.getInstance().getDominant());
        String reachable = settings.getInstance().getReachable();



        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);
        fromtop = AnimationUtils.loadAnimation(this, R.anim.fromtop);

        userImage.setAnimation(fromtop);
        userID.setText(id);
        userID.setAnimation(fromtop);



        if(dominant.equals("left")){

            handImage.setBackgroundResource(R.drawable.holding_phone_left);
            handImage.setAnimation(frombottom);
            userHand.setText("Left");
            userHand.setAnimation(frombottom);

        }else if(dominant.equals("right")){

            handImage.setAnimation(frombottom);
            handImage.setBackgroundResource(R.drawable.holding_phone_right);
            userHand.setText("Right");
            userHand.setAnimation(frombottom);

        }else if(dominant.equals("")){

            handImage.setBackgroundResource(R.drawable.not_set_single);
            handImage.setAnimation(frombottom);
            userHand.setText("Not Set");
            userHand.setAnimation(frombottom);

        }

        if(reachable.equals("min")){

            reachImage.setBackgroundResource(R.drawable.reach_min);
            reachImage.setAnimation(frombottom);
            reachableView.setText("Minimum");
            reachableView.setAnimation(frombottom);

        }else if(reachable.equals("max")) {


            reachImage.setBackgroundResource(R.drawable.reach_max);
            reachImage.setAnimation(frombottom);
            reachableView.setText("Maximum");
            reachableView.setAnimation(frombottom);
        }else{
            reachImage.setBackgroundResource(R.drawable.reach_not_set);
            reachImage.setAnimation(frombottom);
            reachableView.setText("Not Set");
            reachableView.setAnimation(frombottom);

        }

        returnHome.setAnimation(frombottom);



        returnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(userProfile.this, MainActivity.class));

            }
        });

    }
}


/*

//userHand.setText(data);

//String dominant = pref.returnUserData(user, "dominant");
        //settings.setDominant("right");

 //settings.setReachable("max");
        //String reachable = pref.returnUserData(user, "reachable");
        //String reachable = String.valueOf(settings.getPalmSize());

         //pref = new userPreferences(this);



        //user = 1;

        //sets.setDominant("right");

        //String id = pref.returnUserData(user, "id");


<TextView
        android:id="@+id/user_id2"
        android:layout_width="350sp"
        android:layout_height="wrap_content"
        android:gravity="center"
        android:translationY="-100dp"
        android:translationX="150dp"
        android:textSize="80sp"
        android:textColor="@color/textColour"
        app:layout_constraintHorizontal_bias="0.5"
        app:layout_constraintStart_toStartOf="parent"
        style="@style/textShadow"
        app:layout_constraintEnd_toEndOf="@+id/user_image"
        app:layout_constraintTop_toBottomOf="@+id/user_image" />



        //View v = (View) findViewById(R.layout.activity_main);


        //palmTouch p = (palmTouch) findViewById(R.layout.activity_main);

        // activity_main.OnTouchEvent();

        //palmTouch p = new palmTouch();


        //View view = findViewById(R.id.entire_view);
        //view.OnTouchEvent();

        //p.OnTouch(View v);

               //reachableView.setText(reachable);
        //reachableView.setAnimation(frombottom);
 */
