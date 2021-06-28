package com.example.seniorproject2021;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class ActivityProfileCust extends AppCompatActivity {

    TextView name;
    ImageView image;
    TextView gender;

    TextView headerName;
    ImageView headerImage;
    TextView headerEmail;

    Dal dal;
    Customer cust;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_profile_cust);

        name = findViewById(R.id.textViewName);
        image = findViewById(R.id.imageView);
        gender = findViewById(R.id.textViewGender);



        dal = new Dal(this);
        if (getIntent().getIntExtra("customerId", 0) != 0)
            cust = dal.getCustomerById(getIntent().getIntExtra("customerId", 0));
        else
            cust = dal.getCustomerByAccountId(getIntent().getIntExtra("accountId", 0));

        InsetData(cust);



        //Navigation code:
        final NavigationView navigationView = findViewById(R.id.nav_view);
        if (!dal.checkForProvider(cust.getAccountId())) {
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
                boolean flag = false;
                if (item.getItemId() == R.id.nav_profile_cust) {
                    i = new Intent(getApplicationContext(), ActivityProfileCust.class);
                    flag = true;
                } else if (item.getItemId() == R.id.nav_view_appointments) {
                    i = new Intent(getApplicationContext(), ActivityViewAppointmentsCust.class);
                    flag = true;
                } else if (item.getItemId() == R.id.nav_view_providers) {
                    i = new Intent(getApplicationContext(), ActivityViewProviders.class);
                    flag = true;
                } else if (item.getItemId() == R.id.nav_switch_accounts) {
                    i = new Intent(getApplicationContext(), ActivityProfilePick.class);
                    flag = true;
                } else if (item.getItemId() == R.id.nav_logout) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ActivityProfileCust.this);
                    builder.setTitle("Log Out");
                    builder.setMessage("Are you sure you want to log out?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(ActivityProfileCust.this, ActivityLogIn.class);
                            startActivity(i);
                        }
                    });
                    builder.setNegativeButton("No", null);
                    builder.show();
                }
                DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
                drawerLayout.closeDrawer(GravityCompat.START);
                if (flag) {
                    i.putExtra("customerId", cust.getId());
                    startActivity(i);
                }
                return true;
            }
        });

        headerImage.setImageBitmap(BitmapFactory.decodeByteArray(cust.getImage(), 0, cust.getImage().length));
        headerName.setText(cust.getName());
        headerEmail.setText(dal.getAccount(cust.getAccountId()).getEmail());
    }

    private void InsetData(Customer cust) {
        name.setText(cust.getName());
        image.setImageBitmap(BitmapFactory.decodeByteArray(cust.getImage(), 0, cust.getImage().length));
        gender.setText(cust.getGender());
    }

    public void edit(View view) {
        Intent i = new Intent(this,ActivityProfileEditorCust.class);
        i.putExtra("accountId", cust.getAccountId());
        startActivity(i);
    }
}