package com.health;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class UploadHistory extends AppCompatActivity {
    private RecyclerView vistaChat;
    private EditText escribir;

    private EditText pesoH;
    private EditText tallaH;
    private EditText tempH;
    private EditText tensiH;
    private EditText satH;
    private EditText sintH;
    private EditText obsH;
    private EditText presH;


    private Button boton;
    private ImageButton btnFoto;
    private ImageButton btnDown;

    public AdapterHistory adapter;

    private FirebaseDatabase database;
    private DatabaseReference DBReference;
    private DatabaseReference databaseReference;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private LocalDateTime localDateTime;
    private DatabaseReference mDatabase;
    private static final int PHOTO_SEND = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_history);

        vistaChat = findViewById(R.id.vistaChat);
        escribir = findViewById(R.id.escribir);

        pesoH = (EditText) findViewById(R.id.pesoH);
        tallaH = (EditText) findViewById(R.id.tallaH);
        tempH = (EditText) findViewById(R.id.tempH);
        tensiH = (EditText) findViewById(R.id.tensiH);
        satH = (EditText) findViewById(R.id.satH);
        sintH = (EditText) findViewById(R.id.sintH);
        obsH = (EditText) findViewById(R.id.obsH);
        presH = (EditText) findViewById(R.id.presH);

        boton = findViewById(R.id.boton);
        btnFoto = findViewById(R.id.btnFoto);
        btnDown = findViewById(R.id.btnDown);

        database = FirebaseDatabase.getInstance();
        DBReference = database.getReference("Chats");
        //databaseReference = DBReference.child("Proof");//Sala chat
        AccessActivity.iden ident = new AccessActivity.iden();
        final String id = ident.idFireBase;
        databaseReference = DBReference.child(id);//Sala chat
        mDatabase = FirebaseDatabase.getInstance().getReference();

        storage = FirebaseStorage.getInstance();
/*
        adapter = new AdapterHistory(this);
        LinearLayoutManager l = new LinearLayoutManager(this);
        vistaChat.setLayoutManager(l);
        vistaChat.setAdapter(adapter);
*/

        final DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
        final Date date = new Date();
        AccessActivity.elNombre elNombre = new AccessActivity.elNombre();
        final String nombreDe = elNombre.nombreDe;
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                //mDatabase.child("Users").child(id).child("name").get
                String a = escribir.getText().toString();
                String b = pesoH.getText().toString();
                String c = tallaH.getText().toString();
                String d = tempH.getText().toString();
                String e = tensiH.getText().toString();
                String f = satH.getText().toString();
                String g = sintH.getText().toString();
                String h = obsH.getText().toString();
                String i = presH.getText().toString();
                if (!(a.equals("")) || !(b.equals("")) || !(c.equals("")) || !(d.equals("")) || !(e.equals("")) || !(f.equals("")) || !(g.equals("")) ||
                        !(h.equals("")) || !(i.equals(""))) {
                    databaseReference.push().setValue(new History(dateFormat.format(date),
                            nombreDe, a, b, c, d, e, f, g, h, i, "", "1"));
                    onBackPressed();
                    Toast.makeText(UploadHistory.this, "Se ha subido el reporte clínico correctamente", Toast.LENGTH_LONG).show();
                } else{
                    Toast.makeText(UploadHistory.this, "Por favor, complete todos los campos", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("image/jpeg");
                i.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
                startActivityForResult(Intent.createChooser(i,"Seleccionar foto"),PHOTO_SEND);
            }
        });


        /*btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vistaChat.scrollToPosition(adapter.getItemCount()-1);
            }
        });*/


        //scrollAdapter();



        //////
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                History m = dataSnapshot.getValue(History.class);
                //adapter.addMessage(m);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //*/
        //scrollSend();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_SEND && resultCode ==  RESULT_OK){
            Uri u = data.getData();
            storageReference = storage.getReference("imgChat");

            //imágenes chat
            final StorageReference fotoReferencia = storageReference.child(u.getLastPathSegment());
            final DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
            final Date date = new Date();
            AccessActivity.iden ident = new AccessActivity.iden();
            final String id = ident.idFireBase;
            AccessActivity.elNombre elNombre = new AccessActivity.elNombre();
            final String nombreDe = elNombre.nombreDe;
            fotoReferencia.putFile(u).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {

                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Task<Uri> downloadUrl = fotoReferencia.getDownloadUrl();
                    downloadUrl.addOnSuccessListener(new OnSuccessListener<Uri>() {

                        @Override
                        public void onSuccess(Uri uri) {
                            String u = uri.toString();
                            History m = new History(dateFormat.format(date),nombreDe,"_________________________________","","","","","","","","",u,"2");
                            databaseReference.push().setValue(m);
                            Toast.makeText(UploadHistory.this,"La fotografía se ha subido al reporte clínico correctamente",Toast.LENGTH_LONG).show();
                            //scrollSend();
                        }
                    });
                }
            });
            //scrollSend();
        }
        //scrollSend();
    }


    /*private void scrollAdapter(){
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                scrollSend();
            }
        });
    }*/

    /*private void scrollSend(){
        vistaChat.scrollToPosition(adapter.getItemCount()-1);
    }*/
}