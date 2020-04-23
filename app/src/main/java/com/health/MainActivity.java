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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Objetos visibles
    private EditText textEmail;
    private EditText textPass;
    private Button btnRegist;
    private Button btnlogin;

    private TextView notif;

    //Firebase Auth
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicialización firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();

        //Referenciar los views
        textEmail = (EditText) findViewById(R.id.email);
        textPass = (EditText) findViewById(R.id.pass);

        btnRegist = (Button) findViewById(R.id.regist);
        btnlogin = (Button) findViewById(R.id.signin);

        notif = (TextView) findViewById(R.id.notif);

        //Listener
        btnRegist.setOnClickListener(this);
        btnlogin.setOnClickListener(this);
    }

    private void registrarUsuario(){
        //Se convierte a texto el ingreso del correo y la contraseña
        String email = textEmail.getText().toString().trim();
        String password = textPass.getText().toString().trim();

        //Se verifica el estado del ingreso (si está vacío)
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Se debe ingresar un email",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Se debe ingresar una contraseña",Toast.LENGTH_LONG).show();
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
                            notif.setText("Se ha registrado con éxtio, VERIFICA TU CORREO e ingrese nuevamente tus credenciales");
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
}
