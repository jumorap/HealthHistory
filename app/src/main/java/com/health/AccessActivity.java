package com.health;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AccessActivity extends AppCompatActivity {
public static final String user = "names";
TextView txtUser;
private ImageButton mSignout;
private FirebaseAuth mAuth;
private DatabaseReference mDatabse ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access);

        txtUser = (TextView) findViewById(R.id.welcome);
        String user = getIntent().getStringExtra("names");
        //txtUser.setText("¡Bienvenido " + user + "!");


        mAuth = FirebaseAuth.getInstance();
        mDatabse = FirebaseDatabase.getInstance().getReference();
        mSignout = (ImageButton) findViewById(R.id.btnSignout);

        //Se invoca al método que escribe el correo en el ingreso
        getUserInfo();

        mSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(AccessActivity.this, MainActivity.class));
                finish();
            }
        });

    }

//Obtener el nombre de acceso (Cualquier dato de la DB)
    private void getUserInfo(){
        String id = mAuth.getCurrentUser().getUid();
        mDatabse.child("Users").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String email = dataSnapshot.child("email").getValue().toString();

                    txtUser.setText(email);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
