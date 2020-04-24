package com.health;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

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

    /*
    String gender = "genero";
    if(radioGenderMan.isChecked()){
        gender = "Masculino";
    }else if(radioGenderWoman.isChecked()){
        gender = "Femenino";
    }
     */
}
