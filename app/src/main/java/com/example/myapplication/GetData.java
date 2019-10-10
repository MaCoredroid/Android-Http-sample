package com.example.myapplication;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetData extends AsyncTask<String, Void, String> {
    private final OkHttpClient client = new OkHttpClient();
    public interface AsyncResponse {
        void processFinish(String output);
    }

    public AsyncResponse delegate = null;

    public GetData(AsyncResponse delegate){
        this.delegate = delegate;
    }
    @Override
    protected String doInBackground(String... strings) {
        String ans="";
        Request request = new Request.Builder()
                .header("api-key", "V5BBXsih6tOyXroC==dwuTCIRtA=")
                .url("http://api.heclouds.com/devices/553304452/datastreams/temperature")
                .get()
                .build();
        String responseData="";
        try {
            Response response = client.newCall(request).execute();
            responseData = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ans="温度："+responseData.substring(120, 126)+"°C";
        Request request1 = new Request.Builder()
                .header("api-key", "V5BBXsih6tOyXroC==dwuTCIRtA=")
                .url("http://api.heclouds.com/devices/553304452/datastreams/moisture")
                .get()
                .build();
        String responseData1="";
        try {
            Response response = client.newCall(request1).execute();
            responseData1 = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ans+="\n湿度："+responseData1.substring(114, 116)+"%";


        return ans;
    }

    @Override
    protected void onPostExecute(String result) {
        delegate.processFinish(result);
    }
}
