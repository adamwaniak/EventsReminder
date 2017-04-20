package com.example.adam.eventsreminder;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class DataInputActivity extends AppCompatActivity {
    @BindView(R.id.scrollView) ScrollView scrollView;
    @BindView(R.id.dataInputLayout) LinearLayout dataInputLayout;
    @BindView(R.id.titleEditText) EditText titleEditText;
    @BindView(R.id.descriptionEditText) EditText descriptionEditText;
    @BindView(R.id.datePicker) DatePicker datePicker;
    @BindView(R.id.prioritySeekBar) SeekBar prioritySeekBar;
    @BindView(R.id.reminderToggleButton) ToggleButton reminderToggleButton;
    @BindView(R.id.minutesBeforeEditText) EditText minutesBeforeEditText;
    @BindView(R.id.minutesBeforeTextView) TextView minutesBeforeTextView;

    private final String fileName = "savedEvents";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_input);
        ButterKnife.bind(this);
        minutesBeforeEditText.setEnabled(false);

    }
    private void clearField(){
        titleEditText.setText("");
        descriptionEditText.setText("");
        prioritySeekBar.setProgress(3);
        reminderToggleButton.setChecked(false);
        minutesBeforeEditText.setText("");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Utilities.loadTextColorFromPreferences(this,dataInputLayout);
        Utilities.loadBackgroundColorFromPreferences(this,scrollView);
    }



    @OnCheckedChanged(R.id.reminderToggleButton)
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
        if(isChecked){
            minutesBeforeEditText.setEnabled(true);
        }else{
            minutesBeforeEditText.setEnabled(false);
        }
    }
    @OnClick(R.id.saveButton)
    public void save(){
        try {
            saveData();
            clearField();
        }catch (Exception e){
            e.printStackTrace();
        }


    }


    private void saveData() {

        String title = titleEditText.getText().toString();
        String description = descriptionEditText.getText().toString();
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();
        int priority = prioritySeekBar.getProgress();
        int reminder = (reminderToggleButton.isChecked()) ? 1 : 0;
        String minutesToRemind = "0";
        if(reminder==1&&!minutesBeforeEditText.getText().equals("")) {
            minutesToRemind = minutesBeforeEditText.getText().toString();
        }
        try {
            StringBuilder stringBuilder = new StringBuilder();
            FileOutputStream fileOutputStream = openFileOutput(fileName, Context.MODE_APPEND);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            stringBuilder.append("\n").append(title).append("\n").append(description).append("\n").append(day+"-"+month+"-"+year).append("\n").append(priority).append("\n")
                    .append(reminder).append("\n").append(minutesToRemind).append("\n");
            outputStreamWriter.write(stringBuilder.toString());
            outputStreamWriter.close();
            fileOutputStream.close();
            Toast.makeText(getApplicationContext(),"Data saved",Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
