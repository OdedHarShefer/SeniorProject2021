package com.example.seniorproject2021;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ActivityCustomer extends AppCompatActivity {

    Dal dal;
    Customer cust;

    ImageView imageView;
    TextView gender;
    TextView note;
    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        imageView = findViewById(R.id.imageView);
        gender = findViewById(R.id.textViewGender);
        note = findViewById(R.id.textViewNotes);
        name = findViewById(R.id.textViewName);

        dal = new Dal(this);
        cust = dal.getCustomerById(getIntent().getIntExtra("customerId", 0));
        insertData();
    }

    private void insertData() {
        imageView.setImageBitmap(BitmapFactory.decodeByteArray(cust.getImage(), 0, cust.getImage().length));
        gender.setText(cust.getGender());
        note.setText(dal.getContact(getIntent().getIntExtra("providerId", 0), getIntent().getIntExtra("customerId", 0)).getNote());
        name.setText(cust.getName());
    }

    public void remove(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Remove customer");
        builder.setMessage("Are you sure you want to remove " + cust.getName() + "?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dal.deleteContact(getIntent().getIntExtra("providerId", 0), getIntent().getIntExtra("customerId", 0));
                Intent i = new Intent(ActivityCustomer.this, ActivityViewCustomers.class);
                i.putExtra("providerId", getIntent().getIntExtra("providerId", 0));
                startActivity(i);
            }
        });
        builder.setNegativeButton("No", null);
        builder.show();
    }

    public void editNote(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Note");
        final EditText input = new EditText(this);
        input.setText(note.getText());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        builder.setView(input);
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                note.setText(input.getText().toString());
                dal.updateContactNote(getIntent().getIntExtra("providerId", 0), getIntent().getIntExtra("customerId", 0), input.getText().toString());
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

}