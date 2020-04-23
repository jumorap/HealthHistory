package com.health;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class ResetPasswordActivity extends AppCompatActivity {


    private EditText mEditEmail;
    private Button mButtonReset;
    private TextView asegura;

    private String email;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        mAuth = FirebaseAuth.getInstance();

        mEditEmail = (EditText) findViewById(R.id.textEmailPass);
        mButtonReset = (Button) findViewById(R.id.btnChangePass);
        asegura = (TextView) findViewById(R.id.asegura);

        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = mEditEmail.getText().toString().trim();

                if(!email.isEmpty()) {
                    resetPassword();
                }else{
                    asegura.setText("Debe ingresar su email");
                }
            }
        });

    }

    private void resetPassword(){
        mAuth.setLanguageCode("es");
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){
                    asegura.setText("Se le ha enviado un correo para reestablecer su contraseña");
                }else{
                    asegura.setText("Ha ocurrido un error, verifique su correo y su conexión a la red");
                }

            }
        });

    }

}
