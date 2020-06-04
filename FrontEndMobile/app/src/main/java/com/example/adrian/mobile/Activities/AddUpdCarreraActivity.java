package com.example.adrian.mobile.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.adrian.mobile.Models.CarreraModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.adrian.mobile.R;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class AddUpdCarreraActivity extends AppCompatActivity {

    private FloatingActionButton fBtn;
    private boolean editable = true;
    private EditText codFld;
    private EditText nomFld;
    private EditText tituloFld;
    private  static final  String URL_POST =  "http://192.168.0.119:8080/ServerWeb/incertarCarrera";
    private  static final  String URL_PUT =  "http://192.168.0.119:8080/ServerWeb/actualizarCarrera";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_upd_carrera);

        editable = true;

        // button check
        fBtn = findViewById(R.id.addUpdCarreraBtn);

        //cleaning stuff
        codFld = findViewById(R.id.codigoAddUpdCarrera);
        nomFld = findViewById(R.id.nombreAddUpdCarrera);
        tituloFld = findViewById(R.id.tituloAddUpdCarrera);
        codFld.setText("");
        nomFld.setText("");
        tituloFld.setText("");

        //receiving data from admCarreraActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            editable = extras.getBoolean("editable");
            if (editable) {   // is editing some row
                CarreraModel aux = (CarreraModel) getIntent().getSerializableExtra("carrera");
                codFld.setText(String.valueOf(aux.getCodigo()));
                codFld.setEnabled(false);
                nomFld.setText(aux.getNombre());
                tituloFld.setText(aux.getTitulo());
                //edit action
                fBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        editCarrera();
                    }
                });
            } else {         // is adding new CarreraModel object
                //add new action
                fBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        addCarrera();
                    }
                });
            }
        }
    }

    public void addCarrera() {
        if (validateForm()) {
            //do something
            CarreraModel car = new CarreraModel(Integer.parseInt(codFld.getText().toString()), nomFld.getText().toString(), tituloFld.getText().toString());
            IncertCarrera incertar = new IncertCarrera(URL_POST, car);
            incertar.execute();
            Intent intent = new Intent(getBaseContext(), AdmCarreraActivity.class);
            //sending carrera data
            intent.putExtra("addCarrera", car);
            startActivity(intent);
            finish(); //prevent go back
        }
    }

    public void editCarrera() {
        if (validateForm()) {
            CarreraModel car = new CarreraModel(Integer.parseInt(codFld.getText().toString()), nomFld.getText().toString(), tituloFld.getText().toString());
            UpdateCarrera actualizar = new UpdateCarrera(URL_PUT,car);
            actualizar.execute();
            Intent intent = new Intent(getBaseContext(), AdmCarreraActivity.class);
            //sending carrera data
            intent.putExtra("editCarrera", car);
            startActivity(intent);
            finish(); //prevent go back
        }
    }

    public boolean validateForm() {
        int error = 0;
        if (TextUtils.isEmpty(this.nomFld.getText())) {
            nomFld.setError("Nombre requerido");
            error++;
        }
        if (TextUtils.isEmpty(this.codFld.getText())) {
            codFld.setError("Codigo requerido");
            error++;
        }
        if (TextUtils.isEmpty(this.tituloFld.getText())) {
            tituloFld.setError("Titulo requerido");
            error++;
        }
        if (error > 0) {
            Toast.makeText(getApplicationContext(), "Algunos errores", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    class UpdateCarrera extends AsyncTask<String, Integer, String> {
        private String apiUrl;
        private CarreraModel carrera;

        public UpdateCarrera(String URL, CarreraModel carrera){
            this.apiUrl= URL;
            this.carrera = carrera;
        }
        protected String doInBackground(String... urls) {
            URL url ;
            HttpURLConnection urlConnection = null;
            String query ="";
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("codigo",carrera.getCodigo());
            jsonObject.addProperty("nombre",carrera.getNombre());
            jsonObject.addProperty("titulo",carrera.getTitulo());
            try {
                query = String.format(apiUrl);
                url = new URL(query);
                urlConnection =  (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("PUT");
                urlConnection.setReadTimeout(15000 /* milliseconds */);
                urlConnection.setConnectTimeout(15000 /* milliseconds */);
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);
                OutputStream os = urlConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(jsonObject.toString());
                writer.flush();
                writer.close();
                os.close();

                int responseCode = urlConnection.getResponseCode();
                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(
                                    urlConnection.getInputStream()));
                    StringBuffer sb = new StringBuffer("");
                    String line = "";
                    while ((line = in.readLine()) != null) {
                        sb.append(line);
                        break;
                    }
                    in.close();
                    return sb.toString();
                } else {
                    return new String("false : " + responseCode);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onProgressUpdate(Integer... progress) {
            // optionally report progress
        }

        protected void onPostExecute(String result) {
            // do something on the UI thread
        }
    }


    class IncertCarrera extends AsyncTask<String, Integer, String> {
        private String apiUrl;
        private CarreraModel carrera;

        public IncertCarrera(String URL, CarreraModel carrera){
            this.apiUrl= URL;
            this.carrera = carrera;
        }
        protected String doInBackground(String... urls) {
            URL url ;
            HttpURLConnection urlConnection = null;
            String query ="";
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("codigo",carrera.getCodigo());
            jsonObject.addProperty("nombre",carrera.getNombre());
            jsonObject.addProperty("titulo",carrera.getTitulo());
            try {
                query = String.format(apiUrl);
                url = new URL(query);
                urlConnection =  (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setReadTimeout(15000 /* milliseconds */);
                urlConnection.setConnectTimeout(15000 /* milliseconds */);
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);
                OutputStream os = urlConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(jsonObject.toString());
                writer.flush();
                writer.close();
                os.close();

                int responseCode = urlConnection.getResponseCode();
                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(
                                    urlConnection.getInputStream()));
                    StringBuffer sb = new StringBuffer("");
                    String line = "";
                    while ((line = in.readLine()) != null) {
                        sb.append(line);
                        break;
                    }
                    in.close();
                    return sb.toString();
                } else {
                    return new String("false : " + responseCode);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onProgressUpdate(Integer... progress) {
            // optionally report progress
        }

        protected void onPostExecute(String result) {
            // do something on the UI thread
        }
    }
}
