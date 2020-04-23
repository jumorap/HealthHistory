package com.health;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class AccessActivity extends AppCompatActivity {
public static final String user = "names";
TextView txtUser;
private ImageButton mSignout;
private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access);

        txtUser = (TextView) findViewById(R.id.welcome);
        String user = getIntent().getStringExtra("names");
        txtUser.setText("¡Bienvenido " + user + "!");

        mAuth = FirebaseAuth.getInstance();
        mSignout = (ImageButton) findViewById(R.id.btnSignout);

        mSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
            }
        });

    }
}
