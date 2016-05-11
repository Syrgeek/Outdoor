package com.example.hassan.outdoor;
import java.util.ArrayList;
import java.util.Date;

public class Comment {
    private String text ;
    private String date ;
    private String user ;

    public Comment(String u, String t, String d){
        user = u;
        text = t;
        date = d;
    }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

}
