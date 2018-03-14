package edu.gatech.pistolpropulsion.homesforall.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class Shelter implements Serializable{
    private String name;
    private String capacity;
    private String restrictions;
    private String longitude;
    private String latitude;
    private String address;
    private String specialNotes;
    private String phone;
    private String key;
    private Category search;

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
        search = new Category();
        search.addItems(restrictions);
        search.addItems(specialNotes);
        search.addName(name);
        for(String item: search.getList()) {
            System.out.println(name + " " + item);
        }
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

    public ArrayList<String> getCategories() {
        return this.search.getList();
    }

    public boolean canAccommodate(String str) {
        System.out.println(str);
        for(int i = 0; i < search.getList().size(); i++){
            System.out.println(search.getList().get(i));
            if(search.getList().get(i).equals(str))
                return true;
        }
        return false;
    }
}
