package com.example.seniorproject2021;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ActivityAddProviders extends AppCompatActivity implements TextWatcher {

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

        getProvData();

        ProviderAdapter pa = new ProviderAdapter(this, R.layout.provider, arrProv);
        gridView.setAdapter(pa);
    }

    private void getProvData() {
        Provider prov;
        prov = new Provider("Yakov", "Lawyer", BitmapFactory.decodeResource(this.getResources(), R.drawable.temp_profile_pic_3), "Male", "I am a lawyer");
        arrProv.add(prov);
        prov = new Provider("Jamila", "Accountant", BitmapFactory.decodeResource(this.getResources(), R.drawable.temp_profile_pic_2), "Female", "I am an accountant");
        arrProv.add(prov);
        prov = new Provider("Steve", "House salesman", BitmapFactory.decodeResource(this.getResources(), R.drawable.temp_profile_pic), "Male", "I am a house salesman");
        arrProv.add(prov);
        prov = new Provider("Daniel", "plumber", BitmapFactory.decodeResource(this.getResources(), R.drawable.temp_profile_pic_4), "Male", "I am a plumber");
        arrProv.add(prov);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Toast.makeText(this, "Type: " + spinner.getSelectedItem() + ", Search: " + s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}