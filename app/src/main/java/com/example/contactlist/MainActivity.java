package com.example.contactlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.contactlist.Adapters.ContactListAdapter;
import com.example.contactlist.Models.ContactListModel;
import com.example.contactlist.Singleton.Singleton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public ArrayList<ContactListModel> contactList;
    private RecyclerView recyclerView;
    private Singleton singletonInstance;
    ListView listView;
    ContactListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.list);
        contactList = new ArrayList<ContactListModel>();
        singletonInstance = Singleton.getInstance();
        if(singletonInstance.getContactList() != null) {
            contactList = singletonInstance.getContactList();
        }
        setContactList();
        System.out.println("Test");
        setAdapter();
        FloatingActionButton fab = findViewById(R.id.fab);

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                System.out.println(viewHolder.getAdapterPosition());
                ContactListModel contactListData = contactList.get(viewHolder.getAdapterPosition());
                int position = viewHolder.getAdapterPosition();
                contactList.remove(viewHolder.getAdapterPosition());
                adapter.notifyItemRemoved(position);
            }
        });
        itemTouchhelper.attachToRecyclerView(recyclerView);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddContact.class);
                startActivity(intent);
            }
        });

    }

    private void setAdapter() {
        adapter = new ContactListAdapter(singletonInstance.getContactList());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    public void setContactList() {
        singletonInstance.setContactList(contactList);
        System.out.println(contactList);
    }
}