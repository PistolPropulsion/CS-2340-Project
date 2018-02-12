package edu.gatech.pistolpropulsion.homesforall.Models;

/**
 * Created by Yuan Zhang on 2/11/2018.
 */

public class User {
    private String email; //also username
    private String pwd;
    private boolean locked;

    public User(String email, String pwd) {
        this.email = email;
        this.pwd = pwd;
        this.locked = false;
    }
}
