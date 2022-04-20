package com.example.contactlist.Models;

public class ContactListModel {
    private String name;
    private String phoneNum;
    private String email;
    private String contactType;

    public ContactListModel(String name, String phoneNum, String email, String contactType) {
        this.name = name;
        this.phoneNum = phoneNum;
        this.email = email;
        this.contactType = contactType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactType() {
        return contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }
}
