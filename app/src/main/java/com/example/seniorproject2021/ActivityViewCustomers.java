package com.example.seniorproject2021;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class ActivityViewCustomers extends AppCompatActivity implements AdapterView.OnItemClickListener {

    GridView gridView;
    ArrayList<Customer> aryCust;
    CustomerAdapter ca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_view_customers);

        gridView = findViewById(R.id.gridViewCustomers);
        aryCust = new ArrayList<Customer>();

        getCustData();

        ca = new CustomerAdapter(this, R.layout.customer, aryCust);
        gridView.setAdapter(ca);

        gridView.setOnItemClickListener(this);

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

    private void getCustData() {
        Customer cust;
        cust = new Customer("Yakov", BitmapFactory.decodeResource(this.getResources(), R.drawable.temp_profile_pic_3), "Male");
        aryCust.add(cust);
        cust = new Customer("Jamila", BitmapFactory.decodeResource(this.getResources(), R.drawable.temp_profile_pic_2), "Female");
        aryCust.add(cust);
        cust = new Customer("Steve", BitmapFactory.decodeResource(this.getResources(), R.drawable.temp_profile_pic), "Male");
        aryCust.add(cust);
        cust = new Customer("Daniel", BitmapFactory.decodeResource(this.getResources(), R.drawable.temp_profile_pic_4), "Male");
        aryCust.add(cust);
    }

    public void addCustomer(View view) {
        Intent i = new Intent(this, ActivityAddCustomer.class);
        startActivity(i);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(this, ActivityCustomer.class);
        //i.putExtra("provider", ca.Customer(position));
        startActivity(i);
    }
}