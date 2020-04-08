package com.example.palmfoldv2;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.HashSet;

public class testIconAdapter extends BaseAdapter {


    private Context mContext;
    //public static String sideToFold;

    //private ArrayList<Integer> mSelected = new ArrayList<Integer>();

    private ArrayList<Integer> mSelected = new ArrayList<Integer>();
    private static ArrayList<Integer> foldSelect = new ArrayList<Integer>();

    ////////////////////////////////////////////
    //palmTouch p = new palmTouch();
    //RelativeLayout relativeLayout = new RelativeLayout(mContext);
    //RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);


    //private HashSet<Integer> mSelected = new HashSet<Integer>();

    public static Integer[] outOfReach;


    // Constructor
    public testIconAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return targets.length;
    }

    public ArrayList<Integer> getSelected() { return mSelected; }

    public void addToSelected() {

        for (int x : foldSelect) {
            if(mSelected.contains(x)) {

                //v.setBackgroundColor(Color.parseColor("#FF0000"));
                //setBackgroundColor(Color.parseColor("#FF0000"));
                //p.getGridViewFrom().setBackgroundColor(Color.parseColor("#FF0000"));

                }
            }
        }

    public int getSelectedCount() {
        return mSelected.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public void onItemSelect(AdapterView<?> parent, View v, int pos, long id) {
        Integer position = new Integer(pos);
        if(mSelected.contains(position)) {
            mSelected.remove(position);
            // update view (v) state here
            // eg: remove highlight
            v.setBackgroundColor(Color.parseColor("#00000000"));
        }
        else {
            mSelected.add(position);
            v.setBackgroundColor(Color.parseColor("#FF0000"));

        }
    }

    public void highlightSelection(View v, boolean fold) {

        // Integer position = new Integer(pos);

        //v.setBackgroundColor(Color.parseColor("#FFFFFF"));

        if (Boolean.TRUE.equals(fold)) {

            v.setVisibility(View.INVISIBLE);

        } else if (Boolean.FALSE.equals(fold)) {

            v.setVisibility(View.VISIBLE);

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

        //RelativeLayout layoutParams = new RelativeLayout();

        //MainActivity fold = new MainActivity();
        //String foldSide = MainActivity.getSide();

        //if(palmTouch.getSide().equals("left")){
        //    outOfReach = new Integer[]{ 0,4,5,8,9,10,12,13,14,15};
       // }else if(palmTouch.getSide().equals("right")) {
       //     outOfReach = new Integer[]{3,6,7,9,10,11,12,13,14,15};
       // }else{
       //     outOfReach = new Integer[] {};
      //  }

        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(192, 192));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);

            /////////////////////////////////////////////////////////////
            //imageView.setX(300);
            //imageView.setY(600);
            /////////////////////////////////////////////////////////////
            //for (int element : outOfReach) {
            //    if (element == position) {

              //      imageView.setVisibility(View.INVISIBLE);

                    //imageView.setPadding(20, 20, 20, 20);
                    //imageView.setLayoutParams(new GridView.LayoutParams(400, 400));

                    //layoutParams.leftMargin = 70;
                   // layoutParams.topMargin = 80;

                    //relativeLayout.addView(imageView, layoutParams);

                    //params.leftMargin = 20; //XCOORD
                   // params.topMargin = 200; //YCOORD
                   // imageView.setLayoutParams(params);

                    //imageView.setX(500);
                   // imageView.setX(500);


                    //imageView.setVisibility(View.GONE);
                    //imageView.setClickable(false);
                    //imageView.setEnabled(false);
                    //imageView.setX(400);
                    //imageView.setY(500);


            //}

        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(targets[position]);


        // mThumbIds.setTag(new Integer(position));
        // mThumbIds.setTag(Integer.valueOf(position));
        return imageView;
    }


    // Keep all Images in array
    public Integer[] targets = {
            R.drawable.target_icon,  R.drawable.target_icon,  R.drawable.target_icon,  R.drawable.target_icon,
            R.drawable.target_icon,  R.drawable.target_icon,  R.drawable.target_icon,  R.drawable.target_icon,
            R.drawable.target_icon,  R.drawable.target_icon,  R.drawable.target_icon,  R.drawable.target_icon,
            R.drawable.target_icon,  R.drawable.target_icon,  R.drawable.target_icon,  R.drawable.target_icon,


    };
}
/*

RelativeLayout relativeLayout = new RelativeLayout(this);
// ImageView
ImageView imageView = new ImageView(this);

// Setting layout params to our RelativeLayout
RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(50, 60);

// Setting position of our ImageView
layoutParams.leftMargin = 70;
layoutParams.topMargin = 80;

// Finally Adding the imageView to RelativeLayout and its position
relativeLayout.addView(imageView, layoutParams);



RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
//WRAP_CONTENT param can be FILL_PARENT
params.leftMargin = 206; //XCOORD
params.topMargin = 206; //YCOORD
childView.setLayoutParams(params);












 */