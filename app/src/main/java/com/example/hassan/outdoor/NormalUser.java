package com.example.hassan.outdoor;

import java.util.ArrayList;

public class NormalUser {
    private String email ;
    private String username ;
    private String password ;
    private String question;
    private String answer;
    private String email2;
    private ArrayList<Checkin> checkins ;
    private ArrayList<NormalUser> friends ;
    //private ArrayList<Taste> tastes ;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public ArrayList<Checkin> getCheckins() {
        return checkins;
    }

    public void setCheckins(ArrayList<Checkin> checkins) {
        this.checkins = checkins;
    }

    public ArrayList<NormalUser> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<NormalUser> friends) {
        this.friends = friends;
    }
}
