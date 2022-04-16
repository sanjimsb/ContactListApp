package com.example.contactlist.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactlist.AddContact;
import com.example.contactlist.MainActivity;
import com.example.contactlist.Models.ContactListModel;
import com.example.contactlist.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.MyViewHolder> {
    private ArrayList<ContactListModel> contactList;

    public ContactListAdapter(ArrayList<ContactListModel> getContactList) {
        this.contactList = getContactList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTxt;
        private TextView phoneNum;
        private TextView email;

        public MyViewHolder(final View view) {
            super(view);
            nameTxt = view.findViewById(R.id.textName);
            phoneNum = view.findViewById(R.id.textNumber);
            email = view.findViewById(R.id.textEmail);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FloatingActionButton fab = view.findViewById(R.id.fab);
                    fab.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            System.out.println("test");
                        }
                    });
                }
            });
        }
    }


    @NonNull
    @Override
    public ContactListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_contact_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactListAdapter.MyViewHolder holder, int position) {
        String name = contactList.get(position).getName();
        String phoneNum =  String.valueOf(contactList.get(position).getPhoneNum());
        String email = contactList.get(position).getEmail();
        holder.nameTxt.setText(name);
        holder.phoneNum.setText(phoneNum);
        holder.email.setText(email);
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }


}
