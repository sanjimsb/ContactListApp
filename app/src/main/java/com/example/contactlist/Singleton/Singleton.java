package com.example.contactlist.Singleton;

import android.content.Context;

import com.example.contactlist.AddContact;
import com.example.contactlist.Models.ContactListModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Singleton extends AddContact {
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
