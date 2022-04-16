package com.example.contactlist.Models;

public class ContactListModel {
    private String name;
    private int phoneNum;
    private String email;

    public ContactListModel(String name, int phoneNum, String email) {
        this.name = name;
        this.phoneNum = phoneNum;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(int phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
