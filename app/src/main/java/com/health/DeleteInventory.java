package com.health;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.health.R.layout.delete;

public class DeleteInventory extends AppCompatActivity {
    public Spinner spinnerProduct, spinnerQuantity;
    public Button deleteButton;
    public ArrayList<String> productsArray = new ArrayList<>();

    public static FirebaseAuth mAuthD;
    //Database
    public static DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(delete);

        mAuthD = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();//Nodo principal DB

        delete();
    }

    public void delete(){
        spinnerProduct = findViewById(R.id.spinnerProduct);
        spinnerQuantity= findViewById(R.id.spinnerQuantity);
        ArrayList<String> quantityArray = new ArrayList<>();

        AddInventory products_array = new AddInventory();


        // Aqui se llama el arreglo de productos


//de esta manera llenamos el arreglo de la cantidad del producto
        quantityArray.add("4");
        quantityArray.add("3");
        quantityArray.add("2");
        quantityArray.add("1");

        ArrayAdapter adpProducts = new ArrayAdapter(DeleteInventory.this, android.R.layout.simple_spinner_dropdown_item,products_array.getArray());
        spinnerProduct.setAdapter(adpProducts);
        final String[] dataSpinnerText = {""};
        spinnerProduct.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String elemenProductArray = (String) spinnerProduct.getAdapter().getItem(position);
                dataSpinnerText[0] = elemenProductArray;
                Toast.makeText(DeleteInventory.this, "Producto: "+elemenProductArray, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //-----------------------------------------------------------------------------------------------
        //Cantidad de producto

        final String[] dataSpinner = {""};

        ArrayAdapter adpQuantity = new ArrayAdapter(DeleteInventory.this, android.R.layout.simple_spinner_dropdown_item,quantityArray);
        spinnerQuantity.setAdapter(adpQuantity);

        spinnerQuantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String elemenQuantityArray = (String) spinnerQuantity.getAdapter().getItem(position);
                dataSpinner[0] = elemenQuantityArray;
                Toast.makeText(DeleteInventory.this, "Cantidad: "+elemenQuantityArray, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Borrar el precio y cantidad mostradas, suerte!!
                mDatabase.child("Products").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Map<String, Object> mapHashAdd = new HashMap<>();
                        if(dataSnapshot.exists()) {
                            for(DataSnapshot ds: dataSnapshot.getChildren()) {
                                String prName = Objects.requireNonNull(ds.child("product").getValue()).toString();
                                if(dataSpinnerText[0].equals(prName)){
                                    String prCount = Objects.requireNonNull(ds.child("total").getValue()).toString();
                                    int newTotal = Integer.parseInt(prCount) - Integer.parseInt(dataSpinner[0]);
                                    if(newTotal>=0){
                                        mapHashAdd.put("total", newTotal);

                                        mDatabase.child("Products")
                                                .child(Objects.requireNonNull(ds.child("ident").getValue()).toString())
                                                .updateChildren(mapHashAdd)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        Toast.makeText(DeleteInventory.this, "Se ha eliminado correctamente", Toast.LENGTH_LONG).show();
                                                    }
                                                }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(DeleteInventory.this, "Hubo un error, rectifique su conexión", Toast.LENGTH_LONG).show();
                                            }
                                        });
                                    }
                                }
                                break;
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

    }
}
