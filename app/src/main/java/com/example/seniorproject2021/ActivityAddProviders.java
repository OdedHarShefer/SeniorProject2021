package com.example.seniorproject2021;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ActivityAddProviders extends AppCompatActivity implements TextWatcher, AdapterView.OnItemClickListener {

    Dal dal;
    ProviderAdapter pa;

    GridView gridView;
    ArrayList<Provider> arrProv;
    Spinner spinner;
    EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_providers);

        gridView = findViewById(R.id.gridViewProviders);
        arrProv = new ArrayList<Provider>();
        spinner = findViewById(R.id.spinner);
        search = findViewById(R.id.editTextSearchProviders);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.search_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        search.addTextChangedListener(this);

        dal = new Dal(this);

        pa = new ProviderAdapter(this, R.layout.provider, arrProv);
        gridView.setAdapter(pa);
        gridView.setOnItemClickListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (spinner.getSelectedItemPosition() == 0) {
            if (count > 0) {
                arrProv = dal.getAllProvidersByName(s.toString(), getIntent().getIntExtra("customerId", 0));
                pa = new ProviderAdapter(this, R.layout.provider, arrProv);
            } else
                pa = new ProviderAdapter(this, R.layout.provider, new ArrayList<Provider>());
        }
        else {
            if (count > 0) {
                arrProv = dal.getAllProvidersByProfession(s.toString(), getIntent().getIntExtra("customerId", 0));
                pa = new ProviderAdapter(this, R.layout.provider, arrProv);
            } else
                pa = new ProviderAdapter(this, R.layout.provider, new ArrayList<Provider>());
        }

        gridView.setAdapter(pa);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Adding Provider");
        builder.setMessage("Are you sure you want to add " + pa.getProvider(position).getName() + " to your providers?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dal.addContact(pa.getProvider(position).getId(), getIntent().getIntExtra("customerId", 0), "");
            }
        });
        builder.setNegativeButton("No", null);
        AlertDialog mDialog = builder.create();
        mDialog.show();
    }
}