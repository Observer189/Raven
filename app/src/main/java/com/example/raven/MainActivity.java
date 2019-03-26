package com.example.raven;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.raven.Model.Chat;
import com.example.raven.Model.Contact;
import com.example.raven.Model.Message;
import com.example.raven.Model.User;
import com.example.raven.utils.ApiService;
import com.example.raven.utils.Consts;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends Activity {
    //final String baseUrl="https://intense-waters-91005.herokuapp.com";
    final String baseUrl="http://192.168.0.56:8080";

    Context context=this;
    boolean isRegistred;
    User user;


    TextView text;
    Button contactButton;
    ListView chatList;
    ApiService service;
    ChatsAdapter adapter;

    ArrayList<Chat> chatsAr;
    //Имя файла настроек


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Consts.sp = getSharedPreferences(Consts.APP_PREFERENCES, Context.MODE_PRIVATE);
        Consts.editor= Consts.sp.edit();
        isRegistred= Consts.sp.contains(Consts.APP_PREFERENCES_NAME);
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service=retrofit.create(ApiService.class);

        Consts.gsonBuilder=new GsonBuilder();
        Consts.gson=Consts.gsonBuilder.create();


        Chat chat=new Chat();
        //chat.setAdresatName("");
        chat.setAdresatId(88888888);
        Message mes=new Message("Kostya",null);
        mes.setTime(System.currentTimeMillis());
        chat.setLastMessage(mes);


        adapter=new ChatsAdapter(this,chatsAr);

        setContentView(R.layout.activity_main);

        text = (TextView) findViewById(R.id.text_appName);
        contactButton = (Button) findViewById(R.id.contactButton);
        chatList=findViewById(R.id.chatList);
        user=new User();

        chatsAr=new ArrayList<Chat>();
        loadChats();

        adapter=new ChatsAdapter(this,chatsAr);
        chatList.setAdapter(adapter);

        if(!isRegistred) {
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
                                    Consts.editor.putString(Consts.APP_PREFERENCES_NAME, nameText.getText().toString());
                                    Consts.editor.putInt(Consts.APP_PREFERENCES_Id,Integer.valueOf(idText.getText().toString()));
                                    Consts.editor.apply();
                                }

                            });
            //Создаем AlertDialog:
            AlertDialog alertDialog = mDialogBuilder.create();
            //и отображаем его:
            alertDialog.show();


                }
                user.setName(Consts.sp.getString(Consts.APP_PREFERENCES_NAME,""));
                user.setId(Consts.sp.getInt(Consts.APP_PREFERENCES_Id,0));

                contactButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this, ContactsActivity.class);
                        startActivity(intent);
                    }
                });







    }
    @Override
    protected void onRestart() {
        loadChats();
        adapter.notifyDataSetChanged();
        super.onRestart();
    }
    @Override
    public void onStop()
    {
        super.onStop();
        saveChats();
    }
    public void saveChats()
    {
        Set<String> set=new HashSet<String>();
        for(int i=0;i<chatsAr.size();i++)
        {

            String temp=Consts.gson.toJson(chatsAr.get(i));
            set.add(temp);
        }

        Consts.editor.putStringSet(Consts.APP_PREFERENCES_CHATS,set);
        Consts.editor.apply();
    }
    public void loadChats()
    {
        if(Consts.sp.contains(Consts.APP_PREFERENCES_CHATS)) {
            Set<String> set = Consts.sp.getStringSet(Consts.APP_PREFERENCES_CHATS, null);
            ArrayList<Chat> tAr=new ArrayList<Chat>();
            System.out.println(set);
            for (String s : set) {
                Chat temp = Consts.gson.fromJson(s, Chat.class);
                tAr.add(temp);
                chatsAr=tAr;

            }
        }
    }

}
