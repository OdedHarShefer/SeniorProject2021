package com.example.seniorproject2021;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class ActivityViewCustomers extends AppCompatActivity implements AdapterView.OnItemClickListener {

    Dal dal;

    GridView gridView;
    ArrayList<Customer> aryCust;
    CustomerAdapter ca;

    TextView headerName;
    ImageView headerImage;
    TextView headerEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_view_customers);

        gridView = findViewById(R.id.gridViewCustomers);
        aryCust = new ArrayList<Customer>();

        dal = new Dal(this);
        getCustData();

        ca = new CustomerAdapter(this, R.layout.customer, aryCust);
        gridView.setAdapter(ca);

        gridView.setOnItemClickListener(this);

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
                    AlertDialog.Builder builder = new AlertDialog.Builder(ActivityViewCustomers.this);
                    builder.setTitle("Log Out");
                    builder.setMessage("Are you sure you want to log out?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(ActivityViewCustomers.this, ActivityLogIn.class);
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

    private void getCustData() {
        ArrayList<Contact> arr = dal.getContactsByProviderId(getIntent().getIntExtra("providerId", 0));
        while (!arr.isEmpty())
            aryCust.add(dal.getCustomerById(arr.remove(0).getCustomerId()));
    }

    public void addCustomer(View view) {
        Intent i = new Intent(this, ActivityAddCustomer.class);
        i.putExtra("providerId", getIntent().getIntExtra("providerId", 0));
        startActivity(i);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(this, ActivityCustomer.class);
        i.putExtra("providerId", getIntent().getIntExtra("providerId", 0));
        i.putExtra("customerId", ca.getCustomer(position).getId());
        startActivity(i);
    }
}