package com.example.seniorproject2021;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.Spinner;

import com.google.android.material.navigation.NavigationView;

public class ActivityMakeSchedule extends AppCompatActivity implements CalendarView.OnDateChangeListener {

    CalendarView calendar;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_make_schedule);

        calendar = findViewById(R.id.calendarView);
        calendar.setOnDateChangeListener(this);
        spinner = findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.calenderTypes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //Navigation code:
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent i = new Intent();
                if (item.getItemId() == R.id.nav_profile_prov) {
                    i = new Intent(getApplicationContext(), ActivityProfileProv.class);
                }
                else if (item.getItemId() == R.id.nav_make_schedule) {
                    i = new Intent(getApplicationContext(), ActivityMakeSchedule.class);
                }
                else if (item.getItemId() == R.id.nav_view_appointments) {
                    i = new Intent(getApplicationContext(), ActivityViewAppointmentsProv.class);
                }
                else if (item.getItemId() == R.id.nav_view_customers) {
                    i = new Intent(getApplicationContext(), ActivityViewCustomers.class);
                }
                else if (item.getItemId() == R.id.nav_view_chats) {
                    i = new Intent(getApplicationContext(), ActivityViewChatsProv.class);
                }
                else if (item.getItemId() == R.id.nav_switch_accounts) {
                    i = new Intent(getApplicationContext(), ActivityProfilePick.class);
                }
                else if (item.getItemId() == R.id.nav_logout) {

                }
                DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
                drawerLayout.closeDrawer(GravityCompat.START);
                startActivity(i);
                return true;
            }
        });
    }

    @Override
    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
        if (spinner.getSelectedItemPosition() == 0) {
            Intent i = new Intent(this, ActivitySchedule.class);
            i.putExtra("year", year);
            i.putExtra("month", month);
            i.putExtra("day", dayOfMonth);
            startActivity(i);
        }
        if (spinner.getSelectedItemPosition() == 1){
            Intent i = new Intent(this, ActivityViewAppointmentsProv.class);
            i.putExtra("year", year);
            i.putExtra("month", month);
            i.putExtra("day", dayOfMonth);
            startActivity(i);
        }
    }
}