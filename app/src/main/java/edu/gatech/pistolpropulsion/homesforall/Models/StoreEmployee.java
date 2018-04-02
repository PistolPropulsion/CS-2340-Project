package edu.gatech.pistolpropulsion.homesforall.Models;

/**
 * Store employee class
 */
@SuppressWarnings("FieldCanBeLocal")
public class StoreEmployee {
    private String email; //also username
    private String name;
    private String pwd;

    /**
     * constructor
     * @param email email of employee
     * @param pwd password of employee
     */
    public StoreEmployee(String email, String pwd) {
        this(email, pwd, "");
    }

    /**
     * construcotr
     * @param email email
     * @param pwd password
     * @param name name
     */
    public StoreEmployee(String email, String pwd, String name) {
        this.email = email;
        this.pwd = pwd;
        this.name = name;
    }

    /**
     * gets name of employee
     * @return String name
     */
    public String getName() { return name; }

    /**
     * gets email of employee
     * @return String email
     */
    public String getEmail() { return email; }
}
