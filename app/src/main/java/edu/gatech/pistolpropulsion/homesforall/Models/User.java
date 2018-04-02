package edu.gatech.pistolpropulsion.homesforall.Models;



/**
 * the user class
 */
@SuppressWarnings("FieldCanBeLocal")
public class User {
    private String email; //also username
    private String pwd;
    private boolean locked;
    private String name;
    private boolean isVeteran;
    private Gender gender;

    /**
     * constructor
     * @param email email
     * @param pwd password
     */
    public User(String email, String pwd) {
        this(email, pwd, "", false, null);
    }

    /**
     * constructor
     * @param email email
     * @param pass password
     * @param name name
     * @param isVet boolean is veteran or not
     * @param gen gender
     */
    public User(String email, String pass, String name, boolean isVet, Gender gen){
        this.email = email;
        this.pwd = pass;
        this.locked = false;
        this.name = name;
        this.isVeteran = isVet;
        this.gender = gen;
    }

    /**
     * empty constructor
     */
    public User() {
        this("", "");
    }

    /**
     * gets email
     * @return String email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * sets email
     * @param email String email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * gets name
     * @return String name
     */
    public String getName() {
        return this.name;
    }

    /**
     * sets name
     * @param email email
     */
    public void setName(String email) {
        this.name = email;
    }
}
