package com.example.hassan.outdoor;

import java.util.ArrayList;
import java.util.Date;

public class Checkin {
    private int id;
    private String email;
    private String username;
    private String place ;
    private String status ;
    private String date ;
    private int comments ;
    private int likes ;
    private int if_liked;

    Checkin() {super();}

    Checkin(String u,String p,String s,String d,int l,int i,int liked,int c) {
        status = s;
        place = p;
        date = d;
        username = u;
        likes = l;
        id = i;
        if_liked = liked;
        comments = c;
    }

    public void setIf_liked(int b) {this.if_liked = b;}

    public int getComments() {return comments;}

    public void setComment(int c) {this.comments = c;}

    public int getIf_liked() {return if_liked;}

    public void setLikes(int i) {this.likes = i;}

    public int getLikes() {return this.likes;}

    public void setUsername(String username) {this.username = username;}

    public String getUsername() {return this.username;}

    public void setId(int i) {id = i;}

    public int getId() {return id;}

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getLikers() {
        return likes;
    }

    public void setLikers(int likers) {
        this.likes = likers;
    }
}
