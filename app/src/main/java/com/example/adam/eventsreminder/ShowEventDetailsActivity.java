package com.example.adam.eventsreminder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowEventDetailsActivity extends AppCompatActivity {

    ArrayList<EventDetailsRow> eventDetails;
    @BindView(R.id.eventDetailsListView)
    ListView eventDetailsListView;
    @BindView(R.id.detailsEventLayout)
    LinearLayout detailsEventLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_event_details);
        ButterKnife.bind(this);
        Intent sendIntent = getIntent();
        eventDetails = (ArrayList<EventDetailsRow>) sendIntent.getSerializableExtra("eventDetails");
        EventDetailsAdapter adapter = new EventDetailsAdapter(this,eventDetails);
        eventDetailsListView.setAdapter(adapter);
        Utilities.loadBackgroundColorFromPreferences(this,detailsEventLayout);

    }

}
