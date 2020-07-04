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

import java.util.ArrayList;
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
private ImageButton pdf;

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
        pdf = (ImageButton) findViewById(R.id.pdf);

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
        pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AccessActivity.this, PdfActivity.class));
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




                    Map<String, Object> map = new Map<>();
                    map.put("name", dataSnapshot.child("name").getValue().toString());
                    map.put("document", dataSnapshot.child("cc").getValue().toString());
                    map.put("email", dataSnapshot.child("email").getValue().toString());
                    map.put("city", dataSnapshot.child("address").getValue().toString() + ", " + dataSnapshot.child("city").getValue().toString() + ", " + dataSnapshot.child("local").getValue().toString());
                    map.put("birthday", dataSnapshot.child("birthday").getValue().toString());
                    map.put("ocupa", dataSnapshot.child("ocupa").getValue().toString());
                    //map.put("civilAdd", dataSnapshot.child("civil").getValue().toString());
                    map.put("blood", dataSnapshot.child("blood").getValue().toString());
                    map.put("namemergency", dataSnapshot.child("nameemergency").getValue().toString() + "\n+57 " +dataSnapshot.child("phoneemergency").getValue().toString());
                    map.put("cardiac", dataSnapshot.child("yncardiac").getValue().toString() + ", " + dataSnapshot.child("cardiacrecord").getValue().toString());
                    map.put("cance", dataSnapshot.child("yncancer").getValue().toString() + ", " + dataSnapshot.child("cancerrecord").getValue().toString());
                    map.put("cirug", dataSnapshot.child("yncirug").getValue().toString() + ", " + dataSnapshot.child("cirugrecord").getValue().toString());
                    map.put("alerg", dataSnapshot.child("ynalergic").getValue().toString() + ", " + dataSnapshot.child("alergicrecord").getValue().toString());
                    map.put("ets", dataSnapshot.child("ynets").getValue().toString() + ", " + dataSnapshot.child("etsrecord").getValue().toString());
                    map.put("gende", dataSnapshot.child("gender").getValue().toString());

                    textDesNameA.setText(map.get("name").toString());
                    textDesCcA.setText(map.get("document").toString());
                    textDesEmailA.setText(map.get("email").toString());
                    textDesCityA.setText(map.get("city").toString());
                    textDesOcupaA.setText(map.get("ocupa").toString());
                    textDesBloodA.setText(map.get("blood").toString());
                    textDesContactA.setText(map.get("namemergency").toString());
                    textDescCardiacA.setText(map.get("cardiac").toString());
                    textDesCancerA.setText(map.get("cance").toString());
                    textDesCirugA.setText(map.get("cirug").toString());
                    textDesAlergicA.setText(map.get("alerg").toString());
                    textDesEtsA.setText(map.get("ets").toString());
                    textDesGenderA.setText(map.get("gende").toString());

                    //Compartir información con otras clases
                    patientH = (String) map.get("name");
                    bornH = (String) map.get("birthday");
                    phoneH = dataSnapshot.child("phone").getValue().toString();
                    addressH = (String) map.get("city");
                    documentH = (String) map.get("document");




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







    static class HashNode<K, V> {
        public K key;
        public V value;
        public HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    static class Map<K, V> {
        private ArrayList<HashNode<K, V>> bucketArray;
        private int numBuckets;
        private int size;
        public int bucketIndex;

        public Map() {
            bucketArray = new ArrayList<>();
            numBuckets = 10;
            size = 0;
            for (int i = 0; i < numBuckets; i++) bucketArray.add(null);
        }

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size() == 0;
        }

        private int getBucketIndex(K key) {
            int hashCode = key.hashCode();
            return hashCode % numBuckets;
        }

        public V remove(K key) {
            int bucketIndex = getBucketIndex(key);
            HashNode<K, V> head = bucketArray.get(bucketIndex);
            HashNode<K, V> prev = null;
            while (head != null) {
                if (head.key.equals(key)) break;
                prev = head;
                head = head.next;
            }

            if (head == null) return null;
            size--;
            if (prev != null) prev.next = head.next;
            else bucketArray.set(bucketIndex, head.next);

            return head.value;
        }

        public V get(K key) {
            int bucketIndex = getBucketIndex(key);
            HashNode<K, V> head = bucketArray.get(bucketIndex);

            while (head != null) {
                if (head.key.equals(key)) return head.value;
                head = head.next;
            }

            return null;
        }

        public void put(K key, V value) {
            bucketIndex = getBucketIndex(key);
            HashNode<K, V> head = bucketArray.get(bucketIndex);

            while (head != null) {
                if (head.key.equals(key)) {
                    head.value = value;
                    return;
                }
                head = head.next;
            }
            head = bucketArray.get(bucketIndex);
            HashNode<K, V> newNode = new HashNode<>(key, value);
            newNode.next = head;
            bucketArray.set(bucketIndex, newNode);
            size++;
        }
    }
}
