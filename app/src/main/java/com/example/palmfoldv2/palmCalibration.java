package com.example.palmfoldv2;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class palmCalibration extends MainActivity {


    GridView calibTargets;
    ArrayList<String> sString = new ArrayList<String>();
    List<String> sNumbers;
    String stringstring;
    String hand;
    String json;
    String calib;
    userPreferences pref;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calibration);


        calibTargets = (GridView) findViewById(R.id.testIcons);
        final calibAdapter adapter = new calibAdapter(this);
        //homeView.setAdapter(new ImageAdapter(this));
        //calibTargets.setAdapter(new calibAdapter(this));
        calibTargets.setAdapter(adapter);

        calibTargets.setVisibility(View.VISIBLE);
        //homeView.setAlpha(0.5f);

        calibTargets.setSelected(true);



        TextView tv1 = (TextView) findViewById(R.id.selected);
        TextView tv2 = (TextView) findViewById(R.id.json);
        //ListView lv1 = (ListView) findViewById(R.id.list_view);

        //tv1.setText(sNumbers);

        //tv1.setText(stringstring);
        //tv1.invalidate();

        pref = new userPreferences();

        try {
            JSONObject obj = new JSONObject(pref.loadJSONFromAsset());
            JSONArray m_jArry = obj.getJSONArray("users");


            //JSONObject hand = m_jArry.getJSONObject(3);

            //String getHand = hand.getString("dominant");


            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);

                if (jo_inside.getInt("id") == 3) {

                    String hand = jo_inside.getString("dominant");
                    String calib =jo_inside.getString("calib");

                    //tv1.setText(hand);
                    tv2.setText(hand);
                }


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }




        calibTargets.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                adapter.onItemSelect(arg0, arg1, arg2, arg3);

                stringstring = "";

                for (Integer i : adapter.getSelected()) {
                    //stringstring.add(String.valueOf(i));
                    stringstring += i + " ";

                    TextView tv1 = (TextView) findViewById(R.id.selected);
                    TextView tv2 = (TextView) findViewById(R.id.json);

                    //tv1.setText(sNumbers);
                    //tv1.setText(null);

                    //setPrefereneces(stringstring);

                    //tv1.setText(stringstring);
                    String data = pref.returnUserData(2, "reachable");
                    tv1.setText(setPrefereneces(stringstring));
                    tv2.setText(data);
                    //tv1.invalidate();
                }
            }
        });
    }





    public String setPrefereneces(String p) {

        //userPreferences pref = new userPreferences();
        //pref = new userPreferences(this);
        pref = new userPreferences();

        TextView tv2 = (TextView) findViewById(R.id.json);


        try {
            JSONObject obj = new JSONObject(pref.loadJSONFromAsset());
            JSONArray m_jArry = obj.getJSONArray("users");


            //JSONObject hand = m_jArry.getJSONObject(3);

            //String getHand = hand.getString("dominant");


            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);

                if (jo_inside.getInt("id") == 3) {

                    String hand = jo_inside.getString("dominant");
                    jo_inside.put("calib", p + "prefpref");

                    calib =jo_inside.getString("calib");

                    tv2.setText(hand);
                }


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return calib;
    }
}


/**
 *
 *
 *
 *
 public String loadJSONFromAsset() {
 String json = null;
 try {
 InputStream is = this.getAssets().open("preferences.json");
 int size = is.available();
 byte[] buffer = new byte[size];
 is.read(buffer);
 is.close();
 json = new String(buffer, "UTF-8");
 } catch (IOException ex) {
 ex.printStackTrace();
 return null;
 }
 return json;
 }
 }
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
