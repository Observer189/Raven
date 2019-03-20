package com.example.raven;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button btn;
    TextView text;
    ApiService service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://192.168.0.56:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service=retrofit.create(ApiService.class);
        editText=(EditText)findViewById(R.id.editText);
        btn=(Button)findViewById(R.id.button);
        text=(TextView)findViewById(R.id.textV);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                service.getResponse(editText.getText().toString()).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        text.setText(response.body());
                }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();
                        try {
                            throw(t);
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    }
                });


            }
        });
    }
}
