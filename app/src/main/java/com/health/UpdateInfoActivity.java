package com.health;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Calendar;

public class UpdateInfoActivity extends AppCompatActivity implements View.OnClickListener {
private RadioButton radioGenderMan;
private RadioButton radioGenderWoman;
private EditText city;
private EditText local;
private EditText address;
private EditText birthDay;
    private int nYearInt, nMonthInt,nDayInt, sYearInt, sMonthInt, sDayInt;
    static final int DATE_ID = 0;
    Calendar C = Calendar.getInstance();
private EditText ocupa;
private EditText civil;
private EditText blood;
private Spinner spinner;
private EditText nameEmergency;
private EditText phoneEmergency;
private Button upload;

private RadioButton radioYesCardiac;
private RadioButton radioNoCardiac;
private EditText textCardiac;

private RadioButton radioYesCancer;
private RadioButton radioNoCancer;
private EditText textCancer;

private RadioButton radioYesCirug;
private RadioButton radioNoCirug;
private EditText textCirug;

private RadioButton radioYesAlergic;
private RadioButton radioNoAlergic;
private EditText textAlergic;

private RadioButton radioYesEts;
private RadioButton radioNoEts;
private EditText textEts;

private TextView notifCamps;

private FirebaseAuth mAuthD;

//Database
private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info);

        //Inicialización firebase Auth
        mAuthD = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();//Nodo principal DB

        radioGenderMan = (RadioButton) findViewById(R.id.radioGenderMan);
        radioGenderWoman = (RadioButton) findViewById(R.id.radioGenderWoman);

        radioYesCardiac = (RadioButton) findViewById(R.id.radioYesCardiac);
        radioNoCardiac = (RadioButton) findViewById(R.id.radioNoCardiac);
        textCardiac = (EditText) findViewById(R.id.textCardiac);

        radioYesCancer = (RadioButton) findViewById(R.id.radioYesCancer);
        radioNoCancer = (RadioButton) findViewById(R.id.radioNoCancer);
        textCancer = (EditText) findViewById(R.id.textCancer);

        radioYesCirug = (RadioButton) findViewById(R.id.radioYesCirug);
        radioNoCirug = (RadioButton) findViewById(R.id.radioNoCirug);
        textCirug = (EditText) findViewById(R.id.textCirug);

        radioYesAlergic = (RadioButton) findViewById(R.id.radioYesAlergic);
        radioNoAlergic = (RadioButton) findViewById(R.id.radioNoAlergic);
        textAlergic = (EditText) findViewById(R.id.textAlergic);

        radioYesEts = (RadioButton) findViewById(R.id.radioYesEts);
        radioNoEts = (RadioButton) findViewById(R.id.radioNoEts);
        textEts = (EditText) findViewById(R.id.textEts);

        city = (EditText) findViewById(R.id.city);
        local = (EditText) findViewById(R.id.local);
        address = (EditText) findViewById(R.id.address);
        birthDay = (EditText) findViewById(R.id.birthDay);
        sMonthInt = C.get(Calendar.MONTH);
        sDayInt = C.get(Calendar.DAY_OF_MONTH);
        sYearInt = C.get(Calendar.YEAR);
        birthDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DATE_ID);
            }
        });
        ocupa = (EditText) findViewById(R.id.ocupa);
        civil = (EditText) findViewById(R.id.civil);
        blood = (EditText) findViewById(R.id.blood);
        spinner = (Spinner) findViewById(R.id.spinner);
        nameEmergency = (EditText) findViewById(R.id.nameEmergency);
        phoneEmergency = (EditText) findViewById(R.id.phoneEmergency);

        upload = (Button) findViewById(R.id.update);
        upload.setOnClickListener(this);

        ArrayList<String> bloodTypeList = new ArrayList<>();
        bloodTypeList.add("Grupo sanguíneo");
        bloodTypeList.add("O-");
        bloodTypeList.add("O+");
        bloodTypeList.add("A-");
        bloodTypeList.add("A+");
        bloodTypeList.add("B-");
        bloodTypeList.add("B+");
        bloodTypeList.add("AB-");
        bloodTypeList.add("AB+");
        ArrayAdapter adp = new ArrayAdapter(UpdateInfoActivity.this, android.R.layout.simple_spinner_dropdown_item,bloodTypeList);

        spinner.setAdapter(adp);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String elementoArray = (String) spinner.getAdapter().getItem(position);

                //Toast.makeText(DoctorsMainActivity.this, "Médico: "+elementoArray, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        notifCamps = (TextView) findViewById(R.id.notifCamps);
        getUserInfo();
    }
    //Obtener el nombre de acceso (Cualquier dato de la DB)
    private void getUserInfo(){
        AccessActivity.iden ident = new AccessActivity.iden();
        String id = ident.idFireBase;
        mDatabase.child("Users").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String cityAdd = dataSnapshot.child("city").getValue().toString();
                    String localAdd = dataSnapshot.child("local").getValue().toString();
                    String addressAdd = dataSnapshot.child("address").getValue().toString();
                    String birthDayAdd = dataSnapshot.child("birthday").getValue().toString();
                    String ocupaAdd = dataSnapshot.child("ocupa").getValue().toString();
                    String civilAdd = dataSnapshot.child("civil").getValue().toString();
                    String bloodAdd = dataSnapshot.child("blood").getValue().toString();
                    String nameEmergencyAdd = dataSnapshot.child("nameemergency").getValue().toString();
                    String phoneEmergencyAdd = dataSnapshot.child("phoneemergency").getValue().toString();
                    String cardiacAddYN = dataSnapshot.child("yncardiac").getValue().toString();
                    String textCardiacAdd = dataSnapshot.child("cardiacrecord").getValue().toString();
                    String cancerAddYN = dataSnapshot.child("yncancer").getValue().toString();
                    String textCancerAdd = dataSnapshot.child("cancerrecord").getValue().toString();
                    String cirugAddYN = dataSnapshot.child("yncirug").getValue().toString();
                    String textCirugAdd = dataSnapshot.child("cirugrecord").getValue().toString();
                    String alergicAddYN = dataSnapshot.child("ynalergic").getValue().toString();
                    String textAlergicAdd = dataSnapshot.child("alergicrecord").getValue().toString();
                    String etsAddYN = dataSnapshot.child("ynets").getValue().toString();
                    String etsAdd = dataSnapshot.child("etsrecord").getValue().toString();
                    String genderAdd = dataSnapshot.child("gender").getValue().toString();

                    if(genderAdd.equals("Masculino")) radioGenderMan.setChecked(true);
                    else if(genderAdd.equals("Femenino")) radioGenderWoman.setChecked(true);
                    if(cardiacAddYN.equals("Sí")) radioYesCardiac.setChecked(true);
                    else if(cardiacAddYN.equals("No")) radioNoCardiac.setChecked(true);
                    if(cancerAddYN.equals("Sí")) radioYesCancer.setChecked(true);
                    else if(cancerAddYN.equals("No")) radioNoCancer.setChecked(true);
                    if(cirugAddYN.equals("Sí")) radioYesCirug.setChecked(true);
                    else if(cirugAddYN.equals("No")) radioNoCirug.setChecked(true);
                    if(alergicAddYN.equals("Sí")) radioYesAlergic.setChecked(true);
                    else if(alergicAddYN.equals("No")) radioNoAlergic.setChecked(true);
                    if(etsAddYN.equals("Sí")) radioYesEts.setChecked(true);
                    else if(etsAddYN.equals("No")) radioNoEts.setChecked(true);

                    city.setText(cityAdd);
                    local.setText(localAdd);
                    address.setText(addressAdd);
                    birthDay.setText(birthDayAdd);
                    ocupa.setText(ocupaAdd);
                    civil.setText(civilAdd);
                    blood.setText(bloodAdd);
                    textCardiac.setText(textCardiacAdd);
                    textCancer.setText(textCancerAdd);
                    textCirug.setText(textCirugAdd);
                    textAlergic.setText(textAlergicAdd);
                    textEts.setText(etsAdd);
                    nameEmergency.setText(nameEmergencyAdd);
                    phoneEmergency.setText(phoneEmergencyAdd);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void updateInfoUser(){

        String gender = "";
        if(radioGenderMan.isChecked()) gender = "Masculino";
        else if(radioGenderWoman.isChecked()) gender = "Femenino";
        String cardiacYN = "";
        if(radioYesCardiac.isChecked()) cardiacYN = "Sí";
        else if(radioNoCardiac.isChecked()) cardiacYN = "No";
        String cardiacR = textCardiac.getText().toString().trim();
        String cancerYN = "";
        if(radioYesCancer.isChecked()) cancerYN = "Sí";
        else if(radioNoCancer.isChecked()) cancerYN = "No";
        String cancerR = textCancer.getText().toString().trim();
        String cirugYN = "";
        if(radioYesCirug.isChecked()) cirugYN = "Sí";
        else if(radioNoCirug.isChecked()) cirugYN = "No";
        String cirugR = textCirug.getText().toString().trim();
        String alergicYN = "";
        if(radioYesAlergic.isChecked()) alergicYN = "Sí";
        else if(radioNoAlergic.isChecked()) alergicYN = "No";
        String alergicR = textAlergic.getText().toString().trim();
        String etsYN = "";
        if(radioYesEts.isChecked()) etsYN = "Sí";
        else if(radioNoEts.isChecked()) etsYN = "No";
        String etsR = textEts.getText().toString().trim();

        final String cityR = city.getText().toString().trim();
        final String localR = local.getText().toString().trim();
        final String addressR = address.getText().toString().trim();
        final String birthDayR = birthDay.getText().toString().trim();
        final String ocupaR = ocupa.getText().toString().trim();
        final String civilR = civil.getText().toString().trim();
        final String bloodR = blood.getText().toString().trim();
        final String nameEmergencyR = nameEmergency.getText().toString().trim();
        final String phoneEmergencyR = phoneEmergency.getText().toString().trim();


        if(gender.isEmpty() || cardiacYN.isEmpty() || cancerYN.isEmpty() || cirugYN.isEmpty() || alergicYN.isEmpty() || etsYN.isEmpty() || cityR.isEmpty() || localR.isEmpty() ||
        addressR.isEmpty() || birthDayR.isEmpty() || ocupaR.isEmpty() || civilR.isEmpty() || bloodR.isEmpty() || nameEmergencyR.isEmpty() || phoneEmergencyR.isEmpty()){
            notifCamps.setText("Complete todos los campos necesarios");
            return;
        }

        Map<String, Object> mapHash = new HashMap<>();
        mapHash.put("city", cityR);
        mapHash.put("local", localR);
        mapHash.put("address", addressR);
        mapHash.put("birthday", birthDayR);
        mapHash.put("ocupa", ocupaR);
        mapHash.put("civil", civilR);
        mapHash.put("blood", bloodR);
        mapHash.put("gender", gender);
        mapHash.put("yncardiac", cardiacYN);
        mapHash.put("cardiacrecord", cardiacR);
        mapHash.put("yncancer", cancerYN);
        mapHash.put("cancerrecord", cancerR);
        mapHash.put("yncirug", cirugYN);
        mapHash.put("cirugrecord", cirugR);
        mapHash.put("ynalergic", alergicYN);
        mapHash.put("alergicrecord", alergicR);
        mapHash.put("ynets", etsYN);
        mapHash.put("etsrecord", etsR);
        mapHash.put("nameemergency", nameEmergencyR);
        mapHash.put("phoneemergency", phoneEmergencyR);

        //String id = mAuth.getCurrentUser().getUid();
        AccessActivity.iden ident = new AccessActivity.iden();
        String id = ident.idFireBase;
        /*mDatabase.child("Users").child(id).push().setValue(mapHash);*/
        mDatabase.child("Users").child(id).updateChildren(mapHash).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                startActivity(new Intent(UpdateInfoActivity.this,AccessActivity.class));
                Toast.makeText(UpdateInfoActivity.this, "Su información ha sido actualizada", Toast.LENGTH_LONG).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UpdateInfoActivity.this, "Hubo un error, rectifique su conexión", Toast.LENGTH_LONG).show();
            }
        });



        /*mDatabase.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> taskB) {
                if(taskB.isSuccessful()){
                    startActivity(new Intent(UpdateInfoActivity.this,AccessActivity.class));
                    finish();
                }
            }
        });*/
    }

    @Override
    public void onClick(View view){
        //Se llama el método descrito antes
        updateInfoUser();
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    nYearInt = year;
                    nMonthInt = monthOfYear;
                    nDayInt = dayOfMonth;
                    birthDay.setText((nMonthInt + 1) + "/" + nDayInt + "/" + nYearInt);
                }

            };

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DATE_ID) {
            return new DatePickerDialog(this, mDateSetListener, sYearInt, sMonthInt, sDayInt);
        }
        return null;
    }

    static class HashNode<K, V> {
        public K key;
        public V value;
        public AccessActivity.HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
