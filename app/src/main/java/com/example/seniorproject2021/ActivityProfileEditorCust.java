package com.example.seniorproject2021;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class ActivityProfileEditorCust extends AppCompatActivity {

    int accountId;
    Dal dal;
    boolean newImage;

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

        newImage = false;
        accountId = getIntent().getExtras().getInt("accountId");
        dal = new Dal(this);
        if (dal.checkForCustomer(accountId)) {
            insertData();
            newImage = true;
        }
    }

    private void insertData() {
        Customer c = dal.getCustomerByAccountId(accountId);
        name.setText(c.getName());
        image.setImageBitmap(BitmapFactory.decodeByteArray(c.getImage(), 0, c.getImage().length));
        if (c.getGender() == "male")
            genders.check(R.id.radioButtonMale);
        else if (c.getGender() == "female")
            genders.check(R.id.radioButtonFemale);
        else
            genders.check(R.id.radioButtonOther);
    }

    public void confirm(View view) {
        String stName = name.getText().toString();
        String gender = "";
        if (genders.getCheckedRadioButtonId() == R.id.radioButtonMale)
            gender = "male";
        else if (genders.getCheckedRadioButtonId() == R.id.radioButtonFemale)
            gender = "female";
        else if (genders.getCheckedRadioButtonId() == R.id.radioButtonOther)
            gender = "other";
        Drawable d;
        if (newImage)
            d = image.getDrawable();
        else if (gender == "male")
            d = getDrawable(R.drawable.male_avatar_profile_pic);
        else if (gender == "female")
            d = getDrawable(R.drawable.female_avatar_profile_pic);
        else
            d = getDrawable(R.drawable.avatar_profile_pic);
        Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] bitmapData = stream.toByteArray();

        if (stName.matches("") || gender.matches(""))
            Toast.makeText(this, "All fields must be filled!", Toast.LENGTH_SHORT).show();
        else {
            if (dal.checkForCustomer(accountId))
                dal.updateCustomer(dal.getCustomerByAccountId(accountId).getId(), stName, bitmapData, gender);
            else
                dal.addCustomer(stName, bitmapData, gender, accountId);

            Intent i = new Intent(this, ActivityProfileCust.class);
            i.putExtra("customerId", dal.getCustomerByAccountId(accountId).getId());
            startActivity(i);
        }
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
                        newImage = true;
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
                                Toast.makeText(this, "in", Toast.LENGTH_SHORT).show();
                                newImage = true;
                            }
                        }

                    }
                    break;
            }
        }
    }
}