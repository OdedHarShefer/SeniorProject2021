package com.example.seniorproject2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ActivitySignIn extends AppCompatActivity {

    EditText name;
    EditText email;
    EditText password;
    RadioGroup accountTypes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        name = findViewById(R.id.editTextName);
        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);
        accountTypes = findViewById(R.id.radioGroup);
    }

    public void signIn(View view) {
        String stName = name.getText().toString();
        String stEmail = email.getText().toString();
        String stPassword = password.getText().toString();
        String stAccountType = "";
        if (accountTypes.getCheckedRadioButtonId() == R.id.radioButtonCustomer)
            stAccountType = "Customer";
        else if (accountTypes.getCheckedRadioButtonId() == R.id.radioButtonServiceProvider)
            stAccountType = "Provider";

        Toast.makeText(this, "Name: " + stName + ", Email: " + stEmail + ", Password: " + stPassword + ", Account type: " + stAccountType, Toast.LENGTH_SHORT).show();

        Intent i = new Intent(this, ActivityProfileEditorProv.class);
        startActivity(i);
        //Intent i = new Intent(this, ActivityProfileEditorCust.class);
        //startActivity(i);
    }
}