package com.example.adrian.mobile.Models;

import android.app.job.JobScheduler;
import android.os.AsyncTask;
import android.util.JsonReader;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

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

    public void post(String url , JSONObject json){

    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();

    }
    @Override
    protected String doInBackground(String... strings) {
/*
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
*/

        try {
            URL url = new URL(apiUrl); // here is your URL path
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("nombre", "Juan" );
            jsonObject.put("codigo", 1 );
            jsonObject.put("titulo", "Hola" );
            //Log.e("params",postDataParams.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(jsonObject.toString());
            writer.flush();
            writer.close();
            os.close();
            int responseCode=conn.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                BufferedReader in=new BufferedReader(
                        new InputStreamReader(
                                conn.getInputStream()));
                StringBuffer sb = new StringBuffer("");
                String line="";
                while((line = in.readLine()) != null) {
                    sb.append(line);
                    break;
                }
                in.close();
                return sb.toString();
            }
            else {
                return new String("false : "+responseCode);
            }
        }
        catch(Exception e){
            return new String("Exception: " + e.getMessage());
        }


       // return null;
    }

    @Override
    protected void onPostExecute(String s){

    }
}
