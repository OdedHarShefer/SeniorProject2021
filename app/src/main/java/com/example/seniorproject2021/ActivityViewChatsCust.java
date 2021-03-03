package com.example.seniorproject2021;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class ActivityViewChatsCust extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_view_chats_cust);

        //Navigation code:
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent i = new Intent();
                if (item.getItemId() == R.id.nav_profile_cust) {
                    i = new Intent(getApplicationContext(), ActivityProfileCust.class);
                } else if (item.getItemId() == R.id.nav_make_appointment) {
                    i = new Intent(getApplicationContext(), ActivityMakeAppointment.class);
                } else if (item.getItemId() == R.id.nav_view_appointments) {
                    i = new Intent(getApplicationContext(), ActivityViewAppointmentsCust.class);
                } else if (item.getItemId() == R.id.nav_view_providers) {
                    i = new Intent(getApplicationContext(), ActivityViewProviders.class);
                } else if (item.getItemId() == R.id.nav_view_chats) {
                    i = new Intent(getApplicationContext(), ActivityViewChatsCust.class);
                } else if (item.getItemId() == R.id.nav_logout) {

                }
                DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
                drawerLayout.closeDrawer(GravityCompat.START);
                startActivity(i);
                return true;
            }
        });
    }

    public void newChat(View view) {
        Intent i = new Intent(this, ActivityNewChat.class);
        startActivity(i);
    }
}