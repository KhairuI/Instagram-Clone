package com.example.instagramclone.model.m.authModel;

public class Register {
    private String email;
    private String password;
    public String uId;
    public boolean isRegister;

    public Register() {
    }

    public Register(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
