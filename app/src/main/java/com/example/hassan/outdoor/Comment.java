package com.example.hassan.outdoor;
import java.util.ArrayList;
import java.util.Date;

public class Comment {
    private String text ;
    private String date ;
    private String user ;
<<<<<<< HEAD

    public Comment(String text, String date, String user) {
        this.text = text;
        this.date = date;
        this.user = user;
    }
=======
    private ArrayList<NormalUser> likers ;
    private int likes ;
    private int if_liked;
>>>>>>> 2b7eb031ea182acd03482355ba15a7708d02417e

    Comment(String u, String t, String d){
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

<<<<<<< HEAD
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

=======

    public ArrayList<NormalUser> getLikers() {
        return likers;
    }

    public void setLikers(ArrayList<NormalUser> likers) {
        this.likers = likers;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getIf_liked() {
        return if_liked;
    }

    public void setIf_liked(int if_liked) {
        this.if_liked = if_liked;
    }

>>>>>>> 2b7eb031ea182acd03482355ba15a7708d02417e
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

<<<<<<< HEAD
    /*public ArrayList<NormalUser> getLikers() {
        return likers;
    }


    public void setLikers(ArrayList<NormalUser> likers) {
        this.likers = likers;
=======
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
>>>>>>> 2b7eb031ea182acd03482355ba15a7708d02417e
    }
    */
}
