package com.example.raven;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.raven.Model.Contact;

import java.util.List;

public class ContactAdapter extends ArrayAdapter<Contact> {

    public ContactAdapter(@NonNull Context context, List array) {
        super(context,R.layout.contact_item ,array);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
         Contact contact=getItem(position);

        if(convertView==null)
        {
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.contact_item,null);
        }

        ((TextView)(convertView.findViewById(R.id.contactText1))).setText(contact.getName());
        ((TextView)(convertView.findViewById(R.id.contactText2))).setText("id:"+String.valueOf(contact.getId()));
        return convertView;
    }
}
