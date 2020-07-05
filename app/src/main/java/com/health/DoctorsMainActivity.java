package com.health;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class DoctorsMainActivity extends AppCompatActivity {

    private Spinner spinnerMedico;
    private Spinner spinnerHora;
    private Button buttonCancel;
    private Button buttonConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctors_activity_main);

        buttonConfirm = (Button) findViewById(R.id.buttonConfirm);
        buttonCancel = (Button) findViewById(R.id.buttonCancel);
        spinnerMedico = findViewById(R.id.spinnerMedico);
        spinnerHora = findViewById(R.id.spinnerHora);

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorsMainActivity.this,MaintenanceActivity.class));
                Toast.makeText(DoctorsMainActivity.this, "Lo sentimos, continuamos trabajando en esta función", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ArrayList<String> elementosArray = new ArrayList<>();
        elementosArray.add("Seleccione su médico");
        elementosArray.add("Óscar Alexander Guevara Cruz");
        elementosArray.add("Delma Lucía Zea Llanos");
        elementosArray.add("Fabio Ernesto Grosso Ospina");


        ArrayAdapter adp = new ArrayAdapter(DoctorsMainActivity.this, android.R.layout.simple_spinner_dropdown_item,elementosArray);

        spinnerMedico.setAdapter(adp);
        spinnerMedico.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String elementoArray = (String) spinnerMedico.getAdapter().getItem(position);

                //Toast.makeText(DoctorsMainActivity.this, "Médico: "+elementoArray, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //----------------------------------------------------------------------------------------------------


        ArrayList<String> elementosHora = new ArrayList<>();
        elementosHora.add("Seleccione hora de consulta");
        elementosHora.add("9:00");
        elementosHora.add("9:20");
        elementosHora.add("10:00");
        elementosHora.add("10:20");
        elementosHora.add("10:40");
        elementosHora.add("11:00");
        elementosHora.add("11:20");


        ArrayAdapter adpHora = new ArrayAdapter(DoctorsMainActivity.this, android.R.layout.simple_spinner_dropdown_item,elementosHora);

        spinnerHora.setAdapter(adpHora);
        spinnerHora.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String elementoHora = (String) spinnerHora.getAdapter().getItem(position);

                //Toast.makeText(DoctorsMainActivity.this, "Hora: " +elementoHora, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }
}
