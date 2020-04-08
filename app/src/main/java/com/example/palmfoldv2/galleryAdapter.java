package com.example.palmfoldv2;

import android.content.Context;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.Arrays;

//import com.example.palmfoldapp.R;

public class galleryAdapter extends BaseAdapter {

    private ArrayList<Integer> ticked = new ArrayList<Integer>();


    private Context mContext;
    //public static String sideToFold;
    public static Integer[] outOfReach;
    ImageView images;
    ImageView ticks;
    View view;

    class ViewHolder {
        ImageView img;
        TextView lbl;
    }

    public ArrayList<Integer> getSelected() { return ticked; }

    TextView text;

    //layout.setOrientation(LinearLayout.VERTICAL);


    // Constructor
    public galleryAdapter(Context c) {
        mContext = c;

    }

    public int getCount() {
        return pictures.length;
    }

    public void onItemSelect(AdapterView<?> parent, View v, int pos, long id) {
        Integer position = new Integer(pos);
        if(ticked.contains(position)) {
            ticked.remove(position);

           // v.setBackgroundColor(Color.parseColor("#00000000"));
            v.findViewWithTag("tick").setVisibility(View.INVISIBLE);
            v.setAlpha(1f);

            //v.getChildAt(3).setBackgroundColor(Color.RED);

        }
        else {
            ticked.add(position);
            //v.setBackgroundColor(Color.parseColor("#FF0000"));
            v.findViewWithTag("tick").setVisibility(View.VISIBLE);
            v.setAlpha(0.5f);
        }
    }

    public Object getItem(int position) {
        return null;
    }

    public void testHighlight(View v, boolean fold) {

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

    public void testHide(View v, boolean hide) {

        if (Boolean.TRUE.equals(hide)) {

            v.setVisibility(View.INVISIBLE);

        } else if (Boolean.FALSE.equals(hide)) {

            v.setVisibility(View.VISIBLE);

        }
    }

    public long getItemId(int position) {
        return 0;
    }

    public Integer[] getIcons() {
        return pictures;
    }


    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {

        //ImageView imageView = new ImageView(this);

        //MainActivity fold = new MainActivity();
        //String foldSide = MainActivity.getSide();


        if (convertView == null) {
            //imageView = new ImageView(mContext);

            view = LayoutInflater.from(mContext).inflate( R.layout.gallery_picture, parent, false);
            images = view.findViewById(R.id.gallery_image);
            ticks = view.findViewById(R.id.checked);

            //imageView.setLayoutParams(new GridView.LayoutParams(192, 192));
            images.setLayoutParams(new android.widget.RelativeLayout.LayoutParams(250, 250));
            ticks.setLayoutParams(new android.widget.RelativeLayout.LayoutParams(100, 100));
            images.setScaleType(ImageView.ScaleType.CENTER_CROP);
            images.setPadding(0, 0, 0, 0);
            //if(position == 1){


            //imageView.setVisibility(View.GONE);
            //imageView.setVisibility(View.INVISIBLE);

        } else {
            //imageView = (ImageView) convertView;

            view = convertView;
            images = convertView.findViewById(R.id.gallery_image);
            ticks = convertView.findViewById(R.id.checked);


        }


        //imageView.setImageResource(mThumbIds[position]);
        //text.setText(mThumbTxt[position]);
        //imageView.setPivotX(5000);

        //images.setImageResource(mThumbIds[position]);


        // mThumbIds.setTag(new Integer(position));
        // mThumbIds.setTag(Integer.valueOf(position));

        //return imageView;



        images.setImageResource(pictures[position]);
        ticks.setImageResource(selectedIcon[position]);
        //images.setTag("photo");
        ticks.setTag("tick");
        ticks.setVisibility(View.INVISIBLE);

        return view;


    }

    // Keep all Images in array
    public static Integer[] pictures = {
            R.drawable.gallery_bass, R.drawable.gallery_beach, R.drawable.gallery_coffee, R.drawable.gallery_concert,
            R.drawable.gallery_dog, R.drawable.gallery_edinburgh, R.drawable.gallery_glasgow, R.drawable.gallery_italy,
            R.drawable.gallery_louvre, R.drawable.gallery_milky, R.drawable.gallery_plane, R.drawable.gallery_tiger,
            R.drawable.gallery_earth, R.drawable.gallery_tropics, R.drawable.gallery_thai, R.drawable.gallery_bus,
    };

    public static Integer[] selectedIcon = {
            R.drawable.tick, R.drawable.tick, R.drawable.tick, R.drawable.tick,
            R.drawable.tick, R.drawable.tick, R.drawable.tick, R.drawable.tick,
            R.drawable.tick, R.drawable.tick, R.drawable.tick, R.drawable.tick,
            R.drawable.tick, R.drawable.tick, R.drawable.tick, R.drawable.tick,
    };

}


