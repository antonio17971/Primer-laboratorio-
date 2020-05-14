package com.example.adrian.mobile.Models;

import android.app.job.JobScheduler;
import android.os.AsyncTask;
import android.util.JsonReader;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ClassRest  extends AsyncTask<String, String, String> {

    private String apiUrl;
    private  String metodo;
    private String json;

    public ClassRest(String apiUrl, String metodo) {
        this.apiUrl = apiUrl;
        this.metodo = metodo;
    }

    public ClassRest(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public ClassRest() {
        this.apiUrl = "";
        this.metodo = "";
        this.json = "";
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

    public JSONArray getLista(String url) throws  JSONException {
        this.apiUrl = url;
        this.metodo = "GET";
        this.execute();
        JSONArray jsonArray = null;
        jsonArray = new JSONArray(json);
        return jsonArray;
    }

    public JSONObject getObjeto(String url) throws JSONException {
        this.apiUrl = url;
        this.metodo = "GET";
        this.execute();
        JSONObject jsonObject = null;
        jsonObject = new JSONObject(json);
        return jsonObject;
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();

    }
    @Override
    protected String doInBackground(String... strings) {

        URL url ;
        HttpURLConnection urlConnection = null;

        try {
            url = new URL(this.apiUrl);
            urlConnection =  (HttpURLConnection) url.openConnection();
            //urlConnection.setRequestMethod(metodo);

            InputStream in = urlConnection.getInputStream();
            InputStreamReader isw = new InputStreamReader(in,"UTF-8");
            BufferedReader  stringBufer= new BufferedReader(isw);

            String data = "";
            while ((data = stringBufer.readLine()) != null)
                json +=data;
        }catch (Exception e){
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(String s){

    }
}
