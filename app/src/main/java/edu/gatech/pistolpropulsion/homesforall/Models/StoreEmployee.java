package edu.gatech.pistolpropulsion.homesforall.Models;

/**
 * Created by joelj on 2/19/2018.
 */

public class StoreEmployee {
    private String email; //also username
    private String name;
    private String pwd;

    public StoreEmployee(String email, String pwd) {
        this(email, pwd, "");
    }

    public StoreEmployee(String email, String pwd, String name) {
        this.email = email;
        this.pwd = pwd;
        this.name = name;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
}
