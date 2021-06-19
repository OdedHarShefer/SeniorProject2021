package com.example.seniorproject2021;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class ActivityMakeAppointments extends AppCompatActivity implements CalendarView.OnDateChangeListener {

    CalendarView calendar;
    TextView textViewPersonInfo;

    Dal dal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_make_appointment);

        textViewPersonInfo = findViewById(R.id.textViewPersonInfo);
        calendar = findViewById(R.id.calendarView);
        calendar.setOnDateChangeListener(this);

        dal = new Dal(this);

        textViewPersonInfo.setText(textViewPersonInfo.getText().toString() + " " + dal.getProviderById(getIntent().getIntExtra("providerId", 0)).getName());;
    }

    @Override
    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
        Intent i = new Intent(this, ActivityMakeAppointmentCust.class);
        i.putExtra("year", year);
        i.putExtra("month", month);
        i.putExtra("day", dayOfMonth);
        i.putExtra("providerId", getIntent().getIntExtra("providerId", 0));
        i.putExtra("customerId", getIntent().getIntExtra("customerId", 0));

        startActivity(i);
    }
}