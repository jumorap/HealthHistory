package com.health;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Objetos visibles
    private EditText textEmail;
    private EditText textPass;
    private Button btnRegist;

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

        //Listener
        btnRegist.setOnClickListener(this);
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
                        }else{
                            if(task.getException() instanceof FirebaseAuthUserCollisionException)
                            Toast.makeText(MainActivity.this,"No se ha registrado, verifica tu conexión",Toast.LENGTH_LONG).show();
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
