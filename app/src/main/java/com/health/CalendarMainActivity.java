package com.health;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


public class CalendarMainActivity extends AppCompatActivity {
    CustomCalendarView customCalendarView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_activity_main);

        customCalendarView = (CustomCalendarView) findViewById(R.id.custom_calendar_view);


    }
}





