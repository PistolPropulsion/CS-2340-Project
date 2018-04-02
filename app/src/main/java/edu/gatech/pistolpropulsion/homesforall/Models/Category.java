package edu.gatech.pistolpropulsion.homesforall.Models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Yuan Zhang on 3/13/2018.
 */

/**
 * class that represents search categories
 */
public class Category implements Serializable {
    private ArrayList<String> list;
    private String[] searchItems = {" MEN ", " WOMEN ", " NEWBORN ", " CHILD ", " YOUNG ADULT "};;

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
        for(int i = 0; i < restrictions.length; i++) {
            if(restrictions[i].toLowerCase().contains("anyone")){
                for(int j = 0; j < searchItems.length; j++) {
                    list.add(searchItems[j]);
                }
                return;
            }
            if(restrictions[i].toLowerCase().contains("men") && !restrictions[i].toLowerCase().contains("women"))
                list.add(searchItems[0]);
            if(restrictions[i].toLowerCase().contains("women"))
                list.add(searchItems[1]);
            if(restrictions[i].toLowerCase().contains("newborn"))
                list.add(searchItems[2]);
            if(restrictions[i].toLowerCase().contains("child"))
                list.add(searchItems[3]);
            if(restrictions[i].toLowerCase().contains("young adult"))
                list.add(searchItems[4]);
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
