package com.bowling.edward.bowling;

public class User {


    private String email;
    private String username;


    public User(){

    }

    public User(String username, String email){
        this.username = username;
        this.email = email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

}
