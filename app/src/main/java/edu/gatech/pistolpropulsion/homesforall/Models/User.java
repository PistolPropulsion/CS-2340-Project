package edu.gatech.pistolpropulsion.homesforall.Models;

/**
 * Created by Yuan Zhang on 2/11/2018.
 */

public class User {
    private String email; //also username
    private String pwd;
    private boolean locked;
    private String name;
    private boolean isVeteran;
    private Gender gender;

    public User(String email, String pwd) {
        this(email, pwd, "", false, null);
    }
    public User(String email, String pass, String name, boolean isVet, Gender gen){
        this.email = email;
        this.pwd = pass;
        this.locked = false;
        this.name = name;
        this.isVeteran = isVet;
        this.gender = gen;
    }

    public User() {
        this("", "");
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPass() {
        return this.pwd;
    }
    public void setPass(String pwd) {
        this.pwd = pwd;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String email) {
        this.name = email;
    }
    public boolean isLocked() {
        return this.locked;
    }
    public void setLocked(boolean locked) {
        this.locked = locked;
    }
    public boolean getVeteran() {
        return this.isVeteran;
    }
    public void setVeteran(boolean vet) {
        this.isVeteran = vet;
    }
    public Gender getGender() {
        return this.gender;
    }
    public void setGender(Gender gen) {
        this.gender = gen;
    }
}
