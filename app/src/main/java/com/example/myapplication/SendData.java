package com.example.myapplication;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SendData extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... strings) {
        String ans = "{\"datastreams\": [{\"id\": \"threshold\",\"datapoints\": [{\"value\": \"";
        ans+=strings[0];
        ans+="\"}]}]}";
        Log.d("send",ans);
        final OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON,ans);
        Request request2 = new Request.Builder()
                .header("api-key", "V5BBXsih6tOyXroC==dwuTCIRtA=")
                .url("http://api.heclouds.com/devices/553304452/datapoints")
                .post(body)
                .build();
        try {
            Response response = client.newCall(request2).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
