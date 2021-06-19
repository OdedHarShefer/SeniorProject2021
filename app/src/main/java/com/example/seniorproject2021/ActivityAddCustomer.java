package com.example.seniorproject2021;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;

import java.util.ArrayList;

public class ActivityAddCustomer extends AppCompatActivity implements TextWatcher, AdapterView.OnItemClickListener {

    Dal dal;
    CustomerAdapter ca;

    GridView gridView;
    ArrayList<Customer> aryCust;
    EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        gridView = findViewById(R.id.gridViewCustomers);
        aryCust = new ArrayList<Customer>();
        search = findViewById(R.id.editTextSearchCustomers);

        dal = new Dal(this);

        ca = new CustomerAdapter(this, R.layout.customer, aryCust);
        gridView.setAdapter(ca);
        gridView.setOnItemClickListener(this);

        search.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (count > 0) {
            aryCust = dal.getAllCustomersByName(s.toString(), getIntent().getIntExtra("providerId", 0));
            ca = new CustomerAdapter(this, R.layout.customer, aryCust);
        } else
            ca = new CustomerAdapter(this, R.layout.customer, new ArrayList<Customer>());
        gridView.setAdapter(ca);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Adding Customer");
        builder.setMessage("Are you sure you want to add " + ca.getCustomer(position).getName() + " to your customers?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dal.addContact(getIntent().getIntExtra("providerId", 0), ca.getCustomer(position).getId(), "");
            }
        });
        builder.setNegativeButton("No", null);
        AlertDialog mDialog = builder.create();
        mDialog.show();
    }
}