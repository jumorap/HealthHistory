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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AccessActivity extends AppCompatActivity {
public static final String user = "names";
TextView txtUser;
private ImageButton mSignout;
private ImageButton updateInfo;
private static FirebaseAuth mAuth;
DatabaseReference mDatabse ;

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
        updateInfo = (ImageButton) findViewById(R.id.updateInfo);

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
        updateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AccessActivity.this, UpdateInfoActivity.class));
            }
        });

        noRegisterMessage();
        //Enviar el id a las otras clases (MUY IMPORTANTE)

    }

//Obtener el nombre de acceso (Cualquier dato de la DB)
    public static class iden{
        String idFireBase = mAuth.getCurrentUser().getUid();
    }

    private void getUserInfo(){
        final String id = mAuth.getCurrentUser().getUid();

        mDatabse.child("Users").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String name = dataSnapshot.child("name").getValue().toString();

                    txtUser.setText("Bienvenido, " + name);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void noRegisterMessage(){
        FirebaseUser usuario = mAuth.getCurrentUser();
        if(!usuario.isEmailVerified()){
            String catched = txtUser.getText().toString();
            txtUser.setText(catched + "\nPor favor haga clic en el lápiz de abajo y complete sus datos médicos");
        }
    }


}
