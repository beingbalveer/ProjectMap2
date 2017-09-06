package com.example.android.projectmap;


public class LocationClass {

    String location = null;
    Double lan = null;
    Double lon = null;
    boolean selected = false;

    public LocationClass(String location, Double lan,Double lon,boolean selected) {
        super();
        this.location = location;
        this.lan = lan;
        this.lon = lon;
        this.selected = selected;
    }


    public void setLocation(String location) {
        this.location = location;
    }

    public void setLan(Double lan) {
        this.lan = lan;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getLocation() {
        return location;
    }

    public Double getLan() {
        return lan;
    }

    public Double getLon() {
        return lon;
    }

    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
