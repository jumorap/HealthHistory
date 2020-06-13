package com.health;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class AddInventory extends AppCompatActivity {

    public static FirebaseAuth mAuthD;
    //Database
    public static DatabaseReference mDatabase;

    public static final ArrayList<String> data = new ArrayList<String>();
    public static final ArrayList<String> data1 = new ArrayList<String>();
    public static final ArrayList<String> data2 = new ArrayList<String>();
    public static EditText ed1, ed2, ed3, ed4, ed5;
    public static Button btn1;
    public static ArrayList<String> productArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory);

        mAuthD = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();//Nodo principal DB

        ed1 = findViewById(R.id.ed1);
        ed2 = findViewById(R.id.ed2);
        ed3 = findViewById(R.id.ed3);
        ed4 = findViewById(R.id.ed4);
        ed5 = findViewById(R.id.ed5);
        btn1 = findViewById(R.id.btn1);
        //table = (TableLayout) findViewById(R.id.tb1);

        //Añade los datos en la tabla al iniciar el programa
        //setInTable();


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });
    }

    public void add(){
        Map<String, Object> mapHashAdd = new HashMap<>();
        String prodname = ed1.getText().toString().trim();
        String priceA = ed2.getText().toString().trim();
        String qtyA = ed3.getText().toString().trim();
        String concentA = ed4.getText().toString().trim();
        String totalA = ed5.getText().toString().trim();
        int price = 0;
        int qty = 0;
        int concent = 0;
        int total = 0;
        try {
            price = Integer.parseInt(priceA);
        } catch (java.lang.NumberFormatException alpha){
            Toast.makeText(AddInventory.this, "El precio es inválido, ingrese únicamente números", Toast.LENGTH_LONG).show();
            return;
        }try {
            qty = Integer.parseInt(qtyA);
        } catch (java.lang.NumberFormatException alpha){
            Toast.makeText(AddInventory.this, "Las unidades x empaque son inválidas, ingrese únicamente números", Toast.LENGTH_LONG).show();
            return;
        }try {
            concent = Integer.parseInt(concentA);
        } catch (java.lang.NumberFormatException alpha){
            Toast.makeText(AddInventory.this, "La concentración es inválida, ingrese únicamente números", Toast.LENGTH_LONG).show();
            return;
        }try {
            total = Integer.parseInt(totalA);
        } catch (java.lang.NumberFormatException alpha){
            Toast.makeText(AddInventory.this, "Las unidades totales son inválidas, ingrese únicamente números", Toast.LENGTH_LONG).show();
            return;
        }
        mapHashAdd.put("product", prodname);
        mapHashAdd.put("price", price);
        mapHashAdd.put("count", qty);
        mapHashAdd.put("concentra", concent);
        mapHashAdd.put("total", total);
        if(!prodname.equals("") || price != 0 || qty != 0  || concent != 0  || total != 0 && !Collections.singletonList(AddInvent.onlyProducts).contains(prodname)) {
            //if(!Arrays.asList(AddInvent.searchProd).contains(prodname)) {
                DateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmssZ");
                Date date = new Date();
                String idProduct = dateFormat.format(date);
                mapHashAdd.put("ident", idProduct);
                mDatabase.child("Products")
                        .child(idProduct)
                        .updateChildren(mapHashAdd)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(AddInventory.this, "Se ha agregado correctamente", Toast.LENGTH_LONG).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddInventory.this, "Hubo un error, rectifique su conexión", Toast.LENGTH_LONG).show();
                    }
                });
                ed1.setText("");
                ed2.setText("");
                ed3.setText("");
                ed4.setText("");
                ed5.setText("");
        } else Toast.makeText(AddInventory.this, "Por favor, complete todos los campos", Toast.LENGTH_LONG).show();
    }




//Metodo que se usa para llamar el arreglo de productos llenos :)
    public ArrayList<String> getArray() {
        Collections.sort(AddInvent.onlyProducts);
        return AddInvent.onlyProducts;
    }
}
