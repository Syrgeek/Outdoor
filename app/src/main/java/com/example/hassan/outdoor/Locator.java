package com.example.hassan.outdoor;

public class Locator {
    private Location location ;

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getCurrentLocation()
    {
        return location ;
    }


    public Place getNeareastPlace()
    {
        return null;
    }
}
