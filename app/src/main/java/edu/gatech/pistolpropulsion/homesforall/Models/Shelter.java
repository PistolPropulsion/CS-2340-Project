package edu.gatech.pistolpropulsion.homesforall.Models;

public class Shelter {
    private String name;
    private String capacity;
    private String restrictions;
    private String longitude;
    private String latitude;
    private String address;
    private String specialNotes;
    private String phone;
    private String key;

    public Shelter(String key, String name, String capacity, String restrictions, String longitude, String latitude,
                   String address, String specialNotes, String phone) {
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

    public String getKey() {
        return key;
    }

    public String getPhone() {
        return phone;
    }

    public String getSpecialNotes() {
        return specialNotes;
    }

    public String getAddress() {
        return address;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
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

    public String toString(){
        return this.key + " " + this.name + " "
                + this.capacity + " " + this.restrictions + " "
                + this.longitude + " " + this.latitude + " "
                + this.address + " " + this.specialNotes + " "
                + this.phone;
    }
}
