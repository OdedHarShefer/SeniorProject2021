package com.example.seniorproject2021;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class ActivityViewProviders extends AppCompatActivity implements AdapterView.OnItemClickListener {

    Dal dal;

     GridView gridView;
     ArrayList<Provider> arrProv;
    ProviderAdapter pa;

    TextView headerName;
    ImageView headerImage;
    TextView headerEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_view_providers);

        gridView = findViewById(R.id.gridViewProviders);
        arrProv = new ArrayList<Provider>();

        dal = new Dal(this);
        getProvData();

        pa = new ProviderAdapter(this, R.layout.provider, arrProv);
        gridView.setAdapter(pa);

        gridView.setOnItemClickListener(this);

        //Navigation code:
        NavigationView navigationView = findViewById(R.id.nav_view);
        if (!dal.checkForProvider(dal.getCustomerById(getIntent().getIntExtra("customerId", 0)).getAccountId())) {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.activity_cust_drawer_v2);
        }
        View header = navigationView.getHeaderView(0);
        headerName = header.findViewById(R.id.textViewHeaderName);
        headerImage = header.findViewById(R.id.imageViewHeaderProfilePic);
        headerEmail = header.findViewById(R.id.textViewHeaderEmail);
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
                } else if (item.getItemId() == R.id.nav_switch_accounts) {
                    i = new Intent(getApplicationContext(), ActivityProfilePick.class);
                } else if (item.getItemId() == R.id.nav_logout) {

                }
                DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
                drawerLayout.closeDrawer(GravityCompat.START);
                i.putExtra("customerId", getIntent().getIntExtra("customerId", 0));
                startActivity(i);
                return true;
            }
        });

        headerImage.setImageBitmap(BitmapFactory.decodeByteArray(dal.getCustomerById(getIntent().getIntExtra("customerId", 0)).getImage(), 0, dal.getCustomerById(getIntent().getIntExtra("customerId", 0)).getImage().length));
        headerName.setText(dal.getCustomerById(getIntent().getIntExtra("customerId", 0)).getName());
        headerEmail.setText(dal.getAccount(dal.getCustomerById(getIntent().getIntExtra("customerId", 0)).getAccountId()).getEmail());

    }

    private void getProvData() {
        ArrayList<Contact> arr = dal.getContactsByCustomerId(getIntent().getIntExtra("customerId", 0));
        while (!arr.isEmpty()) {
            arrProv.add(dal.getProviderById(arr.remove(0).getProviderId()));
        }
    }

    public void addProvider(View view) {
        Intent i = new Intent(this, ActivityAddProviders.class);
        i.putExtra("customerId", getIntent().getIntExtra("customerId", 0));
        startActivity(i);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(this, ActivityProvider.class);
        i.putExtra("providerId", pa.getProvider(position).getId());
        i.putExtra("customerId", getIntent().getIntExtra("customerId", 0));
        startActivity(i);
    }
}