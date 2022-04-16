package com.example.contactlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.contactlist.Adapters.ContactListAdapter;
import com.example.contactlist.Models.ContactListModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<ContactListModel> contactList;
    private RecyclerView recyclerView;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.list);
        contactList = new ArrayList<ContactListModel>();
        setContactList();
        System.out.println("Test");
        setAdapter();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddContact.class);
                startActivity(intent);
            }
        });

    }

    private void setAdapter() {
        ContactListAdapter adapter = new ContactListAdapter(contactList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setContactList() {
        contactList.add(new ContactListModel("Bipin",9465, "bpmsb@gamil.com"));
        contactList.add(new ContactListModel("Sapin",9465, "bpmsb@gamil.com"));
        contactList.add(new ContactListModel("Sagar",9465, "bpmsb@gamil.com"));
        contactList.add(new ContactListModel("Ram",9465, "bpmsb@gamil.com"));
        contactList.add(new ContactListModel("Ram Hari",9465, "bpmsb@gamil.com"));
        contactList.add(new ContactListModel("Sanji",9465, "bpmsb@gamil.com"));
        System.out.println(contactList);
    }
}