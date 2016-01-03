package com.example.hassan.outdoor;
import java.util.ArrayList;

public class Brand {
    private String name ;
    private PremiumUser owner ;
    private ArrayList<NormalUser> followers ;
    private ArrayList<Comment> tips ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PremiumUser getOwner() {
        return owner;
    }

    public void setOwner(PremiumUser owner) {
        this.owner = owner;
    }

    public ArrayList<NormalUser> getFollowers() {
        return followers;
    }

    public void setFollowers(ArrayList<NormalUser> followers) {
        this.followers = followers;
    }

    public ArrayList<Comment> getTips() {
        return tips;
    }

    public void setTips(ArrayList<Comment> tips) {
        this.tips = tips;
    }

    public void addFollower(NormalUser newFollower)
    {

    }

    public void deleteFollower(NormalUser deletedFollower)
    {

    }
}
