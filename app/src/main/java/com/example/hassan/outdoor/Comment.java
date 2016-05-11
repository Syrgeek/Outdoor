package com.example.hassan.outdoor;
import java.util.ArrayList;
import java.util.Date;

public class Comment {
    private String text ;
    private String date ;
    private String user ;

    public Comment(String text, String date, String user) {
        this.text = text;
        this.date = date;
        this.user = user;
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

    /*public ArrayList<NormalUser> getLikers() {
        return likers;
    }


    public void setLikers(ArrayList<NormalUser> likers) {
        this.likers = likers;
    }
    */
}
