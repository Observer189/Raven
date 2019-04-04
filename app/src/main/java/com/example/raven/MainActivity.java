package com.example.raven;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.raven.Model.Chat;
import com.example.raven.Model.Message;
import com.example.raven.Model.User;
import com.example.raven.crypt.Crypting;
import com.example.raven.utils.ApiService;
import com.example.raven.utils.Consts;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import javax.crypto.SecretKey;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends Activity {
    final String baseUrl="https://intense-waters-91005.herokuapp.com";
    //final String baseUrl = "http://192.168.1.105:8080";
    //final String baseUrl="http://192.168.1.104:8080";
    Context context = this;
    boolean isRegistred;
    User user;


    TextView textNameUser;
    TextView textIdUser;
    ImageButton contactButton;
    ListView chatList;
    Thread thread;

    static ChatsAdapter chatsAdapter;

   static ArrayList<Chat> chatsAr;
    //Имя файла настроек


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Consts.sp = getSharedPreferences(Consts.APP_PREFERENCES, Context.MODE_PRIVATE);
        Consts.editor = Consts.sp.edit();
        isRegistred = Consts.sp.contains(Consts.APP_PREFERENCES_NAME);
        Consts.retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Consts.service = Consts.retrofit.create(ApiService.class);

        Consts.gsonBuilder = new GsonBuilder();
        Consts.gsonBuilder.registerTypeAdapter(SecretKey.class, new SecretKeyAdapter());
        Consts.gson = Consts.gsonBuilder.create();


        textNameUser = (TextView) findViewById(R.id.text_userName);
        textIdUser=findViewById(R.id.text_userId);
        contactButton =  findViewById(R.id.contactButton);
        chatList = findViewById(R.id.chatList);
        Consts.user = new User();
        user = Consts.user;
        chatsAr = new ArrayList<Chat>();
        //loadChats();
        chatsAdapter = new ChatsAdapter(this, chatsAr);
        chatList.setAdapter(chatsAdapter);



        final Chat chat = new Chat();
        //chat.setAdresatName("");
        chat.setAdresatId(1111);
        Message mes = new Message(123, chat.getAdresatId(), "add");
        mes.setTime(System.currentTimeMillis());
        chat.getMessages().add(mes);
        //chatsAr.add(chat);
        loadChats();

        chatsAdapter.notifyDataSetChanged();




        chatList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ChatActivity.class);
                //String transfer=Consts.gson.toJson(chatsAr.get(position));
                //intent.putExtra("Chat",transfer);
                intent.putExtra("ChatNumber", position);
                startActivity(intent);
            }
        });

        if (!isRegistred) {
            LayoutInflater li = LayoutInflater.from(context);
            View registerView = li.inflate(R.layout.register, null);

           // AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(context);
            //mDialogBuilder.setView(registerView);

            final EditText nameText = registerView.findViewById(R.id.nameText);
            final EditText idText = registerView.findViewById(R.id.idText);

            final AlertDialog dialog = new AlertDialog.Builder(context)
                    .setView(registerView)
                    .setPositiveButton(android.R.string.ok, null) //Set to null. We override the onclick
                    //.setCancelable(false)
                    .create();
            dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface dialogInterface) {
                    Button posButton = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                    posButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if((!nameText.getText().toString().isEmpty()) && (!idText.getText().toString().isEmpty())&&(idText.getText().toString().length()<9)) {
                                Consts.editor.putString(Consts.APP_PREFERENCES_NAME, nameText.getText().toString());
                                Consts.editor.putInt(Consts.APP_PREFERENCES_Id, Integer.valueOf(idText.getText().toString()));
                                Consts.editor.apply();
                                user.setName(nameText.getText().toString());
                                user.setId(Integer.valueOf(idText.getText().toString()));
                                textNameUser.setText(user.getName());
                                textIdUser.setText("id:" + user.getId());
                                dialog.dismiss();
                            }
                            else
                            {

                                Toast toast=Toast.makeText(context, "You entered incorrect data", Toast.LENGTH_LONG);
                                toast.show();
                            }
                        }
                    });
                }
            });
            dialog.getWindow().setBackgroundDrawableResource(R.color.darcGray);
             dialog.show();
           /* mDialogBuilder
                    .setCancelable(false)
                    .setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int id) {
                                    Consts.editor.putString(Consts.APP_PREFERENCES_NAME, nameText.getText().toString());
                                    Consts.editor.putInt(Consts.APP_PREFERENCES_Id, Integer.valueOf(idText.getText().toString()));
                                    Consts.editor.apply();
                                    user.setName(nameText.getText().toString());
                                    user.setId(Integer.valueOf(idText.getText().toString()));
                                    textNameUser.setText(user.getName());
                                    textIdUser.setText("id:"+user.getId());

                                }

                            });
            //Создаем AlertDialog:
            AlertDialog alertDialog = mDialogBuilder.create();
            alertDialog.getWindow().setBackgroundDrawableResource(R.color.darcGray);
            //и отображаем его:
            alertDialog.show();*/



        }
        else {
            Consts.user.setName(Consts.sp.getString(Consts.APP_PREFERENCES_NAME, ""));
            Consts.user.setId(Consts.sp.getInt(Consts.APP_PREFERENCES_Id, 0));
            textNameUser.setText(user.getName());
            textIdUser.setText("id:"+user.getId());
        }

        contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ContactsActivity.class);
                startActivity(intent);
            }
        });

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.corbie_sound);

        thread = new Thread(new Runnable() {
            @Override
            public void run() {

                Timer timer = new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        //System.out.println("request");
                        Consts.service.getMessages(Consts.user.getId()).enqueue(new Callback<ArrayList<Message>>() {
                            @Override
                            public void onResponse(Call<ArrayList<Message>> call, Response<ArrayList<Message>> response) {
                                ArrayList<Message> resp = response.body();
                                if (resp != null){
                                    for (int i = 0; i < resp.size(); i++) {
                                        boolean isExist = false;
                                        mp.start();
                                        for (int j = 0; j < chatsAdapter.getCount(); j++) {
                                            if (resp.get(i).getAuthorId() == chatsAdapter.getItem(j).getAdresatId())//Если чат с адресантом существует то добавляем сообщение в массив
                                            {
                                                isExist = true;
                                                Message message = resp.get(i);

                                                GsonBuilder builder = new GsonBuilder();
                                                builder.registerTypeAdapter(SecretKey.class, new SecretKeyAdapter());
                                                Gson gson = builder.create();

                                                String keystr = message.getKey();

                                                SecretKey key = gson.fromJson(keystr, SecretKey.class);



                                                try {
                                                    String string = Crypting.decrypt(message.getText(), key);
                                                    message.setText(string);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                                chatsAdapter.getItem(j).getMessages().add(message);
                                                chatsAdapter.notifyDataSetChanged();
                                                if(ChatActivity.adapter!=null) {
                                                    ChatActivity.adapter.notifyDataSetChanged();
                                                }
                                            }
                                        }
                                        if (!isExist)//если же чата не существует то создаем его
                                        {
                                            System.out.println("Work please");
                                            Message message = resp.get(i);

                                            GsonBuilder builder = new GsonBuilder();
                                            builder.registerTypeAdapter(SecretKey.class, new SecretKeyAdapter());
                                            Gson gson = builder.create();

                                            System.out.println(message.getKey());

                                            SecretKey key = gson.fromJson(message.getKey(), SecretKey.class);

                                            final Chat chat = new Chat(key);

                                            chat.setAdresatId(resp.get(i).getAuthorId());



                                            try {
                                                System.out.println(Consts.gson.toJson(chat.getKey()));
                                                String string = Crypting.decrypt(message.getText(), chat.getKey());
                                                message.setText(string);
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            chatsAdapter.add(chat);
                                            chatsAdapter.getItem(chatsAdapter.getPosition(chat)).getMessages().add(message);
                                            chatsAdapter.notifyDataSetChanged();
                                            //loadChats();
                                            if(ChatActivity.adapter!=null) {
                                                ChatActivity.adapter.notifyDataSetChanged();
                                            }

                                        }
                                    }
                                }

                            }

                            @Override
                            public void onFailure(Call<ArrayList<Message>> call, Throwable t) {

                            }
                        });
                    }

                };

                timer.schedule(timerTask, 0, Consts.requestDelay);

            }
        });
        thread.start();
        textNameUser.setText(user.getName());
        textIdUser.setText("id:"+user.getId());
    }

    @Override
    protected void onRestart() {
        //loadChats();
        //chatsAdapter.notifyDataSetChanged();
        super.onRestart();
    }

    @Override
    public void onStop() {
        super.onStop();
        saveChats();
    }

    public static void saveChats() {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < chatsAdapter.getCount(); i++) {

            String temp = Consts.gson.toJson(chatsAdapter.getItem(i));
            set.add(temp);
        }

        Consts.editor.putStringSet(Consts.APP_PREFERENCES_CHATS, set);
        Consts.editor.apply();
    }

    public void loadChats() {
        chatsAdapter.clear();
        if (Consts.sp.contains(Consts.APP_PREFERENCES_CHATS)) {
            Set<String> set = Consts.sp.getStringSet(Consts.APP_PREFERENCES_CHATS, null);
            //ArrayList<Chat> tAr = new ArrayList<Chat>();
            System.out.println(set);
            for (String s : set) {
                Chat temp = Consts.gson.fromJson(s, Chat.class);
                chatsAdapter.add(temp);
                //tAr.add(temp);
                //chatsAr = tAr;

            }
        }
        //chatsAdapter.notifyDataSetChanged();
    }

}
