package com.example.contactlist.Adapters;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactlist.AddContact;
import com.example.contactlist.ContactList;
import com.example.contactlist.MainActivity;
import com.example.contactlist.Models.ContactListModel;
import com.example.contactlist.R;
import com.github.ivbaranov.mli.MaterialLetterIcon;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Random;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.MyViewHolder> {
    private ArrayList<ContactListModel> contactList;

    public ContactListAdapter(ArrayList<ContactListModel> getContactList) {
        this.contactList = getContactList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTxt;
        private TextView phoneNum;
        private TextView email;
        private MaterialLetterIcon firstLetterHolder;

        public MyViewHolder(final View view) {
            super(view);
            nameTxt = view.findViewById(R.id.textName);
            phoneNum = view.findViewById(R.id.textNumber);
            email = view.findViewById(R.id.textEmail);
            firstLetterHolder = view.findViewById(R.id.firstName);

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

        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        holder.firstLetterHolder.setLetter(String.valueOf(name.charAt(0)));
        holder.firstLetterHolder.setShapeColor(color);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddContact.class);
                intent.putExtra("name",name);
                intent.putExtra("phn",phoneNum);
                intent.putExtra("email",email);
                intent.putExtra("position",String.valueOf(holder.getAdapterPosition()));
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }


}
