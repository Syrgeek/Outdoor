package com.example.hassan.outdoor;
import java.util.ArrayList;
import java.util.Date;

public class Comment {
    private String text ;
    private Date date ;
    private NormalUser user ;
    private ArrayList<NormalUser> likers ;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public NormalUser getUser() {
        return user;
    }

    public void setUser(NormalUser user) {
        this.user = user;
    }

    public ArrayList<NormalUser> getLikers() {
        return likers;
    }

    public void setLikers(ArrayList<NormalUser> likers) {
        this.likers = likers;
    }
}
