package com.theironyard.charlotte;

/**
 * Created by jenniferchang on 8/23/16.
 */
public class User {
    String name;
    private String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
