package com.example.seniorproject2021;

import android.graphics.Bitmap;

public class Customer {
    private int id;
    private int accountId;
    private String name;
    private byte[] image;
    private String gender;

    public Customer() {}

    public Customer(String name, byte[] image, String gender) {
        this.name = name;
        this.image = image;
        this.gender = gender;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
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
