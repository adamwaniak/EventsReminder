package com.example.adam.eventsreminder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Adam on 16.04.2017.
 */

public class EventDetailsAdapter extends BaseAdapter {


    ArrayList<EventDetailsRow> details;
    Context context;
    int reminderImg;
    int noReminderImg;

    private static class ViewHolder {
        TextView txtTitle;
        TextView txtDescription;
        ImageView isReminderSetImg;
    }

    public  EventDetailsAdapter(Context context, ArrayList<EventDetailsRow> details){
        this.context = context;
        this.details = details;
    }

    @Override
    public int getCount() {
        return details.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View result;
        ViewHolder viewHolder;
        EventDetailsRow row = details.get(position);
        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.event_details_list_row, parent, false);
            viewHolder.txtTitle = (TextView) convertView.findViewById(R.id.titleTextView);
            viewHolder.txtDescription = (TextView) convertView.findViewById(R.id.descriptionTextView);
            viewHolder.isReminderSetImg = (ImageView) convertView.findViewById(R.id.isReminderSet);
            viewHolder.isReminderSetImg.setImageResource(0);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String title = row.getTitle();
        String description = row.getDescription();
        boolean isSet = setReminderImgInAdapter(title,description,viewHolder.isReminderSetImg);
        if (isSet){
            description = "";
        }

        viewHolder.txtTitle.setText(title);
        viewHolder.txtDescription.setText(description);

        // Return the completed view to render on screen
        return convertView;
    }

    private boolean setReminderImgInAdapter(String title, String description, ImageView imageView){
        if(title.equals("Reminder")){
            if(description.equals("1")){
                imageView.setImageResource(R.drawable.alarm);
            }else{
                imageView.setImageResource(R.drawable.no_alarm);
            }
            return true;
        }else{
            imageView.setImageResource(0);
            return false;
        }
    }
}
