package com.example.adrian.mobile.Models;

import android.app.job.JobScheduler;
import android.os.AsyncTask;
import android.util.JsonReader;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ClassRest  extends AsyncTask<String, String, String> {

    private String apiUrl;
    private  String metodo;

    public ClassRest(String apiUrl, String metodo) {
        this.apiUrl = apiUrl;
        this.metodo = metodo;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();

    }
    @Override
    protected String doInBackground(String... strings) {

        String response = "";
        URL url ;
        HttpURLConnection urlConnection = null;
        JSONObject json = null;
        JSONArray jsonArray = null;
        JsonReader readJson = null;

        try {
            url = new URL(apiUrl);
            urlConnection =  (HttpURLConnection) url.openConnection();
            //urlConnection.setRequestMethod(metodo);


            InputStream in = urlConnection.getInputStream();
            InputStreamReader isw = new InputStreamReader(in,"UTF-8");
            BufferedReader  stringBufer= new BufferedReader(isw);
            
            String data = "";
            while ((data = stringBufer.readLine()) != null)
                response +=data;
            jsonArray = new JSONArray(response);

        }catch (Exception e){

        }


        return null;
    }

    @Override
    protected void onPostExecute(String s){

    }
}
