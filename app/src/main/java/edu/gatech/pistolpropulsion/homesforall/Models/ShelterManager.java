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

    public Shelter[] search(String search, ArrayList<String> options) {
        ArrayList<Shelter> returnShelterList = new ArrayList<>();
        for(String i: options) {
            System.out.println(i);
        }
        for (int i = 0; i < shelterArray.length; i++) {
            switch(search.toLowerCase()) {
                case "age":
                    for(int j = 0; j < options.size(); j++) {
                        if (shelterArray[i].toString().toLowerCase().contains(options.get(j).toLowerCase())
                                || shelterArray[i].toString().toLowerCase().contains("anyone")) {
                            returnShelterList.add(shelterArray[i]);
                        }
                    }
                    break;
                case "gender":
                    for(int j = 0; j < options.size(); j++) {
                        if (shelterArray[i].toString().toLowerCase().contains(options.get(j).toLowerCase())
                                || shelterArray[i].toString().toLowerCase().contains("anyone")) {
                            returnShelterList.add(shelterArray[i]);
                        }
                    }
                    break;
                case "name":
                    for(int j = 0; j < options.size(); j++) {
                        if (shelterArray[i].toString().toLowerCase().contains(options.get(j).toLowerCase())
                                || shelterArray[i].toString().toLowerCase().contains("anyone")) {
                            returnShelterList.add(shelterArray[i]);
                        }
                    }
                    break;
            }
        }
        for(Shelter i: returnShelterList){
            System.out.println(i.getName());
        }
        return returnShelterList.toArray(new Shelter[returnShelterList.size()]);
    }
}
