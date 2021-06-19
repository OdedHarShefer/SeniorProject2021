package com.example.seniorproject2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ActivitySchedule extends AppCompatActivity implements AdapterView.OnItemClickListener {

    Dal dal;
    String date;

    TextView textViewDate;
    ListView listViewHours;
    ArrayList<Hour> arrHours;
    HourAdapter hourAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        textViewDate = findViewById(R.id.textViewDate);
        listViewHours = findViewById(R.id.listViewHours);
        arrHours = new ArrayList<Hour>();

        dal = new Dal(this);
        date = formatTime();

        textViewDate.setText(textViewDate.getText().toString() + date);

        getHoursData();

        hourAdapter = new HourAdapter(this, R.layout.hour, arrHours);
        listViewHours.setAdapter(hourAdapter);

        listViewHours.setOnItemClickListener(this);
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

    private void getHoursData() {
        Hour hour;
        for (int i = 0; i < 24; i++) {
            hour = new Hour(i);
            if (i < 10) {
                if (dal.checkMeetings(getIntent().getIntExtra("providerId", 0), date + "/0" + i))
                    hour.setSelected(true);
            } else
            if (dal.checkMeetings(getIntent().getIntExtra("providerId", 0), date + "/" + i))
                hour.setSelected(true);
            arrHours.add(hour);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        hourAdapter.changeBackground(view, position);
    }

    public void confirm(View view) {
        dal.removeMeetings(getIntent().getIntExtra("providerId", 0), date);
        for (int i = 0; i < arrHours.size(); i++)
            if (arrHours.get(i).isSelected())
                    dal.addMeeting(getIntent().getIntExtra("providerId", 0), 0, date + "/" + arrHours.get(i).getStringHour());
    }
}