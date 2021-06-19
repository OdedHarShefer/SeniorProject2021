package com.example.seniorproject2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

public class ActivitySignIn extends AppCompatActivity {

    EditText email;
    EditText password;
    RadioGroup accountTypes;
    Dal dal;
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);
        accountTypes = findViewById(R.id.radioGroup);

        dal = new Dal(this);
        message = "";
    }

    public void signIn(View view) {
        Intent i;
        String stEmail = email.getText().toString();
        String stPassword = password.getText().toString();
        String stAccountType = "";
        if (accountTypes.getCheckedRadioButtonId() == R.id.radioButtonCustomer)
            stAccountType = "Customer";
        else if (accountTypes.getCheckedRadioButtonId() == R.id.radioButtonServiceProvider)
            stAccountType = "Provider";

        if (checkData()) {
            if (dal.checkForAccount(stEmail)) {
                if (stAccountType == "Provider") {
                    if (dal.checkForProvider(dal.getAccount(stEmail).getId()))
                        Toast.makeText(this, "Provider account already exists", Toast.LENGTH_SHORT).show();
                    else {
                        i = new Intent(this, ActivityProfileEditorProv.class);
                        i.putExtra("accountId", dal.getAccount(stEmail).getId());
                        startActivity(i);
                    }
                } else if (dal.checkForCustomer(dal.getAccount(stEmail).getId()))
                    Toast.makeText(this, "Customer account already exists", Toast.LENGTH_SHORT).show();
                else {
                    i = new Intent(this, ActivityProfileEditorCust.class);
                    i.putExtra("accountId", dal.getAccount(stEmail).getId());
                    startActivity(i);
                }
            } else {
                dal.addAccount(stEmail, stPassword);
                if (stAccountType == "Provider")
                    i = new Intent(this, ActivityProfileEditorProv.class);
                else
                    i = new Intent(this, ActivityProfileEditorCust.class);
                i.putExtra("accountId", dal.getAccount(stEmail).getId());
                startActivity(i);
            }
        } else
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public boolean checkData() {
        String stEmail = email.getText().toString();
        String stPassword = password.getText().toString();
        if (stEmail.matches("") || stPassword.matches("") || accountTypes.getCheckedRadioButtonId() == -1) {
            message = "All values must be filled!";
            return false;
        } else if (!stEmail.contains(".com") || stEmail.indexOf('@') < 1) {
            message = "Not a valid email";
            return false;
        } else if (stPassword.length() < 8) {
            message = "Password is too short";
            return false;
        } else if (!hasAlphabet(stPassword)) {
            message = "Password must contain at least 1 letter";
            return false;
        } else if (!hasNumber(stPassword)) {
            message = "Password must contain at least 1 number";
            return false;
        }
        return true;
    }

    private boolean hasNumber(String st) {
        char[] chars = st.toCharArray();
        for (char c : chars)
            if (Character.isDigit(c))
                return true;
        return false;
    }

    public boolean hasAlphabet(String st) {
        char letter = 'a';
        for (int i = 0; i < 26; i++)
            if (st.toLowerCase().indexOf(letter + i) != -1)
                return true;
            return false;
    }
}