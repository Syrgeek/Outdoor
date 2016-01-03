package com.example.hassan.outdoor;
import java.util.ArrayList;

public class Place {
    private String name ;
    private Location location ;
    private PremiumUser owner ;
    private double rate ;
    private int numberOfUsers ;
    private ArrayList<Comment> tips ;
    //private ArrayList<Taste> tastes ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public PremiumUser getOwner() {
        return owner;
    }

    public void setOwner(PremiumUser owner) {
        this.owner = owner;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getNumberOfUsers() {
        return numberOfUsers;
    }

    public void setNumberOfUsers(int numberOfUsers) {
        this.numberOfUsers = numberOfUsers;
    }

    public ArrayList<Comment> getTips() {
        return tips;
    }

    public void setTips(ArrayList<Comment> tips) {
        this.tips = tips;
    }
}
