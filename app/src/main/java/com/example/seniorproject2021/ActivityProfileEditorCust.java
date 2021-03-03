package com.example.seniorproject2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ActivityProfileEditorCust extends AppCompatActivity {

    EditText name;
    ImageView image;
    RadioGroup genders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_editor_cust);

        name = findViewById(R.id.editTextName);
        image = findViewById(R.id.imageView);
        genders = findViewById(R.id.radioGroup);
    }

    public void confirm(View view) {
        String stName = name.getText().toString();
        //לשאול את המורה לגבי תמונה
        String gender = "";
        if (genders.getCheckedRadioButtonId() == R.id.radioButtonMale)
            gender = "Male";
        else if (genders.getCheckedRadioButtonId() == R.id.radioButtonFemale)
            gender = "Female";
        else if (genders.getCheckedRadioButtonId() == R.id.radioButtonOther)
            gender = "Other";

        Toast.makeText(this, "Name: " + stName + ", Gender: " + gender, Toast.LENGTH_SHORT).show();

        //Intent i = new Intent(this,ActivityProfileCust.class);
        //startActivity(i);
    }
}