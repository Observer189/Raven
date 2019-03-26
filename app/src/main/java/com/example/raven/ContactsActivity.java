package com.example.raven;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.raven.Model.Contact;
import com.example.raven.utils.Consts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ContactsActivity extends Activity {
    ListView contactList;
    Button addButton;
    ContactAdapter adapter;

    ArrayList<Contact> contactAr;//для сохранения в листвью

    //Set<String> contactSet;//для сохранения в ретрофит
    public static final String APP_PREFERENCES_ContactList="ContactList";

    Context context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_contacts);
        contactList=(ListView) findViewById(R.id.contactList);
        addButton=findViewById(R.id.addButton);
        contactAr=new ArrayList<Contact>();

        loadContacts();

        adapter=new ContactAdapter(this,contactAr);
        contactList.setAdapter(adapter);
        final Contact Max= new Contact(989054056,"Max");


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater li=LayoutInflater.from(context);
                View registerView = li.inflate(R.layout.register, null);

                AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(context);
                mDialogBuilder.setView(registerView);

                final EditText nameText =  registerView.findViewById(R.id.nameText);
                final EditText idText =  registerView.findViewById(R.id.idText);

                mDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int id) {
                                        contactAr.add(new Contact(Integer.valueOf(idText.getText().toString()),nameText.getText().toString()));
                                        adapter.notifyDataSetChanged();
                                        System.out.println(contactAr.size());
                                    }

                                });
                //Создаем AlertDialog:
                AlertDialog alertDialog = mDialogBuilder.create();
                //и отображаем его:
                alertDialog.show();


            }
        });
    }
    public void saveContacts()
    {
        Set<String> set=new HashSet<String>();
        for(int i=0;i<contactAr.size();i++)
        {

            String temp=Consts.gson.toJson(contactAr.get(i));
            set.add(temp);
        }

        Consts.editor.putStringSet(Consts.APP_PREFERENCES_CONTACTS,set);
        Consts.editor.apply();
    }

    @Override
    protected void onRestart() {
        loadContacts();
        adapter.notifyDataSetChanged();
        super.onRestart();
    }

    public void loadContacts()
    {
        if(Consts.sp.contains(Consts.APP_PREFERENCES_CONTACTS)) {
            Set<String> set = Consts.sp.getStringSet(Consts.APP_PREFERENCES_CONTACTS, null);
            ArrayList<Contact> tAr=new ArrayList<Contact>();
            System.out.println(set);
            for (String s : set) {
                Contact temp = Consts.gson.fromJson(s, Contact.class);
                tAr.add(temp);
                contactAr=tAr;

            }
        }
    }
    public void onPause() {
        saveContacts();
        super.onPause();
    }

}