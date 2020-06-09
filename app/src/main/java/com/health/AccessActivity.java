package com.health;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class AccessActivity extends AppCompatActivity {
public static final String user = "names";
public static String patientH, bornH, phoneH, addressH, documentH;
TextView txtUser;
private ImageButton mSignout;
private ImageButton updateInfo;
private ImageButton calendarInfo;
private ImageButton history;
private ImageButton uploadHistory;
private ImageButton addInventa;

    private TextView textDesNameA;
    private TextView textDesCcA;
    private TextView textDesEmailA;
    private TextView textDesCityA;
    private TextView textDesOcupaA;
    private TextView textDesBloodA;
    private TextView textDesContactA;
    private TextView textDesGenderA;
    private TextView textDescCardiacA;
    private TextView textDesCancerA;
    private TextView textDesCirugA;
    private TextView textDesAlergicA;
    private TextView textDesEtsA;
private static FirebaseAuth mAuth;
DatabaseReference mDatabase ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access);

        txtUser = (TextView) findViewById(R.id.welcome);
        //String user = getIntent().getStringExtra("names");
        //txtUser.setText("¡Bienvenido " + user + "!");

        textDesNameA = (TextView) findViewById(R.id.textDesNameA);
        textDesCcA = (TextView) findViewById(R.id.textDesCcA);
        textDesEmailA = (TextView) findViewById(R.id.textDesEmailA);
        textDesOcupaA = (TextView) findViewById(R.id.textDesOcupaA);
        textDesCityA = (TextView) findViewById(R.id.textDesCityA);
        textDesBloodA = (TextView) findViewById(R.id.textDesBloodA);
        textDesContactA = (TextView) findViewById(R.id.textDesContactA);
        textDesGenderA = (TextView) findViewById(R.id.textDesGenderA);
        textDescCardiacA = (TextView) findViewById(R.id.textDescCardiacA);
        textDesCancerA = (TextView) findViewById(R.id.textDesCancerA);
        textDesCirugA = (TextView) findViewById(R.id.textDesCirugA);
        textDesAlergicA = (TextView) findViewById(R.id.textDesAlergicA);
        textDesEtsA = (TextView) findViewById(R.id.textDesEtsA);

        //medicalDataContent();

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mSignout = (ImageButton) findViewById(R.id.btnSignout);
        updateInfo = (ImageButton) findViewById(R.id.updateInfo);
        calendarInfo = (ImageButton) findViewById(R.id.calendarInfo);
        history = (ImageButton) findViewById(R.id.history);
        uploadHistory = (ImageButton) findViewById(R.id.uploadHistory);
        addInventa = (ImageButton) findViewById(R.id.addInventa);

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
        calendarInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AccessActivity.this, CalendarMainActivity.class));
            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AccessActivity.this, MainHisory.class));
            }
        });
        uploadHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AccessActivity.this, UploadHistory.class));
            }
        });
        addInventa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AccessActivity.this, AddInvent.class));
            }
        });
        //noRegisterMessage();
    }

//Obtener el nombre de acceso (Cualquier dato de la DB)
    public static class iden{
    //Enviar el id a las otras clases (MUY IMPORTANTE)
        String idFireBase = mAuth.getCurrentUser().getUid();
    }
    public static String seLlama;
    private void getUserInfo(){
        final String id = mAuth.getCurrentUser().getUid();
        mDatabase.child("Users").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String name = dataSnapshot.child("name").getValue().toString();

                    txtUser.setText("Bienvenido, " + name);
                    seLlama = name;






                    Map<String, Object> map = new HashMap<>();
                    map.put("nameAdd", dataSnapshot.child("name").getValue().toString());
                    map.put("documentAdd", dataSnapshot.child("cc").getValue().toString());
                    map.put("emailAdd", dataSnapshot.child("email").getValue().toString());
                    map.put("cityAdd", dataSnapshot.child("address").getValue().toString() + ", " + dataSnapshot.child("city").getValue().toString() + ", " + dataSnapshot.child("local").getValue().toString());
                    map.put("birthDayAdd", dataSnapshot.child("birthday").getValue().toString());
                    map.put("ocupaAdd", dataSnapshot.child("ocupa").getValue().toString());
                    //map.put("civilAdd", dataSnapshot.child("civil").getValue().toString());
                    map.put("bloodAdd", dataSnapshot.child("blood").getValue().toString());
                    map.put("nameEmergencyAdd", dataSnapshot.child("nameemergency").getValue().toString() + "\n+57 " +dataSnapshot.child("phoneemergency").getValue().toString());
                    map.put("cardiacAddYN", dataSnapshot.child("yncardiac").getValue().toString() + ", " + dataSnapshot.child("cardiacrecord").getValue().toString());
                    map.put("cancerAddYN", dataSnapshot.child("yncancer").getValue().toString() + ", " + dataSnapshot.child("cancerrecord").getValue().toString());
                    map.put("cirugAddYN", dataSnapshot.child("yncirug").getValue().toString() + ", " + dataSnapshot.child("cirugrecord").getValue().toString());
                    map.put("alergicAddYN", dataSnapshot.child("ynalergic").getValue().toString() + ", " + dataSnapshot.child("alergicrecord").getValue().toString());
                    map.put("etsAddYN", dataSnapshot.child("ynets").getValue().toString() + ", " + dataSnapshot.child("etsrecord").getValue().toString());
                    map.put("genderAdd", dataSnapshot.child("gender").getValue().toString());

                    textDesNameA.setText(map.get("nameAdd").toString());
                    textDesCcA.setText(map.get("documentAdd").toString());
                    textDesEmailA.setText(map.get("emailAdd").toString());
                    textDesCityA.setText(map.get("cityAdd").toString());
                    textDesOcupaA.setText(map.get("ocupaAdd").toString());
                    textDesBloodA.setText(map.get("bloodAdd").toString());
                    textDesContactA.setText(map.get("nameEmergencyAdd").toString());
                    textDescCardiacA.setText(map.get("cardiacAddYN").toString());
                    textDesCancerA.setText(map.get("cancerAddYN").toString());
                    textDesCirugA.setText(map.get("cirugAddYN").toString());
                    textDesAlergicA.setText(map.get("alergicAddYN").toString());
                    textDesEtsA.setText(map.get("etsAddYN").toString());
                    textDesGenderA.setText(map.get("genderAdd").toString());

                    //Compartir información con otras clases
                    patientH = (String) map.get("nameAdd");
                    bornH = (String) map.get("birthDayAdd");
                    phoneH = dataSnapshot.child("phone").getValue().toString();
                    addressH = (String) map.get("cityAdd");
                    documentH = (String) map.get("documentAdd");




                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public static class elNombre{
        String nombreDe = seLlama;
    }

    public static class identidadUsuario{
        String patientSent = patientH;
        String bornSent = bornH;
        String phoneSent = phoneH;
        String addressSent = addressH;
        String documentSent = documentH;
    }

    /*public void noRegisterMessage(){
        FirebaseUser usuario = mAuth.getCurrentUser();
        if(!usuario.isEmailVerified()){
            String catched = txtUser.getText().toString();
            txtUser.setText(catched + "\nPor favor haga clic en el lápiz de abajo y complete sus datos médicos");
        }
    }*/

    /*public void medicalDataContent(){
        String id = mAuth.getCurrentUser().getUid();
        mDatabase.child("Users").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot){

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }*/

}
