package com.example.seniorproject2021;

import javax.sql.StatementEvent;

public class Contact {
    private int providerId;
    private int customerId;
    private String note;
    private boolean voted;

    public Contact() {
        voted = false;
    }

    public Contact(int providerId, int customerId, String note) {
        this.providerId = providerId;
        this.customerId = customerId;
        this.note = note;
    }

    public boolean isVoted() {
        return voted;
    }

    public void setVoted(boolean voted) {
        this.voted = voted;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "providerId=" + providerId +
                ", customerId=" + customerId +
                ", note='" + note + '\'' +
                '}';
    }
}
