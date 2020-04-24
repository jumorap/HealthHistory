package com.health;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class HystoryAcces extends AppCompatActivity {
private EditText correo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hystory_acces);

        correo = (EditText) findViewById(R.id.email);

    }
}
