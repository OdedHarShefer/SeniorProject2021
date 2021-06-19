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
    Dal dal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);

        dal = new Dal(this);
    }

    public void login(View view) {
        String stEmail = email.getText().toString();
        String stPassword = password.getText().toString();

        //Toast.makeText(this, "" + dal.getAllAccounts().remove(0), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "" + dal.getAllAccounts().get(1), Toast.LENGTH_SHORT).show();
        if (dal.checkForAccount(stEmail, stPassword)) {
            Account a = dal.getAccount(stEmail);
            Toast.makeText(this, a.getId() + ", " + a.getEmail() + ", " + a.getPassword(), Toast.LENGTH_LONG).show();
            Intent i;
            if (dal.checkForProvider(a.getId()) && dal.checkForCustomer(a.getId()))
                i = new Intent(this, ActivityProfilePick.class);
            else if (dal.checkForProvider(a.getId()))
                i = new Intent(this, ActivityProfileProv.class);
            else
                i = new Intent(this, ActivityProfileCust.class);
            i.putExtra("accountId", a.getId());
            startActivity(i);
        } else;
            Toast.makeText(this, "Email of password are wrong", Toast.LENGTH_SHORT).show();
    }

    public void signIn(View view) {
        Intent i = new Intent(this, ActivitySignIn.class);
        startActivity(i);
    }
}