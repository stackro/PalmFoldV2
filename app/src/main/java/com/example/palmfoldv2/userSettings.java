package com.example.palmfoldv2;

import android.content.Context;

public class userSettings {


    private Integer id = 0;
    private Context mContext;
    private static userSettings instance;

    private static String dominant = "";
    private static float palmSize = 0.11f;
    private String reachable = " ";
    private static int direct = 0;
    private static int oneHanded =0;
    private static int palmTouch =0;

    public static userSettings getInstance()
    {
        if (instance == null)
            instance = new userSettings();

        return instance;
    }

    public Integer[] userID = {};


    //public userSettings(Context c) {
    //    mContext = c;
    //}

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getId(){
        return id;
    }

    public void setDominant(String dominant){
         this.dominant = dominant;
    }

    public String getDominant(){
        return dominant;
    }

    public void setPalmSize(Float palmSize){
        this.palmSize = palmSize;
    }

    public Float getPalmSize(){
        return palmSize;
    }

    public void setReachable(String reachable){
        this.reachable = reachable;
    }

    public String getReachable(){
        return reachable;
    }

    public void setDirect(int direct){
        this.direct = direct;
    }

    public int getDirect(){
        return direct;
    }

    public void setOneHanded(int oneHanded){
        this.oneHanded = oneHanded;
    }

    public int getOneHanded(){
        return oneHanded;
    }

    public void setPalmTouch(int palmTouch){
        this.palmTouch = palmTouch;
    }

    public int getPalmTouch(){
        return palmTouch;
    }








}
