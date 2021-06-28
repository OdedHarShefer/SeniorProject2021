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
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class ActivityProfileProv extends AppCompatActivity {

    Dal dal;
    Provider prov;

    TextView name;
    TextView profession;
    ImageView image;
    TextView gender;
    TextView misc;
    RatingBar score;

    TextView headerName;
    ImageView headerImage;
    TextView headerEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_profile_prov);

        name = findViewById(R.id.textViewName);
        profession = findViewById(R.id.textViewProfession);
        image = findViewById(R.id.imageView);
        gender = findViewById(R.id.textViewGender);
        misc = findViewById(R.id.textViewMisc);
        score = findViewById(R.id.ratingBarScore);
        score.setEnabled(false);

        dal = new Dal(this);
        if (getIntent().getIntExtra("providerId", 0) != 0)
            prov = dal.getProviderById(getIntent().getIntExtra("providerId", 0));
        else
            prov = dal.getProviderByAccountId(getIntent().getIntExtra("accountId", 0));

        InsertData(prov);

        //Navigation code:
        NavigationView navigationView = findViewById(R.id.nav_view);
        if (!dal.checkForCustomer(prov.getAccountId())) {
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(ActivityProfileProv.this);
                    builder.setTitle("Log Out");
                    builder.setMessage("Are you sure you want to log out?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(ActivityProfileProv.this, ActivityLogIn.class);
                            startActivity(i);
                        }
                    });
                    builder.setNegativeButton("No", null);
                    builder.show();
                }
                DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
                drawerLayout.closeDrawer(GravityCompat.START);
                if (flag) {
                    i.putExtra("providerId", prov.getId());
                    startActivity(i);
                }
                return true;
            }
        });

        headerImage.setImageBitmap(BitmapFactory.decodeByteArray(prov.getImage(), 0, prov.getImage().length));
        headerName.setText(prov.getName());
        headerEmail.setText(dal.getAccount(prov.getAccountId()).getEmail());
    }

    private void InsertData(Provider prov) {
        name.setText(prov.getName());
        profession.setText(prov.getProfession());
        image.setImageBitmap(BitmapFactory.decodeByteArray(prov.getImage(), 0, prov.getImage().length));
        gender.setText(prov.getGender());
        misc.setText(prov.getMisc());
        score.setRating((float)prov.getAvgScore());
    }

    public void edit(View view) {
        Intent i = new Intent(this, ActivityProfileEditorProv.class);
        i.putExtra("accountId", prov.getAccountId());
        startActivity(i);
    }
}