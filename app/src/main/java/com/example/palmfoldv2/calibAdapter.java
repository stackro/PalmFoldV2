package com.example.palmfoldv2;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

public class calibAdapter extends BaseAdapter {


        private Context mContext;
        //public static String sideToFold;
        private ArrayList<Integer> mSelected = new ArrayList<Integer>();

    // Constructor
        public calibAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return targets.length;
        }

        public ArrayList<Integer> getSelected() {
        return mSelected;
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
                v.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }
            else {
                mSelected.add(position);
                v.setBackgroundColor(Color.parseColor("#FF0000"));
            }
        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {


            ImageView imageView;

            MainActivity fold = new MainActivity();
            //String foldSide = MainActivity.getSide();


            if (convertView == null) {
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(192, 192));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(8, 8, 8, 8);

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


