package com.example.seniorproject2021;

import android.graphics.Bitmap;
import android.widget.ImageView;

public class Provider {
    private int id;
    private int accountId;
    private String name;
    private String profession;
    private byte[] image;
    private String gender;
    private String misc;
    private double score;
    private int scoreCount;

    public Provider() {}

    public double getAvgScore() {
        return score / scoreCount;
    }

    public int getScoreCount() {
        return scoreCount;
    }

    public void setScoreCount(int scoreCount) {
        this.scoreCount = scoreCount;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
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

    public String getMisc() {
        return misc;
    }

    public void setMisc(String misc) {
        this.misc = misc;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
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
