package com.example.seniorproject2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityLogIn extends AppCompatActivity {

    EditText email;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);
    }

    public void login(View view) {
        String stEmail = email.getText().toString();
        String stPassword = password.getText().toString();

        Toast.makeText(this, "Email: " + stEmail + ", Password: " + stPassword, Toast.LENGTH_SHORT).show();

        //Intent i = new Intent(this, ActivityProfilePick.class);
        //startActivity(i);

        Intent i = new Intent(this,ActivityProfileCust.class);
        startActivity(i);

        //Intent i = new Intent(this, ActivityProfileProv.class);
        //startActivity(i);
    }

    public void signIn(View view) {
        Intent i = new Intent(this, ActivitySignIn.class);
        startActivity(i);
    }
}