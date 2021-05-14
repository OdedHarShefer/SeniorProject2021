package com.example.seniorproject2021;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CalendarView;

import com.google.android.material.navigation.NavigationView;

public class ActivityViewAppointmentsCust extends AppCompatActivity implements CalendarView.OnDateChangeListener {

    CalendarView calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_view_appointments_cust);

        calendar = findViewById(R.id.calendarView);
        calendar.setOnDateChangeListener(this);

        //Navigation code:
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent i = new Intent();
                if (item.getItemId() == R.id.nav_profile_cust) {
                    i = new Intent(getApplicationContext(), ActivityProfileCust.class);
                } else if (item.getItemId() == R.id.nav_view_appointments) {
                    i = new Intent(getApplicationContext(), ActivityViewAppointmentsCust.class);
                } else if (item.getItemId() == R.id.nav_view_providers) {
                    i = new Intent(getApplicationContext(), ActivityViewProviders.class);
                } else if (item.getItemId() == R.id.nav_view_chats) {
                    i = new Intent(getApplicationContext(), ActivityViewChatsCust.class);
                } else if (item.getItemId() == R.id.nav_switch_accounts) {
                    i = new Intent(getApplicationContext(), ActivityProfilePick.class);
                } else if (item.getItemId() == R.id.nav_logout) {

                }
                DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
                drawerLayout.closeDrawer(GravityCompat.START);
                startActivity(i);
                return true;
            }
        });
    }
    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
        Intent i = new Intent(this, ActivityAppointments.class);
        i.putExtra("year", year);
        i.putExtra("month", month);
        i.putExtra("day", dayOfMonth);
        startActivity(i);
    }
}