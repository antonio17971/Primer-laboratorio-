package com.example.adrian.mobile.Activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.adrian.mobile.LogicaNegocio.CarreraModel;
import com.example.adrian.mobile.LogicaNegocio.CursoModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.adrian.mobile.AccesoDatos.Model;
import com.example.adrian.mobile.R;

import java.util.ArrayList;
import java.util.List;

public class AddUpdCursoActivity extends AppCompatActivity {
    private FloatingActionButton fBtn;
    private boolean editable = true;
    private EditText codFld;
    private Spinner carreras, ciclos;
    private EditText nomFld;
    private EditText creditosFld;
    private EditText horasFld;
    private EditText anhoFld;

    private Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_upd_curso);
        editable = true;

        // button check
        fBtn = findViewById(R.id.addUpdCursoBtn);

        //cleaning stuff
        codFld = findViewById(R.id.codigoAddUpdCurso);
        carreras = (Spinner) findViewById(R.id.sp_carreras);
        ciclos = (Spinner) findViewById(R.id.sp_ciclos);
        nomFld = findViewById(R.id.nombreAddUpdCurso);
        creditosFld = findViewById(R.id.creditosAddUpdCurso);
        horasFld = findViewById(R.id.horasAddUpdCiclo);
        anhoFld = findViewById(R.id.anhoAddUpdCurso);
        codFld.setText("");
        nomFld.setText("");
        creditosFld.setText("");
        horasFld.setText("");
        anhoFld.setText("");

        model = new Model();

        loadCarreras();
        loadCiclos();

        //receiving data from admCursoActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            editable = extras.getBoolean("editable");
            if (editable) {   // is editing some row
                CursoModel aux = (CursoModel) getIntent().getSerializableExtra("curso");
                codFld.setText(aux.getCodigo());
                codFld.setEnabled(false);
                nomFld.setText(aux.getNombre());
                creditosFld.setText(Integer.toString(aux.getCreditos()));
                horasFld.setText(Integer.toString(aux.getHoras()));
                //edit action
                fBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        editCurso();
                    }
                });
            } else {         // is adding new CarreraModel object
                //add new action
                fBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        addCurso();
                    }
                });
            }
        }
    }

    public void addCurso() {
        if (validateForm()) {
            //do something
            CarreraModel carr = buscarCarrera(model.getCarreras(), (CarreraModel)carreras.getSelectedItem());
//            Ciclo cic = buscarCiclo(model.getCicloList(), ciclos.getSelectedItem().toString());

            CursoModel cur = new CursoModel(
                    Integer.parseInt(codFld.getText().toString()),
                    Integer.parseInt(creditosFld.getText().toString()),
                    Integer.parseInt(horasFld.getText().toString()),
                    nomFld.getText().toString(),
                    anhoFld.getText().toString(),
                    ciclos.getSelectedItem().toString()
            );

            Intent intent = new Intent(getBaseContext(), AdmCursoActivity.class);
            //sending curso data
            intent.putExtra("addCurso", cur);
            startActivity(intent);
            finish(); //prevent go back
        }
    }

    public void editCurso() {
        if (validateForm()) {
            CarreraModel carr = buscarCarrera(model.getCarreras(), (CarreraModel)carreras.getSelectedItem());

            CursoModel cur = new CursoModel(
                    Integer.parseInt(codFld.getText().toString()),
                    Integer.parseInt(creditosFld.getText().toString()),
                    Integer.parseInt(horasFld.getText().toString()),
                    nomFld.getText().toString(),
                    anhoFld.getText().toString(),
                    ciclos.getSelectedItem().toString()
                    );
            Intent intent = new Intent(getBaseContext(), AdmCursoActivity.class);
            //sending curso data
            intent.putExtra("editCurso", cur);
            startActivity(intent);
            finish(); //prevent go back
        }
    }

    public CarreraModel buscarCarrera(List<CarreraModel> carreraList, CarreraModel carrera){
        for (CarreraModel c : carreraList) {
            if (c.getCodigo() == carrera.getCodigo()) {
                return c;
            }
        }
        return null;
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
        if (TextUtils.isEmpty(this.creditosFld.getText())) {
            creditosFld.setError("Creditos requerido");
            error++;
        }
        if (TextUtils.isEmpty(this.horasFld.getText())) {
            horasFld.setError("Horas requerido");
            error++;
        }
        if (TextUtils.isEmpty(this.anhoFld.getText())) {
            anhoFld.setError("Año requerido");
            error++;
        }
        if (error > 0) {
            Toast.makeText(getApplicationContext(), "Algunos errores", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private void loadCiclos() {
        ArrayList<String> ciclosList = new ArrayList<>();
        ciclosList.add("I Ciclo");
        ciclosList.add("II Ciclo");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, ciclosList);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        ciclos.setAdapter(adapter);
    }

    private void loadCarreras() {
        // im not sure about this
        ArrayAdapter<CarreraModel> adapter = new ArrayAdapter<CarreraModel>(this, R.layout.support_simple_spinner_dropdown_item, model.getCarreras());
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        carreras.setAdapter(adapter);
    }

    private int getIndex(Spinner spinner, String cod) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(cod)) {
                return i;
            }
        }

        return 0;
    }
}
