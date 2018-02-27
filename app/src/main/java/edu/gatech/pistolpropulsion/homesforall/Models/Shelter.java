package edu.gatech.pistolpropulsion.homesforall.Models;

public class Shelter {
    private String name;
    private String capacity;
    private String restrictions;
    private double longitude;
    private double latitude;
    private String address;
    private String specialNotes;
    private int phone;
    private int key;

    public Shelter(String name, String capacity, String restrictions, double longitude, double latitude,
                   String address, String specialNotes, int phone, int key) {
        this.name = name;
        this.capacity = capacity;
        this.restrictions = restrictions;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.specialNotes = specialNotes;
        this.phone = phone;
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public int getPhone() {
        return phone;
    }

    public String getSpecialNotes() {
        return specialNotes;
    }

    public String getAddress() {
        return address;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getRestrictions() {
        return restrictions;
    }

    public String getCapacity() {
        return capacity;
    }

    public String getName() {
        return name;
    }
}
