package com.example.seniorproject2021;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ActivityMakeAppointmentCust extends AppCompatActivity implements AdapterView.OnItemClickListener {

    Dal dal;
    String date;

    TextView textViewDate;
    TextView textViewPersonInfo;
    ListView listViewHours;
    ArrayList<Hour> arrHours;
    ArrayList<Hour> selectedHours;
    HourAdapter hourAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_appointment_cust);

        textViewDate = findViewById(R.id.textViewDate);
        textViewPersonInfo = findViewById(R.id.textViewPersonInfo);
        listViewHours = findViewById(R.id.listViewHours);
        arrHours = new ArrayList<Hour>();
        selectedHours = new ArrayList<>();

        dal = new Dal(this);
        date = formatTime();

        textViewDate.setText(textViewDate.getText().toString() + date);
        textViewPersonInfo.setText(textViewPersonInfo.getText().toString() + " " + dal.getProviderById(getIntent().getIntExtra("providerId", 0)).getName());

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
        ArrayList<Meeting> meetings = dal.getProviderEmptyMeetingsByDay(getIntent().getIntExtra("providerId", 0), date);
        for (Meeting meeting : meetings)
            arrHours.add(new Hour(Integer.parseInt(meeting.getDate().substring(11))));
    }

    public void confirm(View view) {
        for (int i = 0; i < selectedHours.size(); i++)
            dal.updateMeeting(getIntent().getIntExtra("providerId", 0), getIntent().getIntExtra("customerId", 0), date + "/" + selectedHours.get(i).getStringHour());
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectedHours.add(hourAdapter.getHour(position));
    }
}