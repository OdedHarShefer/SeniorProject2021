package com.example.seniorproject2021;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ActivityProfileEditorProv extends AppCompatActivity {

    EditText name;
    EditText proffesion;
    ImageButton image;
    RadioGroup genders;
    EditText misc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_editor_prov);

        name = findViewById(R.id.editTextName);
        proffesion = findViewById(R.id.editTextProfession);
        image = findViewById(R.id.imageView);
        genders = findViewById(R.id.radioGroup);
        misc = findViewById(R.id.editTextMultiLine);
    }

    public void confirm(View view) {
        String stName = name.getText().toString();
        String stProffsion = proffesion.getText().toString();
        String stMisc = misc.getText().toString();
        //לשאול את המורה לגבי תמונה
        String gender = "";
        if (genders.getCheckedRadioButtonId() == R.id.radioButtonMale)
            gender = "Male";
        else if (genders.getCheckedRadioButtonId() == R.id.radioButtonFemale)
            gender = "Female";
        else if (genders.getCheckedRadioButtonId() == R.id.radioButtonOther)
            gender = "Other";

        Toast.makeText(this, "Name: " + stName + ", Proffesion: " + stProffsion + ", Gender: " + gender + ", Misc: " + stMisc, Toast.LENGTH_SHORT).show();

        Intent i = new Intent(this, ActivityProfileProv.class);
        startActivity(i);
    }

    public void addPicture(View view) {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose your profile picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto , 1);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {
                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                        image.setImageBitmap(selectedImage);
                    }

                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                        Uri selectedImage = data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        if (selectedImage != null) {
                            Cursor cursor = getContentResolver().query(selectedImage,
                                    filePathColumn, null, null, null);
                            if (cursor != null) {
                                cursor.moveToFirst();

                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                String picturePath = cursor.getString(columnIndex);
                                image.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                                cursor.close();
                            }
                        }

                    }
                    break;
            }
        }
    }
}