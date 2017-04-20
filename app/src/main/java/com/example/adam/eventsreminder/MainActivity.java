package com.example.adam.eventsreminder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.mainLayout) ConstraintLayout mainLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
        Utilities.loadTextColorFromPreferences(this,mainLayout);
        Utilities.loadBackgroundColorFromPreferences(this,mainLayout);
        setWelcomeText();
    }
    @OnClick(R.id.settingsButton)
    public void startSettingsActivity(){
        startActivity(new Intent(this,SettingsActivity.class));
    }

    @OnClick(R.id.buttonAddNewEvent)
    public void startDataInputActivity(){
        startActivity(new Intent(this,DataInputActivity.class));
    }
    @OnClick(R.id.showEventsButton)
    public void startShowEventsActivity(){
        startActivity(new Intent(this,ShowEventsActivity.class));
    }


    protected void setWelcomeText(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        TextView welcomeTextView = (TextView) findViewById(R.id.welcomeTextView);
        String welcomeText = sharedPreferences.getString("welcomeText",getString(R.string.welcome_text_main_activity_default));
        welcomeTextView.setText(welcomeText);
    }

}
