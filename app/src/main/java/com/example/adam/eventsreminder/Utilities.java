package com.example.adam.eventsreminder;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Adam on 13.04.2017.
 */

public class Utilities {


    public static void loadBackgroundColorFromPreferences(Context context,View view){

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        int color = sharedPreferences.getInt("backgroundColor", 0);
        view.setBackgroundColor(color);

    }
    public static void loadTextColorFromPreferences(Context context, ViewGroup layout){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String theme = sharedPreferences.getString("textColor","white");
        switch (theme){
            case "black":
                changeColorAllChild(layout, Color.BLACK);
                break;
            case "white":
                changeColorAllChild(layout,Color.WHITE);
                break;
            case "blue":
                changeColorAllChild(layout,Color.BLUE);
                break;
            case "yellow":
                changeColorAllChild(layout,Color.YELLOW);
                break;
            case "red":
                changeColorAllChild(layout,Color.RED);
                break;
        }
    }

    private static void changeColorAllChild(ViewGroup layout,int color) {
        int childCount = layout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = layout.getChildAt(i);
            if (view instanceof TextView) {
                ((TextView) view).setTextColor(color);
            }
        }
    }
}
