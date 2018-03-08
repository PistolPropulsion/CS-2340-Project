package edu.gatech.pistolpropulsion.homesforall.Models;

import java.util.ArrayList;

public class ShelterManager {
    private Shelter[] shelterArray;
    private int count;

    public ShelterManager(int count) {
        shelterArray = new Shelter[count];
        this.count = count;
    }

    public ShelterManager(String[][] data, int count) {
        this(count);
        for(int i = 0; i < count; i++) {
            Shelter newShelter = new Shelter(data[i][0], data[i][1], data[i][2], data[i][3], data[i][4],
                    data[i][5], data[i][6], data[i][7], data[i][8]);
            shelterArray[i] = newShelter;
        }
    }

    private String[] makeNamesArray(int count) {
        String [] names = new String[count];
        for(int i = 0; i < count; i++){
            names[i] = shelterArray[i].getName();
        }
        return names;
    }

    public String[] getNamesArray() {
        return makeNamesArray(count);
    }

    public Shelter[] getShelterArray() {
        return this.shelterArray;
    }

    public ArrayList<Shelter> search(String search, ArrayList<String> options) {
        ArrayList<Shelter> returnShelterList = new ArrayList<>();
        for (int i = 0; i < shelterArray.length; i++) {
            switch(search) {
                case "Age":
                    for(int j = 0; j < options.size(); j++) {
                        if (shelterArray[i].getRestrictions().toLowerCase().contains(options.get(j).toLowerCase())
                                || shelterArray[i].getSpecialNotes().toLowerCase().contains(options.get(j).toLowerCase())) {
                            returnShelterList.add(shelterArray[i]);
                        }
                    }
                    break;
                case "Gender":
                    for(int j = 0; j < options.size(); j++) {
                        if (shelterArray[i].getRestrictions().toLowerCase().contains(options.get(j).toLowerCase())
                                || shelterArray[i].getSpecialNotes().toLowerCase().contains(options.get(j).toLowerCase())) {
                            returnShelterList.add(shelterArray[i]);
                        }
                    }
                    break;
                case "Other":
                    for(int j = 0; j < options.size(); j++) {
                        if (shelterArray[i].getRestrictions().toLowerCase().contains(options.get(j).toLowerCase())
                                || shelterArray[i].getSpecialNotes().toLowerCase().contains(options.get(j).toLowerCase())) {
                            returnShelterList.add(shelterArray[i]);
                        }
                    }
                    break;
            }
        }
        return returnShelterList;
    }
}
