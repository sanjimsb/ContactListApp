package com.example.contactlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.contactlist.Adapters.ContactListAdapter;
import com.example.contactlist.Models.ContactListModel;
import com.example.contactlist.Singleton.Singleton;

import java.util.ArrayList;

public class AddContact extends AppCompatActivity  implements View.OnClickListener {
    Intent intent;
    private TextView nameTxt;
    private TextView phoneNum;
    private TextView email;
    private Button saveButton;
    private Singleton singletonInstance;
    private ArrayList<ContactListModel> contactList;
    private RecyclerView recyclerView;
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

        nameTxt.setText(intent.getStringExtra("name"));
        phoneNum.setText(intent.getStringExtra("phn"));
        email.setText(intent.getStringExtra("email"));


        singletonInstance = Singleton.getInstance();
        contactList = singletonInstance.getContactList();
        saveButton = findViewById(R.id.button);
        saveButton.setOnClickListener(this);


    }

    public void onClick(View view) {
        int getCurrentDice;
        String getName = String.valueOf( nameTxt.getText());
//        int getPhn = (int) phoneNum.getText();
        String getEmail =  String.valueOf(email.getText());
        switch(view.getId()) {
            case R.id.button:
                contactList.add(new ContactListModel(getName,455, getEmail));
                singletonInstance.setContactList(contactList);
                System.out.println(singletonInstance.getContactList().size());
                setAdapter();
                break;
        }
    }

    private void setAdapter() {
        Intent intent = new Intent(AddContact.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
        overridePendingTransition(0, 0);

    }
}