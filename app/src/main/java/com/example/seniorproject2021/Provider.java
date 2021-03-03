package com.example.seniorproject2021;

import android.graphics.Bitmap;
import android.widget.ImageView;

public class Provider {
    private String name;
    private String profession;
    private Bitmap image;
    private String gender;
    private String misc;
    private float score;

    public Provider(String name, String profession, Bitmap image, String gender, String misc) {
        this.name = name;
        this.profession = profession;
        this.image = image;
        this.gender = gender;
        this.misc = misc;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
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

    public String getMisc() {
        return misc;
    }

    public void setMisc(String misc) {
        this.misc = misc;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Provider{" +
                "name='" + name + '\'' +
                ", profession='" + profession + '\'' +
                ", image=" + image +
                ", gender='" + gender + '\'' +
                ", misc='" + misc + '\'' +
                ", score=" + score +
                '}';
    }
}
