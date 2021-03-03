package com.example.seniorproject2021;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

public class ActivityProvider extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider);
    }

    public void rate(View view) {
        //
        /*final RatingBar ratingBar = new RatingBar(getApplicationContext());
        ratingBar.setRating(0);
        ratingBar.setStepSize(1);
        ratingBar.setMax(5);
        ratingBar.setNumStars(2);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Rate Provider");
        builder.setView(ratingBar);
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("Cancel", null);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();*/
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Rate this provider");
        final View customLayout = getLayoutInflater().inflate(R.layout.rate_dialog, null);
        builder.setView(customLayout);
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                RatingBar ratingBar = findViewById(R.id.ratingBarScore);
                Toast.makeText(ActivityProvider.this, "" + ratingBar.getNumStars(), Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancel", null);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void chat(View view) {
    }

    public void makeAppointment(View view) {
        Intent i = new Intent(this, ActivityMakeAppointment.class);

    }

    public void remove(View view) {
    }
}