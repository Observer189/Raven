package com.example.raven;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.raven.Model.User;
import com.example.raven.utils.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatActivity extends AppCompatActivity {
    EditText editText;
    Button sendButton;
    TextView text;
    ApiService service;

    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        editText = (EditText) findViewById(R.id.editText);
        sendButton = (Button) findViewById(R.id.button);
        text = (TextView) findViewById(R.id.textV);



        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                service.sendMessage(editText.getText().toString(),user.getId()).enqueue(new Callback<ServResponse>() {
                    @Override
                    public void onResponse(Call<ServResponse> call, Response<ServResponse> response) {
                        text.setText(response.body().getMessage());
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

            }
        });
    }
}
