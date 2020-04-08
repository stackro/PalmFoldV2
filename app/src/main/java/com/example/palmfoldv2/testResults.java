package com.example.palmfoldv2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public  class testResults extends Activity  {

    TextView title;
    ImageView directTouch;
    ImageView oneHanded;
    ImageView palmTouch;
    TextView directTouchText;
    TextView oneHandedText;
    TextView palmTouchText;
    ProgressBar directTouchPercentage;
    ProgressBar oneHandedPercentage;
    ProgressBar palmTouchPercentage;
    Button returnHome;
    userPreferences pref;
    userSettings settings;
    Integer user;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_results);


        title = (TextView) findViewById(R.id.test_results_title);

        final userSettings settings = new userSettings();
        //final userPreferences pref = new userPreferences();

        directTouchText = (TextView) findViewById(R.id.direct_touch_text);
        oneHandedText = (TextView) findViewById(R.id.one_handed_text);
        palmTouchText = (TextView) findViewById(R.id.palm_touch_text);

        directTouch = (ImageView) findViewById(R.id.direct_touch);
        oneHanded = (ImageView) findViewById(R.id.one_handed);
        palmTouch = (ImageView) findViewById(R.id.palm_touch);
        directTouchPercentage = (ProgressBar) findViewById(R.id.direct_touch_results);
        oneHandedPercentage = (ProgressBar) findViewById(R.id.one_handed_results);
        palmTouchPercentage = (ProgressBar) findViewById(R.id.palm_touch_results);
        returnHome = (Button) findViewById(R.id.return_home);

        //frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);
       // fromtop = AnimationUtils.loadAnimation(this, R.anim.fromtop);


        //pref = new userPreferences(testResults.this);

        //user = 1;

        //settings = new userSettings(this);



        //String directTouchStored = pref.returnUserData(user, "dominant");
       // //String oneHandedStored = pref.returnUserData(user, "one-handed");
      //  String palmTouchStored = pref.returnUserData(user, "palm");
        //String dominant = pref.returnUserData(user, "dominant");
        // String reachable = pref.returnUserData(user, "reachable");

        directTouchText.setText(String.valueOf(settings.getDirect()) + "%");
        oneHandedText.setText(String.valueOf(settings.getOneHanded()) + "%");
        palmTouchText.setText(String.valueOf(settings.getPalmTouch()) + "%");



        //int dtToInt = Integer.parseInt(directTouchStored);
        //int ohToInt = Integer.parseInt(oneHandedStored);
        //int ptToInt = Integer.parseInt(palmTouchStored);

        int dt = settings.getDirect();
        int oh = settings.getOneHanded();
        int pt = settings.getPalmTouch();

        //title.setText(String.valueOf(pref.returnUserData(user, "palm")));

       directTouchPercentage.setMax(100);
       directTouchPercentage.setProgress(0);
       directTouchPercentage.setProgress(dt);


        oneHandedPercentage.setMax(100);
        oneHandedPercentage.setProgress(0);
        oneHandedPercentage.setProgress(oh);

        palmTouchPercentage.setMax(100);
        palmTouchPercentage.setProgress(0);
        palmTouchPercentage.setProgress(pt);


        //Toast.makeText(testResults.this, String.valueOf(dt), Toast.LENGTH_SHORT).show();

        returnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(testResults.this, MainActivity.class));
            }
        });





        //directTouchPercentage.setProgess(78);

        //directTouchPercentage.setProgress(dt);
        //oneHandedPercentage.setProgress(oh);
       // palmTouchPercentage.setProgress(pt);




       // title.setAnimation(fromtop);
      //  directTouch.setAnimation(fromtop);
      //  oneHanded.setAnimation(fromtop);
       // palmTouch.setAnimation(fromtop);
      //  directTouchPercentage.setAnimation(frombottom);
       // oneHandedPercentage.setAnimation(frombottom);
      //  palmTouchPercentage.setAnimation(frombottom);


    }
}
