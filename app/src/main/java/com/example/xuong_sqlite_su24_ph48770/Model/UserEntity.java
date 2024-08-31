package com.example.xuong_sqlite_su24_ph48770.Model;

public class UserEntity {
    private int id;
    private String email;
    private String pass;
    private String confirmPass;

    public UserEntity(int id, String email, String pass, String confirmPass) {
        this.id = id;
        this.email = email;
        this.pass = pass;
        this.confirmPass = confirmPass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getConfirmPass() {
        return confirmPass;
    }

    public void setConfirmPass(String confirmPass) {
        this.confirmPass = confirmPass;
    }
}
