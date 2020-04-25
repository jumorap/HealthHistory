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

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
private EditText txtName;
private EditText txtLastName;
private EditText txtCC;
private EditText txtPhone;
private EditText txtEmail;
private EditText txtPassword;
private Button btnRegister;
private TextView notifRegist;
private TextView textAccount;
    DatabaseReference mDatabase;

    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Inicialización firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();//Nodo principal DB

        //Rferenciar views
        txtName = (EditText) findViewById(R.id.textCcA);
        txtLastName = (EditText) findViewById(R.id.textLastName);
        txtCC = (EditText) findViewById(R.id.textCC);
        txtPhone = (EditText) findViewById(R.id.textPhone);
        txtEmail = (EditText) findViewById(R.id.textEmail);
        txtPassword = (EditText) findViewById(R.id.textPassword);
        notifRegist = (TextView) findViewById(R.id.notifRegist);

        textAccount = (TextView) findViewById(R.id.textAccount);

        //listener
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);

        textAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                finish();
            }
        });
    }


    private void registrarUsuario(){
        //Se convierte a texto el ingreso del correo y la contraseña
        final String name = txtName.getText().toString().trim();
        final String lastName = txtLastName.getText().toString().trim();
        final String CC = txtCC.getText().toString().trim();
        final String phone = txtPhone.getText().toString().trim();
        final String email = txtEmail.getText().toString().trim();
        final String password = txtPassword.getText().toString().trim();

        //Se verifica el estado del ingreso (si está vacío)
        if(TextUtils.isEmpty(name)){
            Toast.makeText(this,"Debe ingresar su nombre",Toast.LENGTH_LONG).show();
            notifRegist.setText("Debe ingresar su nombre");
            return;
        }
        if(TextUtils.isEmpty(lastName)){
            Toast.makeText(this,"Debe ingresar sus apellidos",Toast.LENGTH_LONG).show();
            notifRegist.setText("Debe ingresar sus apellidos");
            return;
        }
        if(TextUtils.isEmpty(CC)){
            Toast.makeText(this,"Debe ingresar su Cédula ciudadana",Toast.LENGTH_LONG).show();
            notifRegist.setText("Debe ingresar su Cédula ciudadana");
            return;
        }
        if(TextUtils.isEmpty(phone)){
            Toast.makeText(this,"Debe ingresar su teléfono celular",Toast.LENGTH_LONG).show();
            notifRegist.setText("Debe ingresar su teléfono celular");
            return;
        }
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Debe ingresar su email",Toast.LENGTH_LONG).show();
            notifRegist.setText("Debe ingresar su email");
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Se debe ingresar una contraseña",Toast.LENGTH_LONG).show();
            notifRegist.setText("Se debe ingresar una contraseña");
            return;
        }
        if(password.length()<6){
            notifRegist.setText("La contraseña debe contener más de 6 caracteres");
            return;
        }
        if(phone.length()<10){
            notifRegist.setText("Ingrese un número de teléfono válido");
            return;
        }

        //Inicio de creación
        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this,"Se ha registrado exitosamente",Toast.LENGTH_LONG).show();
                            FirebaseUser usuario = firebaseAuth.getCurrentUser();
                            usuario.sendEmailVerification();
                            txtName.setText("");
                            txtLastName.setText("");
                            txtCC.setText("");
                            txtPhone.setText("");
                            txtEmail.setText("");
                            txtPassword.setText("");
                            notifRegist.setText("Se ha registrado con éxtio, VERIFIQUE SU CORREO e ingrese nuevamente sus credenciales");
                            Toast.makeText(RegisterActivity.this, "Se ha registrado con éxtio, VERIFIQUE SU CORREO e ingrese nuevamente sus credenciales", Toast.LENGTH_LONG).show();
                            Toast.makeText(RegisterActivity.this, "Se ha registrado con éxtio, VERIFIQUE SU CORREO e ingrese nuevamente sus credenciales", Toast.LENGTH_LONG).show();
                            Toast.makeText(RegisterActivity.this, "Haga clic en el lapíz de arriba y complete su información médica", Toast.LENGTH_LONG).show();

                            final String textNull = "";
                            //TOMAR DATOS Y ENVIARLOS A REALTIEM DB
                            Map<String, Object> map = new HashMap<>();
                            map.put("name", name);
                            map.put("lastname", lastName);
                            map.put("cc", CC);
                            map.put("phone", phone);
                            map.put("email", email);
                            map.put("password", password);
                            map.put("city", textNull);
                            map.put("local", textNull);
                            map.put("address", textNull);
                            map.put("birthday", textNull);
                            map.put("ocupa", textNull);
                            map.put("civil", textNull);
                            map.put("blood", textNull);
                            map.put("gender", textNull);
                            map.put("yncardiac", textNull);
                            map.put("cardiacrecord", textNull);
                            map.put("yncancer", textNull);
                            map.put("cancerrecord", textNull);
                            map.put("yncirug", textNull);
                            map.put("cirugrecord", textNull);
                            map.put("ynalergic", textNull);
                            map.put("alergicrecord", textNull);
                            map.put("ynets", textNull);
                            map.put("etsrecord", textNull);
                            map.put("nameemergency", textNull);
                            map.put("phoneemergency", textNull);


                            String id = firebaseAuth.getCurrentUser().getUid();
                            mDatabase.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> taskB) {
                                    if(taskB.isSuccessful()){
                                        startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                                        finish();
                                    }
                                }
                            });

                        }else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(RegisterActivity.this, "El usuario ya está registrado", Toast.LENGTH_LONG).show();
                                notifRegist.setText("El usuario ya está registrado");
                            } else {
                                Toast.makeText(RegisterActivity.this, "No se ha registrado, verifica tu conexión", Toast.LENGTH_LONG).show();
                                notifRegist.setText("No se ha registrado, verifica tu conexión");
                            }
                        }
                    }
                });

    }


    @Override
    public void onClick(View view){
        //Se llama el método descrito antes
        registrarUsuario();
    }


}




