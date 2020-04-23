package com.health;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Objetos visibles
    private EditText textEmail;
    private EditText textPass;
    private Button btnRegist;
    private Button btnlogin;

    private TextView notif;
    private TextView resetPassword;

    //Firebase Auth
    private FirebaseAuth firebaseAuth;

    //Database
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicialización firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();//Nodo principal DB

        //Referenciar los views
        textEmail = (EditText) findViewById(R.id.email);
        textPass = (EditText) findViewById(R.id.pass);

        btnRegist = (Button) findViewById(R.id.regist);
        btnlogin = (Button) findViewById(R.id.signin);

        notif = (TextView) findViewById(R.id.notif);
        resetPassword = (TextView) findViewById(R.id.resetPassworda);

        //Listener
        btnRegist.setOnClickListener(this);
        btnlogin.setOnClickListener(this);


        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ResetPasswordActivity.class));
            }
        });
    }


    private void registrarUsuario(){
        //Se convierte a texto el ingreso del correo y la contraseña
        final String email = textEmail.getText().toString().trim();
        final String password = textPass.getText().toString().trim();

        //Se verifica el estado del ingreso (si está vacío)
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Se debe ingresar un email",Toast.LENGTH_LONG).show();
            notif.setText("Se debe ingresar un email");
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Se debe ingresar una contraseña",Toast.LENGTH_LONG).show();
            notif.setText("Se debe ingresar una contraseña");
            return;
        }

        if(password.length()<6){
            notif.setText("La contraseña debe contener más de 6 caracteres");
            return;
        }

        //Inicio de creación
        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this,"Se ha registrado exitosamente",Toast.LENGTH_LONG).show();
                            FirebaseUser usuario = firebaseAuth.getCurrentUser();
                            usuario.sendEmailVerification();
                            textEmail.setText("");
                            textPass.setText("");
                            notif.setText("Se ha registrado con éxtio, VERIFIQUE SU CORREO e ingrese nuevamente sus credenciales");

                            //TOMAR DATOS Y ENVIARLOS A REALTIEM DB
                            Map<String, Object> map = new HashMap<>();
                            map.put("email", email);
                            map.put("password", password);

                            String id = firebaseAuth.getCurrentUser().getUid();
                            mDatabase.child("Users").child(id).setValue(map);/*.addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> taskB) {
                                    if(taskB.isSuccessful()){
                                        startActivity(new Intent(MainActivity.this,AccessActivity.class));
                                        finish();
                                    }
                                }
                            });*/

                        }else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(MainActivity.this, "El usuario ya está registrado", Toast.LENGTH_LONG).show();
                                notif.setText("El usuario ya está registrado");
                            } else {
                                Toast.makeText(MainActivity.this, "No se ha registrado, verifica tu conexión", Toast.LENGTH_LONG).show();
                                notif.setText("No se ha registrado, verifica tu conexión");
                            }
                        }
                    }
                });

    }

    private void loguearUsusario(){
        //Se convierte a texto el ingreso del correo y la contraseña
        final String email = textEmail.getText().toString().trim();
        String password = textPass.getText().toString().trim();

        //Se verifica el estado del ingreso (si está vacío)
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Se debe ingresar un email",Toast.LENGTH_LONG).show();
            notif.setText("Se debe ingresar un email");
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Se debe ingresar una contraseña",Toast.LENGTH_LONG).show();
            notif.setText("Se debe ingresar una contraseña");
            return;
        }

        //Logueo usuario
        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        FirebaseUser usuario = firebaseAuth.getCurrentUser();
                        if(!usuario.isEmailVerified()){
                            Toast.makeText(MainActivity.this, "Correo electrónico no verificado", Toast.LENGTH_LONG).show();
                            notif.setText("Correo electrónico no verificado");
                        }else {

                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Bienvenido", Toast.LENGTH_LONG).show();
                                int pos = email.indexOf("@");
                                String user = email.substring(0, pos);
                                Intent intent = new Intent(getApplication(), AccessActivity.class);
                                intent.putExtra(AccessActivity.user, user);
                                startActivity(intent);
                                textEmail.setText("");
                                textPass.setText("");
                                notif.setText("Acceso exitoso");
                                finish();
                            } else {
                                Toast.makeText(MainActivity.this, "Usuario o contraseña incorrectos\nPuede que su conexión sea inestable", Toast.LENGTH_LONG).show();
                                notif.setText("Usuario o contraseña incorrectos\nPuede que su conexión sea inestable");
                            }
                        }
                        }
                    });

    }



    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.regist:
                //Se llama el método descrito antes
                registrarUsuario();
                break;
            case R.id.signin:
                loguearUsusario();
        }
    }

    @Override
    protected void onStart(){
        super.onStart();

        if(firebaseAuth.getCurrentUser() != null){
            startActivity(new Intent(MainActivity.this, AccessActivity.class));
            finish();
        }
    }
}
