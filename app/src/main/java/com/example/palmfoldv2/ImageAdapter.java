package com.example.palmfoldv2;

import android.content.Context;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

//import com.example.palmfoldapp.R;

public class ImageAdapter extends BaseAdapter {


    private Context mContext;
    //public static String sideToFold;
    public static Integer[] outOfReach;

    TextView text;

    //layout.setOrientation(LinearLayout.VERTICAL);


    // Constructor
    public ImageAdapter(Context c) {
        mContext = c;

    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public Integer [] getIcons(){
        return mThumbIds;
    }

    public String [] getTitle(){
        return mThumbTxt;
    }

    public void highlighSelection(View v, boolean fold) {

        // Integer position = new Integer(pos);

        //v.setBackgroundColor(Color.parseColor("#FFFFFF"));

        if (Boolean.TRUE.equals(fold)) {

            v.setVisibility(View.INVISIBLE);
            //v.findViewWithTag("tick").setVisibility(View.VISIBLE);

        } else if (Boolean.FALSE.equals(fold)) {

            v.setVisibility(View.VISIBLE);
            // v.findViewWithTag("tick").setVisibility(View.INVISIBLE);

        }
    }

    public void hideRemaining(View v, boolean hide) {

        if (Boolean.TRUE.equals(hide)) {

            v.setVisibility(View.INVISIBLE);

        } else if (Boolean.FALSE.equals(hide)) {

            v.setVisibility(View.VISIBLE);

        }
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {



        ImageView imageView;
        TextView textView;
        View view;




        if (convertView == null) {



            view = LayoutInflater.from(mContext).inflate( R.layout.app_icon, parent, false);
            imageView = view.findViewById(R.id.app_icon);
            textView = view.findViewById(R.id.app_description);

            //imageView.setLayoutParams(new GridView.LayoutParams(192, 192));
            imageView.setLayoutParams(new  android.widget.RelativeLayout.LayoutParams(192, 192));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);

            //imageView.setVisibility(View.GONE);
            //imageView.setVisibility(View.INVISIBLE);

        }
        else
        {
            //imageView = (ImageView) convertView;


            view = convertView;
            imageView = convertView.findViewById(R.id.app_icon);
            textView = convertView.findViewById(R.id.app_description);
        }



        imageView.setImageResource(mThumbIds[position]);
        textView.setText(mThumbTxt[position]);
        textView.setTextColor(Color.parseColor("#FFFFFF"));
        return view;

    }

    // Keep all Images in array
    public  static Integer[] mThumbIds = {
            R.drawable.new_red_square, R.drawable.new_green_triangle, R.drawable.new_purple_rounded, R.drawable.new_blue_circle,
            R.drawable.new_blue_triangle, R.drawable.new_purple_square, R.drawable.new_red_circle, R.drawable.new_green_square,
            R.drawable.new_green_circle, R.drawable.new_red_rounded, R.drawable.new_blue_square, R.drawable.new_purple_circle,
            R.drawable.new_blue_rounded, R.drawable.new_green_rounded, R.drawable.new_purple_triangle, R.drawable.new_red_triangle,

    };


    public static String[] mThumbTxt = {
            "Account", "Background", "Settings", " Results",
            "Instructions", "Calibrate", "Web", " Social",
            "Gallery", "Fitness", "Bank", "Food",
            "Shop", "Music", "Camera", " Clock"
    };

    // public Integer[] leftReach ={ 0,4,5,8,9,10,12,13,14,15};

    //public Integer[] rightReach = {3,6,7,9,10,11,12,13,14,15};
}

