package com.example.seniorproject2021;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class ActivityViewAppointmentsProv extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_view_appointments_prov);

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
}