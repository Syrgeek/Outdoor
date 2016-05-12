package com.example.hassan.outdoor;

import java.util.Date;

public class Message {
    private String text ;
    private String sender ;
    private String date ;
    private String userEmail;

    public Message(String text, String sender, String date,String userEmail) {
        this.text = text;
        this.sender = sender;
        this.date = date;
        this.userEmail = userEmail;
    }

    public Message() {
    }

    public String getUserEmail() {return userEmail;}

    public void setUserEmail(String userEmail) {this.userEmail = userEmail;}

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
