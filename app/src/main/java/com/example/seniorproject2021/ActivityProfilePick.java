package com.example.seniorproject2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

public class ActivityProfilePick extends AppCompatActivity {

    Dal dal;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_pick);

        dal = new Dal( this);

        radioGroup = findViewById(R.id.radioGroup);
    }

    public void profile(View view) {
        int accountId;
        Intent i = getIntent();
        if (i.getIntExtra("accountId" , 0) != 0)
            accountId = i.getIntExtra("accountId" , 0);
        else if (i.getIntExtra("providerId" , 0) != 0)
            accountId = dal.getProviderById(i.getIntExtra("providerId" , 0)).getAccountId();
        else
            accountId = dal.getCustomerById(i.getIntExtra("customerId" , 0)).getAccountId();

        if (radioGroup.getCheckedRadioButtonId() == R.id.radioButtonServiceProvider){
            i = new Intent(this, ActivityProfileProv.class);
            i.putExtra("providerId", dal.getProviderByAccountId(accountId).getId());
        } else {
            i = new Intent(this, ActivityProfileCust.class);
            i.putExtra("customerId", dal.getCustomerByAccountId(accountId).getId());
        }
        startActivity(i);
    }
}