package com.health;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class AccessActivity extends AppCompatActivity {
public static final String user = "names";
TextView txtUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access);

        txtUser = (TextView) findViewById(R.id.welcome);
        String user = getIntent().getStringExtra("names");
        txtUser.setText("¡Bienvenido " + user + "!");
    }
}
