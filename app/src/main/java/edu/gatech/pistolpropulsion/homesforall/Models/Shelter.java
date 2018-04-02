package edu.gatech.pistolpropulsion.homesforall.Models;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * shelter class, stores shelter information
 */
public class Shelter implements Serializable{
    private String name;
    private int capacity;
    private String restrictions;
    private String longitude;
    private String latitude;
    private String address;
    private String specialNotes;
    private String phone;
    private String key;
    private int vacancy;
    private Category search;

    /**
     * constructor... yeah not the best oh well. all parameters as Strings
     * @param key key
     * @param name name
     * @param capacity capacity
     * @param restrictions restrictions
     * @param longitude long
     * @param latitude lat
     * @param address address
     * @param specialNotes special notes
     * @param phone phone number
     */
    public Shelter(String key, String name, String capacity, String restrictions, String longitude, String latitude,
                   String address, String specialNotes, String phone) {
        this.name = name;
        this.restrictions = restrictions;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.specialNotes = specialNotes;
        this.phone = phone;
        this.key = key;

        String[] array = capacity.split(",");
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            try {
                sum += Integer.parseInt(array[i].replaceAll("[\\D]", ""));
            } catch (Exception e) {
            }
        }
        this.capacity = sum;
        vacancy = this.capacity;

        search = new Category();
        search.addItems(restrictions);
        search.addItems(specialNotes);
        search.addName(name);
        for(String item: search.getList()) {
            System.out.println(name + " " + item);
        }
    }

    /**
     * empty constructor
     */
    public Shelter() {
        this("none", "empty", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A");
    }

    /**
     * updates spots
     * @param spots number of reservations
     */
    public void reserveSpots(int spots) {
        this.vacancy -= spots;
    }

    /**
     * getter
     * @return key
     */
    public String getKey() { return key; }

    /**
     * getter
     * @return phone #
     */
    public String getPhone() {
        return phone;
    }

    /**
     * getter
     * @return special notes
     */
    public String getSpecialNotes() {
        return specialNotes;
    }

    /**
     * getter
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * getter
     * @return latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * getter
     * @return longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * getter
     * @return restrictions
     */
    public String getRestrictions() {
        return restrictions;
    }

    /**
     * getter
     * @return capacity
     */
    public int getCapacity() {
        return this.capacity;
    }

    /**
     * getter
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * getter
     * @return search categories
     */
    public ArrayList<String> getSearch() {
        return this.search.getList();
    }

    /**
     * getter
     * @return number of vacancies
     */
    public int getVacancy() {
        return vacancy;
    }

    /**
     * setter
     * @param key key
     */
    public void setKey(String key) { this.key = key; }

    /**
     * setter
     * @param phone #
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * setter
     * @param specialNotes notes
     */
    public void setSpecialNotes(String specialNotes) { this.specialNotes = specialNotes; }

    /**
     * setter
     * @param address address
     */
    public void setAddress(String address) { this.address = address; }

    /**
     * setter
     * @param latitude lat
     */
    public void setLatitude(String latitude) { this.latitude = latitude; }

    /**
     * setter
     * @param longitude long
     */
    public void setLongitude(String longitude) { this.longitude = longitude; }

    /**
     * setter
     * @param restrictions restrictions
     */
    public void setRestrictions(String restrictions) {
        this.restrictions = restrictions;
    }

    /**
     * setter
     * @param capacity capacity
     */
    public void setCapacity(int capacity) { this.capacity = capacity; }

    /**
     * setter
     * @param name name
     */
    public void setName(String name) { this.name = name; }

    /**
     * setter
     * @param search search
     */
    public void setSearch(ArrayList<String> search) { this.search =  new Category(search); }

    /**
     * setter
     * @param vacancy vacancies
     */
    public void setVacancy(int vacancy) {
        this.vacancy = vacancy;
    }

    /**
     * to string
     * @return everything in CSV file
     */
    public String toString(){
        return this.key + " " + this.name + " "
                + this.capacity + " " + this.restrictions + " "
                + this.longitude + " " + this.latitude + " "
                + this.address + " " + this.specialNotes + " "
                + this.phone;
    }

    /**
     * given a string containing search items, returns if can accommodate
     * @param str string of search items
     * @return true or false shelter is ok
     */
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
