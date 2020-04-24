package com.health;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class UpdateInfoActivity extends AppCompatActivity {
private RadioButton radioGenderMan;
private RadioButton radioGenderWoman;
private EditText city;
private EditText local;
private EditText address;
private EditText birthDay;
private EditText civil;
private EditText blood;
private EditText nameEmergency;
private EditText phoneEmergency;
private Button upload;

private FirebaseAuth firebaseAuth;

//Database
DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info);

        radioGenderMan = (RadioButton) findViewById(R.id.radioGenderMan);
        radioGenderWoman = (RadioButton) findViewById(R.id.radioGenderWoman);

        city = (EditText) findViewById(R.id.city);
        local = (EditText) findViewById(R.id.local);
        address = (EditText) findViewById(R.id.address);
        birthDay = (EditText) findViewById(R.id.birthDay);
        civil = (EditText) findViewById(R.id.civil);
        blood = (EditText) findViewById(R.id.blood);
        nameEmergency = (EditText) findViewById(R.id.nameEmergency);
        phoneEmergency = (EditText) findViewById(R.id.phoneEmergency);
        upload = (Button) findViewById(R.id.update);
    }
    private void uploadInfo(){
        String gender = "genero";
        if(radioGenderMan.isChecked()) gender = "Masculino";
        else if(radioGenderWoman.isChecked()) gender = "Femenino";
        String cityR = city.getText().toString().trim();
        String localR = local.getText().toString().trim();
        String addressR = address.getText().toString().trim();
        String birthDayR = birthDay.getText().toString().trim();
        String civilR = civil.getText().toString().trim();
        String bloodR = blood.getText().toString().trim();
        String nameEmergencyR = nameEmergency.getText().toString().trim();
        String phoneEmergencyR = phoneEmergency.getText().toString().trim();
        String uploadR = upload.getText().toString().trim();
    }



}
