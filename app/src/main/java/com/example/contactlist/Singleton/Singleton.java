package com.example.contactlist.Singleton;

import com.example.contactlist.Models.ContactListModel;

import java.util.ArrayList;

public class Singleton {
    public ArrayList<ContactListModel> contactList = new ArrayList<ContactListModel>();
    private static final Singleton singletonInstance = new Singleton();

    public static Singleton getInstance() {
        return singletonInstance;
    }

    private Singleton(){}

    public void setContactList(ArrayList<ContactListModel> getContactList) {
        this.contactList = getContactList;
    }

    public ArrayList<ContactListModel> getContactList() {
        return contactList;
    }
}
