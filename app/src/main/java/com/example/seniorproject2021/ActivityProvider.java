package com.example.seniorproject2021;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityProvider extends AppCompatActivity {

    Dal dal;
    Provider prov;

    ImageView imageView;
    TextView gender;
    TextView profession;
    TextView misc;
    TextView name;
    RatingBar score;
    Button buttonVote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider);

        imageView = findViewById(R.id.imageView);
        gender = findViewById(R.id.textViewGender);
        profession = findViewById(R.id.textViewProfession);
        misc = findViewById(R.id.textViewMisc);
        name = findViewById(R.id.textViewName);
        score = findViewById(R.id.ratingBarScore);
        buttonVote = findViewById(R.id.buttonVote);

        dal = new Dal(this);
        prov = dal.getProviderById(getIntent().getIntExtra("providerId", 0));

        if (dal.checkVoted(getIntent().getIntExtra("providerId", 0), getIntent().getIntExtra("customerId", 0)))
            buttonVote.setClickable(false);

        insertData();
    }

    private void insertData() {
        imageView.setImageBitmap(BitmapFactory.decodeByteArray(prov.getImage(), 0, prov.getImage().length));
        gender.setText(prov.getGender());
        profession.setText(prov.getProfession());
        misc.setText(prov.getMisc());
        name.setText(prov.getName());
        score.setRating((float)prov.getAvgScore());
    }

    public void rate(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Rate this provider");
        final View customLayout = getLayoutInflater().inflate(R.layout.rate_dialog, null);
        builder.setView(customLayout);
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                RatingBar ratingBar = customLayout.findViewById(R.id.ratingBarDialogScore);
                dal.updateProvider(prov.getId(), prov.getName(), prov.getProfession(), prov.getImage(), prov.getGender(), prov.getMisc(), (prov.getScore() + (double)ratingBar.getRating()), (prov.getScoreCount() + 1));
                dal.updateContactVote(prov.getId(), getIntent().getIntExtra("customerId", 0));
                score.setRating((float)prov.getAvgScore());
                buttonVote.setClickable(false);
            }
        });
        builder.setNegativeButton("Cancel", null);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void makeAppointment(View view) {
        Intent i = new Intent(this, ActivityMakeAppointments.class);
        i.putExtra("providerId", getIntent().getIntExtra("providerId", 0));
        i.putExtra("customerId", getIntent().getIntExtra("customerId", 0));
        startActivity(i);
    }

    public void remove(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Remove Provider");
        builder.setMessage("Are you sure you want to remove " + prov.getName() + "?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dal.deleteContact(getIntent().getIntExtra("providerId", 0), getIntent().getIntExtra("customerId", 0));
                Intent i = new Intent(ActivityProvider.this, ActivityViewProviders.class);
                i.putExtra("customerId", getIntent().getIntExtra("customerId", 0));
                startActivity(i);
            }
        });
        builder.setNegativeButton("No", null);
        builder.show();
    }
}