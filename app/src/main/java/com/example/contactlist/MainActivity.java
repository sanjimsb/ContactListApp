package com.example.contactlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.contactlist.Adapters.ContactListAdapter;
import com.example.contactlist.Models.ContactListModel;
import com.example.contactlist.Singleton.Singleton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public ArrayList<ContactListModel> contactList;
    private RecyclerView recyclerView;
    private Singleton singletonInstance;
    ListView listView;
    ContactListAdapter contactListAdapter;
    TextView getTextView;
    public static final String MyPREFERENCES = "UserInput";
    public static final String PREFERENCE_KEY = "ContactList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.list);
        contactList = new ArrayList<ContactListModel>();
        singletonInstance = Singleton.getInstance();

        // checks if there is any data in the shared preference
        if(loadData() != null) {
            singletonInstance.setContactList(loadData());
        }

        //if there is data on singleton class it assigns to the contactList variable
        if(singletonInstance.getContactList() != null) {
            contactList = singletonInstance.getContactList();
        }
        setContactList();
        setAdapter();
        FloatingActionButton floatingBtn = findViewById(R.id.fab);

        // handles the on swipe event, deletes the item from the contact list once swiped
        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                ContactListModel contactListData = contactList.get(viewHolder.getAdapterPosition());
                int position = viewHolder.getAdapterPosition();
                contactList.remove(viewHolder.getAdapterPosition());
                singletonInstance.setContactList(contactList);
                contactListAdapter.notifyItemRemoved(position);
                Toast toast = Toast.makeText(getApplicationContext(),"Contact Deleted", Toast.LENGTH_SHORT);
                toast.show();
                saveData();
            }

        });
        itemTouchhelper.attachToRecyclerView(recyclerView);

        // on click listener for floating button
        floatingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddContact.class);
                startActivity(intent);
            }
        });

    }

    // sets Adapter for recycler view
    private void setAdapter() {
        contactListAdapter = new ContactListAdapter(singletonInstance.getContactList());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(contactListAdapter);
    }

    // saves list to the singleton class
    public void setContactList() {
        singletonInstance.setContactList(contactList);
    }

    public ArrayList<ContactListModel> loadData() {
        // shared preferences.
        SharedPreferences sharedPreferences = getSharedPreferences("UserInput", Context.MODE_PRIVATE);

        // creating a variable for gson.
        Gson gson = new Gson();

        // shared prefs if not present setting it as null.
        String json = sharedPreferences.getString("ContactList", null);

        // get the type of our array list.
        Type type = new TypeToken<ArrayList<ContactListModel>>() {}.getType();

        // getting data from gson and saving it to array list
        ArrayList<ContactListModel> getContactArrayList = gson.fromJson(json, type);

        return getContactArrayList;
    }

    // saves contact details added by the users to the shared preference.
    public void saveData() {
        SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(contactList);
        editor.putString(PREFERENCE_KEY,json);
        editor.commit();
    }
}