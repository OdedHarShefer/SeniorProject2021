package com.example.seniorproject2021;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;

public class ActivityAddCustomer extends AppCompatActivity {

    GridView gridView;
    ArrayList<Customer> aryCust;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        gridView = findViewById(R.id.gridViewCustomers);
        aryCust = new ArrayList<Customer>();

        getCustData();

        CustomerAdapter ca = new CustomerAdapter(this, R.layout.customer, aryCust);
        gridView.setAdapter(ca);
    }

    private void getCustData() {
        Customer cust;
        cust = new Customer("Yakov", BitmapFactory.decodeResource(this.getResources(), R.drawable.temp_profile_pic_3), "Male");
        aryCust.add(cust);
        cust = new Customer("Jamila", BitmapFactory.decodeResource(this.getResources(), R.drawable.temp_profile_pic_2), "Female");
        aryCust.add(cust);
        cust = new Customer("Steve", BitmapFactory.decodeResource(this.getResources(), R.drawable.temp_profile_pic), "Male");
        aryCust.add(cust);
        cust = new Customer("Daniel", BitmapFactory.decodeResource(this.getResources(), R.drawable.temp_profile_pic_4), "Male");
        aryCust.add(cust);
    }
}