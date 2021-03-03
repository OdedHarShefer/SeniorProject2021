package com.example.seniorproject2021;

import android.graphics.Bitmap;

public class Customer {
    private String name;
    private Bitmap image;
    private String gender;

    public Customer(String name, Bitmap image, String gender) {
        this.name = name;
        this.image = image;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", image=" + image +
                ", gender='" + gender + '\'' +
                '}';
    }
}
