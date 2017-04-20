package com.example.adam.eventsreminder;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;
import butterknife.OnTextChanged;

public class SettingsActivity extends AppCompatActivity implements  SeekBar.OnSeekBarChangeListener {
    @BindView(R.id.settingsLayout) ConstraintLayout settingsLayout;
    @BindView(R.id.redColorChangeSeekBar) SeekBar redColorSeekBar;
    @BindView(R.id.blueColorChangeSeekBar) SeekBar blueColorSeekBar;
    @BindView(R.id.greenColorChangeSeekBar) SeekBar greenColorSeekBar;
    @BindView(R.id.colorTextSelectSpinner) Spinner colorTextSelectSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        setItems();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Utilities.loadBackgroundColorFromPreferences(this,settingsLayout);
        Utilities.loadTextColorFromPreferences(this,settingsLayout);
    }

    @OnItemSelected(R.id.colorTextSelectSpinner)
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String chosenColor = parent.getItemAtPosition(position).toString();
        if(!chosenColor.equals("No selected")) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = preferences.edit();
            switch (chosenColor) {
                case "White":
                    editor.putString("textColor", "white");
                    break;
                case "Black":
                    editor.putString("textColor", "black");
                    break;
                case "Blue":
                    editor.putString("textColor", "blue");
                    break;
                case "Yellow":
                    editor.putString("textColor", "yellow");
                    break;
                case "Red":
                    editor.putString("textColor", "red");
                    break;
            }
            editor.commit();
            parent.setSelection(0);
            Utilities.loadTextColorFromPreferences(this,settingsLayout);
        }
    }

// SeekBar implemented methods
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        int red = redColorSeekBar.getProgress();
        int blue = blueColorSeekBar.getProgress();
        int green = greenColorSeekBar.getProgress();

        int color = Color.rgb(red,green,blue);

        settingsLayout.setBackgroundColor(color);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        saveBackgroundColor();
    }
    private void setItems(){
        setSpinner();
        redColorSeekBar.setOnSeekBarChangeListener(this);
        blueColorSeekBar.setOnSeekBarChangeListener(this);
        greenColorSeekBar.setOnSeekBarChangeListener(this);
    }
    private void setSpinner(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.color_choice_array,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorTextSelectSpinner.setAdapter(adapter);
    }
    @OnTextChanged(R.id.welcomeTextEditText)
    public void saveWelcomeText(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();
        EditText newWelcomeText = (EditText) findViewById(R.id.welcomeTextEditText);
        editor.putString("welcomeText",newWelcomeText.getText().toString());
        editor.commit();
    }
    private void saveBackgroundColor(){
        int color = Color.TRANSPARENT;
        Drawable background = settingsLayout.getBackground();
        if (background instanceof ColorDrawable)
            color = ((ColorDrawable) background).getColor();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("backgroundColor",color);
        editor.commit();
    }

}

