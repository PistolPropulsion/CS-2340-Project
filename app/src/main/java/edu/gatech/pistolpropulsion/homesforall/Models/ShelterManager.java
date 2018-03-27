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

    public ShelterManager() {
        this(null, 0);
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

    public void setShelterArray(Shelter[] array) {
        this.shelterArray = array;
        count = array.length;
    }

    public Shelter[] search(ArrayList<String> options) {
        for (String items: options){
            System.out.println(items);
        }
        ArrayList<Shelter> searchList = new ArrayList<>();
        for(int i = 0; i < shelterArray.length; i++){
            for(int j = 0; j < options.size(); j++) {
                if (shelterArray[i].canAccommodate(options.get(j))) {
                    if (!searchList.contains(shelterArray[i])) {
                        searchList.add(shelterArray[i]);
                    }
                }
            }
        }
        for(Shelter item: searchList){
            System.out.println(item.getName());
        }
        System.out.println(searchList.size());
        return searchList.toArray(new Shelter[searchList.size()]);
    }

    public Shelter[] searchName(ArrayList<String> options) {
        ArrayList<Shelter> searchList = new ArrayList<>();
        for(int i = 0; i < shelterArray.length; i++){
            for(int j = 0; j < options.size(); j++) {
                if (shelterArray[i].getName().toLowerCase().contains(options.get(j).toLowerCase())) {
                    if (!searchList.contains(shelterArray[i])) {
                        searchList.add(shelterArray[i]);
                    }
                }
            }
        }
        return searchList.toArray(new Shelter[searchList.size()]);
    }
}
