package com.example.seniorproject2021;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import org.w3c.dom.Text;

public class ActivityProfileProv extends AppCompatActivity {

    Provider prov;

    TextView name;
    TextView profession;
    ImageView image;
    TextView gender;
    TextView misc;
    RatingBar score;

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

        prov = new Provider("Steve", "Lawyer", BitmapFactory.decodeResource(this.getResources(), R.drawable.temp_profile_pic), "Male", "I am a lawyer");
        prov.setScore((float) 3.5);

        InsertData(prov);

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

    private void InsertData(Provider prov) {
        name.setText(prov.getName());
        profession.setText(prov.getProfession());
        image.setImageBitmap(prov.getImage());
        gender.setText(prov.getGender());
        misc.setText(prov.getMisc());
        score.setRating(prov.getScore());
    }

    public void edit(View view) {
        Intent i = new Intent(this, ActivityProfileEditorProv.class);
        startActivity(i);
    }
}