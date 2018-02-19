package edu.gatech.pistolpropulsion.homesforall.Models;

/**
 * Created by Yuan Zhang on 2/11/2018.
 */

public class User {
    private String email; //also username
    private String name;
    private String pwd;
    private boolean locked;
    private boolean isVeteran;

    public User(String email, String pwd) {
        this(email, pwd, "", false);
    }

    public User(String email, String pwd, String name, boolean isVeteran) {
        this.email = email;
        this.pwd = pwd;
        this.locked = false; //always default to unlocked
        this.isVeteran = isVeteran;
        this.name = name;
    }
}
