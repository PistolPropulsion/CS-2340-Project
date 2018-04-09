package edu.gatech.pistolpropulsion.homesforall.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;


/**
 * class that represents search categories
 */
class Category implements Serializable {
    private ArrayList<String> list;
    private final String[] searchItems = {" MEN ", " WOMEN ",
            " NEWBORN ", " CHILD ", " YOUNG ADULT "};

    /**
     * default constructor
     */
    public Category(){
        list = new ArrayList<>();
    }

    /**
     * constructor given a list already
     * @param l list of categories
     */
    public Category(ArrayList<String> l) { list = l; }

    /**
     * add items to the list of categories
     * @param str string to search for categories within
     */
    public void addItems(String str){
        String rest = str.replaceAll("/", ",");
        String[] restrictions = rest.split(",");
        for (String restriction : restrictions) {
            if (restriction.toLowerCase().contains("anyone")) {
                Collections.addAll(list, searchItems);
                return;
            }
            if (restriction.toLowerCase().contains("men") && !restriction.
                    toLowerCase().contains("women")) {
                list.add(searchItems[0]);
            } else if (restriction.toLowerCase().contains("women")) {
                list.add(searchItems[1]);
            }
            if (restriction.toLowerCase().contains("newborn")) {
                list.add(searchItems[2]);
            }
            if (restriction.toLowerCase().contains("child")) {
                list.add(searchItems[3]);
            }
            if (restriction.toLowerCase().contains("young adult")) {
                list.add(searchItems[4]);
            }
            list.add(restriction.toUpperCase());
        }
    }

    /**
     * add names to the search categories
     * @param str name of a shelter
     */
    public void addName(String str) {
        list.add(str);
    }

    /**
     * gets list of categories
     * @return an ArrayList containing all the categories
     */
    public ArrayList<String> getList() {
        return this.list;
    }

}
