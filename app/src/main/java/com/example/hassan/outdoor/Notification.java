package com.example.hassan.outdoor;

/**
 * Created by Osama on 5/11/2016.
 */
public class Notification {

    private String username ;
    private String status ;
    private String type ;

    Notification(String u, String s, String t){
        username = u;
        status = s;
        type = t;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }
}
