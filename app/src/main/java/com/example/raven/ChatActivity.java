package com.example.raven;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.raven.Model.Chat;
import com.example.raven.Model.Key;
import com.example.raven.Model.Message;
import com.example.raven.Model.User;
import com.example.raven.crypt.Crypting;
import com.example.raven.utils.ApiService;
import com.example.raven.utils.Consts;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatActivity extends Activity {
    EditText editText;
    Button sendButton;
    TextView name;
    TextView id;
    ListView messageList;
    MessageAdapter adapter;

    private SecretKey key = null;

    User user;
    Chat chat;

    int chatPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        editText = (EditText) findViewById(R.id.sendText);
        sendButton = (Button) findViewById(R.id.sendButton);
        name = (TextView) findViewById(R.id.chAdrName);
        id = (TextView) findViewById(R.id.chAdrId);
        messageList = findViewById(R.id.messageList);

        user = Consts.user;

        chatPos = getIntent().getIntExtra("ChatNumber", 999);
        chat = MainActivity.chatsAr.get(chatPos);
        if (chat.getAdresatName() != null) {
            name.setText(chat.getAdresatName());
        } else {
            name.setText("UNKNOWN");
        }
        id.setText("id:" + String.valueOf(chat.getAdresatId()));
        adapter = new MessageAdapter(this, chat.getMessages());
        messageList.setAdapter(adapter);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message mes = new Message(user.getId(), chat.getAdresatId(), editText.getText().toString());
                mes.setTime(System.currentTimeMillis());
                chat.getMessages().add(mes);
                adapter.notifyDataSetChanged();
                System.out.println("local" + chat.getMessages());
                System.out.println("global" + MainActivity.chatsAr.get(chatPos).getMessages());

                String msg = "";

                try {
                    if (chat.getKey() != null) {
                        key = chat.getKey();
                    }else{
                        key = KeyGenerator.getInstance("DES").generateKey();
                    }
                    msg = Crypting.encrypt(editText.getText().toString(), key);
                } catch (Exception e) {
                    e.printStackTrace();
                }


                Consts.service.sendMessage(msg, user.getId(), chat.getAdresatId()).enqueue(new Callback<ServResponse>() {
                    @Override
                    public void onResponse(Call<ServResponse> call, Response<ServResponse> response) {
                        //name.setText(response.body().getMessage());
                    }

                    @Override
                    public void onFailure(Call<ServResponse> call, Throwable t) {
                        Toast.makeText(ChatActivity.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();
                        try {
                            throw (t);
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    }
                });

                GsonBuilder builder = new GsonBuilder();
                builder.registerTypeAdapter(SecretKey.class, new SecretKeyAdapter());
                Gson gson = builder.create();

                //String temp = gson.toJson(new Key(user.getId(), chat.getAdresatId(), key), Key.class);
                String temp = gson.toJson(key, SecretKey.class);
                //System.out.println(temp);
                //System.out.println(Consts.gson.fromJson(temp, Key.class).toString());
                Consts.service.sendKey(temp).enqueue(new Callback<ServResponse>() {
                    @Override
                    public void onResponse(Call<ServResponse> call, Response<ServResponse> response) {
                        //name.setText(response.body().getMessage());
                    }

                    @Override
                    public void onFailure(Call<ServResponse> call, Throwable t) {
                        Toast.makeText(ChatActivity.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();
                        try {
                            throw (t);
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    }
                });


                editText.setText("");
                onStop();
            }
        });

        //Message mes=new Message(78213222,"and");
        //chat.getMessages().add(mes);
        // adapter=new MessageAdapter(this,chat.getMessages());
        //messageList.setAdapter(adapter);

    }

    @Override
    protected void onStop() {
        super.onStop();
        //System.out.println(chat.getMessages());
    }
}
