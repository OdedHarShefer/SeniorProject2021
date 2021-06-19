package com.example.seniorproject2021;

public class Meeting {
    private int providerId;
    private int customerId;
    private String date;

    public Meeting() {
    }

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "providerId=" + providerId +
                ", customerId=" + customerId +
                ", date='" + date + '\'' +
                '}';
    }
}
