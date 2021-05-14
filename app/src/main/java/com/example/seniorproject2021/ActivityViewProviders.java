package com.example.seniorproject2021;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class ActivityViewProviders extends AppCompatActivity implements AdapterView.OnItemClickListener {

     GridView gridView;
     ArrayList<Provider> arrProv;
    ProviderAdapter pa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_view_providers);

        gridView = findViewById(R.id.gridViewProviders);
        arrProv = new ArrayList<Provider>();

        getProvData();

        pa = new ProviderAdapter(this, R.layout.provider, arrProv);
        gridView.setAdapter(pa);

        gridView.setOnItemClickListener(this);

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

    private void getProvData() {
        Provider prov;
        prov = new Provider("Yakov", "Lawyer", BitmapFactory.decodeResource(this.getResources(), R.drawable.temp_profile_pic_3), "Male", "I am a lawyer");
        arrProv.add(prov);
        prov = new Provider("Jamila", "Accountant", BitmapFactory.decodeResource(this.getResources(), R.drawable.temp_profile_pic_2), "Female", "I am an accountant");
        arrProv.add(prov);
        prov = new Provider("Steve", "House salesman", BitmapFactory.decodeResource(this.getResources(), R.drawable.temp_profile_pic), "Male", "I am a house salesman");
        arrProv.add(prov);
        prov = new Provider("Daniel", "plumber", BitmapFactory.decodeResource(this.getResources(), R.drawable.temp_profile_pic_4), "Male", "I am a plumber");
        arrProv.add(prov);
    }

    public void addProvider(View view) {
        Intent i = new Intent(this, ActivityAddProviders.class);
        startActivity(i);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(this, ActivityProvider.class);
        //i.putExtra("provider", pa.getProvider(position));
        startActivity(i);
    }
}