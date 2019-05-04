package com.bowling.edward.bowling;

public class Alley {

    public String name, location, latitude, longitude, website, email;

    public double rating;

    public Alley(String name, String location, double rating) {
        this.name = name;
        this.location = location;
        this.rating = rating;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
