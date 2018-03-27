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
    private String vacancy;
    private Category search;

    public Shelter() {
    }

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
        vacancy = capacity;

        search = new Category();
        search.addItems(restrictions);
        search.addItems(specialNotes);
        search.addName(name);
        for(String item: search.getList()) {
            System.out.println(name + " " + item);
        }
    }

    public void reserveSpots(int spots) {

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

    public void setName(String name) {
        this.name = name;
    }

    public void setRestrictions(String restrictions) {
        this.restrictions = restrictions;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSpecialNotes(String specialNotes) {
        this.specialNotes = specialNotes;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Category getSearch() {
        return search;
    }

    public void setSearch(Category search) {
        this.search = search;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getVacancy() {
        return vacancy;
    }

    public void setVacancy(String vacancy) {
        this.vacancy = vacancy;
    }
}
