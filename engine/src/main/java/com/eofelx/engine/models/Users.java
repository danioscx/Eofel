package com.eofelx.engine.models;

public class Users {

    private String token;
    private String id;
    private String username;
    private String email;
    private String password;

    private static Users instance;

    public Users withToken(String token) {
        this.token = token;
        return this;
    }

    public Users withId(String id) {
        this.id = id;
        return this;
    }

    public Users withUsername(String username) {
        this.username = username;
        return this;
    }

    public Users withEmail(String email) {
        this.email = email;
        return this;
    }

    public Users withPassword(String password) {
        this.password = password;
        return this;
    }

    public String getToken() {
        return token;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Users() {

    }

    public static Users getInstance() {
        if (instance == null)
            instance = new Users();
        return instance;
    }
}
