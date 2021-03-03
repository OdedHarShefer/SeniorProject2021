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

        getHoursData();

        hourAdapter = new HourAdapter(this, R.layout.hour, arrHours);
        listViewHours.setAdapter(hourAdapter);

        listViewHours.setOnItemClickListener(this);

        Intent i = getIntent();
        textViewDate.setText(textViewDate.getText() + " " + i.getExtras().getInt("day") + "/" + i.getExtras().getInt("month") + 1 + "/" + i.getExtras().getInt("year"));
    }

    private void getHoursData() {
        Hour hour;
        for (int i = 0; i < 24; i++) {
            hour = new Hour(i);
            arrHours.add(hour);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        hourAdapter.changeBackground(view, position);
    }
}