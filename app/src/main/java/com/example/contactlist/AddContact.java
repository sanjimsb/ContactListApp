package com.example.contactlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.contactlist.Adapters.ContactListAdapter;
import com.example.contactlist.Models.ContactListModel;
import com.example.contactlist.Singleton.Singleton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddContact extends AppCompatActivity  implements View.OnClickListener {
    Intent intent;
    private TextView nameTxt;
    private TextView phoneNum;
    private TextView email;
    private Button saveButton;
    private Singleton singletonInstance;
    private ArrayList<ContactListModel> contactList;
    private RecyclerView recyclerView;
    private int currentPosition;
    private boolean isFieldValid;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "UserInput";
    public static final String PREFERENCE_KEY = "ContactList";
    ContactListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        recyclerView = findViewById(R.id.list);
        contactList = new ArrayList<ContactListModel>();

        intent = getIntent();

        nameTxt = findViewById(R.id.nameEditText);
        phoneNum = findViewById(R.id.phoneEditText);
        email = findViewById(R.id.emailEditText);
        System.out.println();intent.getStringExtra("position");

        nameTxt.setText(intent.getStringExtra("name"));
        phoneNum.setText(intent.getStringExtra("phn"));
        email.setText(intent.getStringExtra("email"));


        singletonInstance = Singleton.getInstance();
        contactList = singletonInstance.getContactList();
        saveButton = findViewById(R.id.button);
        saveButton.setOnClickListener(this);


    }

    public void onClick(View view) {
        String getName = String.valueOf(nameTxt.getText());
//        Integer getPhn = Integer.valueOf(phoneNum.getText());
        String getEmail =  String.valueOf(email.getText());
        switch(view.getId()) {
            case R.id.button:
                isFieldValid = formValidation();
                if (isFieldValid) {
                    System.out.println(contactList.get(0));
                    contactList.remove(currentPosition);
                    contactList.add(currentPosition, new ContactListModel(getName, 455, getEmail));
                    singletonInstance.setContactList(contactList);
                    System.out.println(singletonInstance.getContactList().size());
                    saveData();
                    setAdapter();
                }
                break;
        }
    }

    private boolean formValidation() {
        if(nameTxt.length() == 0) {
            nameTxt.setError("This field is required");
            return false;
        }
        if(phoneNum.length() == 0) {
            phoneNum.setError("This field is required");
            return false;
        }
        if(email.length() == 0) {
            email.setError("This field is required");
            return false;
        } else {
            String regex = "^(.+)@(.+)$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(email.getText());
            if(matcher.matches() == false) {
                email.setError("Email is not valid");
                return false;
            }
        }

        return true;
    }

    private void setAdapter() {
        Intent intent = new Intent(AddContact.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    // saves dice added by the users to the shared preference.
    private void saveData() {
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(contactList);
        editor.putString(PREFERENCE_KEY,json);
        editor.commit();
    }
}