package edu.gatech.pistolpropulsion.homesforall.Models;


/**
 * Administrator class
 */
@SuppressWarnings("FieldCanBeLocal")
public class Administrator {
    private final String email; //also username
    private final String name;
    //passwords can change
    @SuppressWarnings("FieldMayBeFinal")
    private String pwd;

    /**
     * creates Administrator
     * @param email email of admin
     * @param pwd password of admin
     */
    public Administrator(String email, String pwd) {
        this(email, pwd, "");
    }

    /**
     * another constructor
     * @param email email of admin
     * @param pwd password of admin
     * @param name name of admin
     */
    public Administrator(String email, String pwd, String name) {
        this.email = email;
        this.pwd = pwd;
        this.name = name;
    }

    /**
     * gets name of admin
     * @return a String name of admin
     */
    public String getName() { return name; }

    /**
     * returns email of admin
     * @return a String email of admin
     */
    public String getEmail() { return email; }
}
