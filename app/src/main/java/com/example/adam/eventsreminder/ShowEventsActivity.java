package com.example.adam.eventsreminder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ShowEventsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private final String fileName = "savedEvents";
    String[] titlesNameForEvent = {"Title","Description","Date","Priority","Reminder","Minutes before alarm"};
    ArrayList<ArrayList<EventDetailsRow>> events = new ArrayList<>();

    @BindView(R.id.eventsListView) ListView eventsListView;
    @BindView(R.id.showEventsLayout) LinearLayout showEventsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_events);
        ButterKnife.bind(this);
        loadData(this,fileName);
        setEventsListView();
        Utilities.loadBackgroundColorFromPreferences(this,showEventsLayout);
        Utilities.loadTextColorFromPreferences(this,showEventsLayout);
        eventsListView.setOnItemClickListener(this);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this,ShowEventDetailsActivity.class);
        intent.putExtra("eventDetails", events.get(position));
        startActivity(intent);
    }

    @OnClick(R.id.deleteButton)
    public void deleteData(){
        deleteFile("savedEvents");
    }

    private void loadData(Context context, String fileName) {
        try {
            FileInputStream fis = context.openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            Scanner scanner = new Scanner(isr);

            while (scanner.hasNextLine()) {
                //First line is always empty
                scanner.nextLine();
                ArrayList<EventDetailsRow> event = new ArrayList<>(6);
                for (String keys : titlesNameForEvent) {
                    String line = scanner.nextLine();
                    EventDetailsRow bean = new EventDetailsRow(keys,line);
                    event.add(bean);
                }
                events.add(event);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setEventsListView(){
        LinkedList<String> titles = getTitles();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,titles);
        eventsListView.setAdapter(adapter);

    }

    private LinkedList<String> getTitles(){
        LinkedList<String> titles = new LinkedList<>();
        for(int i = 0;i<events.size();i++){
            titles.add(events.get(i).get(0).getDescription());
        }
        return titles;
    }



}
