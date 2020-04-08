package com.example.palmfoldv2;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import static android.content.ContentValues.TAG;

public class userPreferences extends Activity {

    //private Context c;
    //JSONArray userArray;
    private static userPreferences instance;


    //public userPreferences(Context c) {
    //   this.c = c;
    //}

    public static userPreferences getInstance()
    {
        if (instance == null)
            instance = new userPreferences();

        return instance;
    }


    public void setHand(String h, Integer User){
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray userArray = obj.getJSONArray("users");


            for (int i = 0; i < userArray.length(); i++) {
                JSONObject user = userArray.getJSONObject(i);

                if (user.getInt("id") == User) {

                    //String hand = jo_inside.getString("dominant");
                    user.put("dominant",   h);

                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String returnUserData(Integer User, String data){

        String value = null;


        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray userArray = obj.getJSONArray("users");

            for (int i = 0; i < userArray.length(); i++) {
                JSONObject user = userArray.getJSONObject(i);

                if (user.getInt("id") == User) {

                    value = user.getString(data);
                    //jo_inside.put("calib", p + "prefpref");

                    //calib =jo_inside.getString("calib");

                    //tv2.setText(hand);
                }


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return value;


    }


    public void updatePreferences(Integer User, String k, String v){

        ;

        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray userArray = obj.getJSONArray("users");

            for (int i = 0; i < userArray.length(); i++) {
                JSONObject user = userArray.getJSONObject(i);

                if (user.getInt("id") == User) {


                    //user.put(k, v);

                    user.put(k, v);



                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void setPalmSize(Float palmSize, Integer User){
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray userArray = obj.getJSONArray("users");


            for (int i = 0; i < userArray.length(); i++) {
                JSONObject user = userArray.getJSONObject(i);

                if (user.getInt("id") == User) {

                    //String hand = jo_inside.getString("dominant");
                    user.put("palm",   palmSize);

                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setFinger(Float fingerSize, Integer User){
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray userArray = obj.getJSONArray("users");


            for (int i = 0; i < userArray.length(); i++) {
                JSONObject user = userArray.getJSONObject(i);

                if (user.getInt("id") == User) {

                    JSONObject json = new JSONObject();

                    //String hand = jo_inside.getString("dominant");
                    //.put("palm",   fingerSize);


                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.v(TAG, "Finger update failed");
        }
    }



    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("preferences.json");
            //InputStream is = instance.loadJSONFromAsset("preferences.json");
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
