package com.oaec.eshop.entity;

public class User {
        private Integer userId;
        private String username;
        private String password;
        private Boolean status;
        private String telNumber;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String telNumber) {
        this.username = username;
        this.password = password;
        this.telNumber = telNumber;
    }

    public User(Integer userId, String username, String password, Boolean status, String telNumber) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.status = status;
        this.telNumber = telNumber;
    }




    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


}
