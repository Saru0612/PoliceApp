package com.example.policeapp;

public class MarkerData {

    private double lat;
    private double lon;
    private String crime;

    public MarkerData() {
    }

    public MarkerData(double lat, double lon, String crime) {
        this.lat = lat;
        this.lon = lon;
        this.crime = crime;
    }

    public MarkerData(String crime, double lat, double lon) {
        this.crime = crime;
        this.lat = lat;
        this.lon = lon;
    }

    public String getCrime() {
        return crime;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public void setCrime(String crime) {
        this.crime = crime;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
