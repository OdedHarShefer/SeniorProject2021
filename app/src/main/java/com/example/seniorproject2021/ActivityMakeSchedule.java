package com.example.seniorproject2021;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class ActivityMakeSchedule extends AppCompatActivity implements CalendarView.OnDateChangeListener {

    Dal dal;

    TextView headerName;
    ImageView headerImage;
    TextView headerEmail;

    CalendarView calendar;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_make_schedule);

        calendar = findViewById(R.id.calendarView);
        calendar.setOnDateChangeListener(this);
        spinner = findViewById(R.id.spinner);

        dal = new Dal(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.calenderTypes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //Navigation code:
        NavigationView navigationView = findViewById(R.id.nav_view);
        if (!dal.checkForCustomer(dal.getProviderById(getIntent().getIntExtra("providerId", 0)).getAccountId())) {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.activity_prov_drawer_v2);
        }
        View header = navigationView.getHeaderView(0);
        headerName = header.findViewById(R.id.textViewHeaderName);
        headerImage = header.findViewById(R.id.imageViewHeaderProfilePic);
        headerEmail = header.findViewById(R.id.textViewHeaderEmail);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent i = new Intent();
                boolean flag = false;
                if (item.getItemId() == R.id.nav_profile_prov) {
                    i = new Intent(getApplicationContext(), ActivityProfileProv.class);
                    flag = true;
                }
                else if (item.getItemId() == R.id.nav_make_schedule) {
                    i = new Intent(getApplicationContext(), ActivityMakeSchedule.class);
                    flag = true;
                }
                else if (item.getItemId() == R.id.nav_view_customers) {
                    i = new Intent(getApplicationContext(), ActivityViewCustomers.class);
                    flag = true;
                }
                else if (item.getItemId() == R.id.nav_switch_accounts) {
                    i = new Intent(getApplicationContext(), ActivityProfilePick.class);
                    flag = true;
                }
                else if (item.getItemId() == R.id.nav_logout) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ActivityMakeSchedule.this);
                    builder.setTitle("Log Out");
                    builder.setMessage("Are you sure you want to log out?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(ActivityMakeSchedule.this, ActivityLogIn.class);
                            startActivity(i);
                        }
                    });
                    builder.setNegativeButton("No", null);
                    builder.show();
                }
                DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
                drawerLayout.closeDrawer(GravityCompat.START);
                if (flag) {
                    i.putExtra("providerId", getIntent().getIntExtra("providerId", 0));
                    startActivity(i);
                }
                return true;
            }
        });

        headerImage.setImageBitmap(BitmapFactory.decodeByteArray(dal.getProviderById(getIntent().getIntExtra("providerId", 0)).getImage(), 0, dal.getProviderById(getIntent().getIntExtra("providerId", 0)).getImage().length));
        headerName.setText(dal.getProviderById(getIntent().getIntExtra("providerId", 0)).getName());
        headerEmail.setText(dal.getAccount(dal.getProviderById(getIntent().getIntExtra("providerId", 0)).getAccountId()).getEmail());

    }

    @Override
    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
        if (spinner.getSelectedItemPosition() == 0) {
            Intent i = new Intent(this, ActivitySchedule.class);
            i.putExtra("year", year);
            i.putExtra("month", month);
            i.putExtra("day", dayOfMonth);
            i.putExtra("providerId", getIntent().getIntExtra("providerId", 0));
            startActivity(i);
        }
        if (spinner.getSelectedItemPosition() == 1){
            Intent i = new Intent(this, ActivityAppointments.class);
            i.putExtra("year", year);
            i.putExtra("month", month);
            i.putExtra("day", dayOfMonth);
            i.putExtra("providerId", getIntent().getIntExtra("providerId", 0));
            startActivity(i);
        }
    }
}