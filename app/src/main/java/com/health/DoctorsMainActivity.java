package com.health;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class DoctorsMainActivity extends AppCompatActivity {

    private Spinner spinnerMedico;
    private Spinner spinnerHora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctors_activity_main);

        spinnerMedico = findViewById(R.id.spinnerMedico);

        ArrayList<String> elementosArray = new ArrayList<>();
        elementosArray.add("Seleccione su médico");
        elementosArray.add("Medico1");
        elementosArray.add("Medico2");
        elementosArray.add("Medico3");


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

        spinnerHora = findViewById(R.id.spinnerHora);

        ArrayList<String> elementosHora = new ArrayList<>();
        elementosHora.add("Seleccione hora de consulta");
        elementosHora.add("9:00");
        elementosHora.add("9:20");
        elementosHora.add("9:40");


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