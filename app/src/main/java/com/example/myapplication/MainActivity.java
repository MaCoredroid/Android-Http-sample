package com.example.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView = findViewById(R.id.textView2);
        new CountDownTimer(300000000, 1000) {
            String ans="";
            public void onTick(long millisUntilFinished) {
                AsyncTask asyncTask = new GetData(new GetData.AsyncResponse(){

                    @Override
                    public void processFinish(String output){
                        ans=output;
                    }
                }).execute();

                textView.setText(ans);
            }

            public void onFinish() {
                textView.setText("done!");
            }
        }.start();
    }

    public void sendMessage(View view) {
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        AsyncTask asyncTask = new SendData().execute(message);

    }



}


