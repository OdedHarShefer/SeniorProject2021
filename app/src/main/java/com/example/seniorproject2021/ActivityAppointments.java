package com.example.seniorproject2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ActivityAppointments extends AppCompatActivity implements AdapterView.OnItemClickListener{

    Dal dal;
    String date;
    boolean accountType;

    TextView textViewDate;
    TextView textViewPersonInfo;
    ListView listViewHours;
    ArrayList<Hour> arrHours;
    HourAdapter hourAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);

        textViewDate = findViewById(R.id.textViewDate);
        textViewPersonInfo = findViewById(R.id.textViewPersonInfo);
        listViewHours = findViewById(R.id.listViewHours);
        arrHours = new ArrayList<Hour>();

        dal = new Dal(this);
        date = formatTime();

        textViewDate.setText(textViewDate.getText().toString() + date);

        accountType = getIntent().getIntExtra("providerId", 0) != 0;

        getHourData();

        hourAdapter = new HourAdapter(this, R.layout.hour, arrHours);
        listViewHours.setAdapter(hourAdapter);

        listViewHours.setOnItemClickListener(this);
    }

    private void getHourData() {
        ArrayList<Meeting> arrMeetings;
        if (accountType)
            arrMeetings = dal.getProviderMeetingsByDay(getIntent().getIntExtra("providerId", 0), date);
        else
            arrMeetings = dal.getCustomerMeetingsByDay(getIntent().getIntExtra("customerId", 0), date);
        Hour hour;
        for (int i = 0; i < arrMeetings.size(); i++) {
            hour = new Hour(Integer.parseInt(arrMeetings.get(i).getDate().substring(11)));
            arrHours.add(hour);
        }
    }

    public String formatTime() {
        String st = "";
        int year = getIntent().getIntExtra("year", 0);
        int month = getIntent().getIntExtra("month", 0);
        int day = getIntent().getIntExtra("day", 0);

        if (day < 10)
            st += "0";
        st += day + "/";
        if (month < 10)
            st += "0";
        st += month + "/" + year;

        return st;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (accountType)
            textViewPersonInfo.setText("Meet with: " + dal.getCustomerById(dal.getCustomerIdByMeeting(getIntent().getIntExtra("providerId", 0), date + "/" + hourAdapter.getHour(position).getStringHour())).getName());
        else
            textViewPersonInfo.setText("Meet with: " + dal.getProviderById(dal.getProviderIdByMeeting(getIntent().getIntExtra("customerId", 0), date + "/" + hourAdapter.getHour(position).getStringHour())).getName());
    }
}