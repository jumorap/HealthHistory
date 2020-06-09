package com.health;

import android.os.Bundle;
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
    public static TableLayout table;

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
        String price = ed2.getText().toString().trim();
        String qty = ed3.getText().toString().trim();
        String concent = ed4.getText().toString().trim();
        String total = ed5.getText().toString().trim();
        mapHashAdd.put("product", prodname);
        mapHashAdd.put("price", price);
        mapHashAdd.put("count", qty);
        mapHashAdd.put("concentra", concent);
        mapHashAdd.put("total", total);
        if(!prodname.equals("") || !price.equals("") || !qty.equals("") || !concent.equals("") || !total.equals("")) {
            //if(!Arrays.asList(AddInvent.searchProd).contains(prodname)) {
                DateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmssZ");
                Date date = new Date();
                String idProduct = dateFormat.format(date);
                mDatabase.child("Products")
                        .child(idProduct)
                        .updateChildren(mapHashAdd)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(AddInventory.this, "Se ha actualizado correctamente", Toast.LENGTH_LONG).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddInventory.this, "Hubo un error, rectifique su conexión", Toast.LENGTH_LONG).show();
                    }
                });
            /*data.add(prodname);
            //Este es el arreglo que guarda los productos, pero se reinicia cada vez que termina >:|
            productArray.add(prodname);
            data1.add(price);
            data2.add(qty);*/

                //setInTable();

                ed1.setText("");
                ed2.setText("");
                ed3.setText("");
                ed4.setText("");
                ed5.setText("");
                //ed1.requestFocus();
            /*}else{
                int indexOfProduct = AddInvent.searchProd.indexOf(prodname);
                mDatabase.child("Products").child(AddInvent.idProduct.get(indexOfProduct)).child("total").updateChildren(mapHashAdd).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(AddInventory.this, "Se ha actualizado correctamente", Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddInventory.this, "Hubo un error, rectifique su conexión", Toast.LENGTH_LONG).show();
                    }
                });;
            }*/

        } else Toast.makeText(AddInventory.this, "Por favor, complete todos los campos", Toast.LENGTH_LONG).show();
    }




//Metodo que se usa para llamar el arreglo de productos llenos :)
    public ArrayList<String> getArray() {
        return AddInvent.onlyProducts;
    }
}
