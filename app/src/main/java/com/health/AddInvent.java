package com.health;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddInvent extends AppCompatActivity {

    public Button ba, be;
    public static FirebaseAuth mAuthD;
    //Database
    public static DatabaseReference mDatabase;
    //public static TableLayout table;
    //public AddInventory invent = new AddInventory();

    public RecyclerView recycler;
    public ArrayList<String> listDatos = new ArrayList<String>();

    public static ArrayList<String> onlyProducts = new ArrayList<String>();
    public static ArrayList<String> searchProd = new ArrayList<String>();
    public static ArrayList<String> idProduct = new ArrayList<String>();

    /*public static final ArrayList<String> data = new ArrayList<String>();
    public static final ArrayList<String> data1 = new ArrayList<String>();
    public static final ArrayList<String> data2 = new ArrayList<String>();
    public static ArrayList<String> productArray = new ArrayList<>();*/

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_invent);

        mAuthD = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();//Nodo principal DB

        recycler = (RecyclerView) findViewById(R.id.recyclerId);
        recycler.setLayoutManager(new GridLayoutManager(this,3));

        setInTable();

        ba = (Button) findViewById(R.id.ba);
        be = (Button) findViewById(R.id.be);
        //table = (TableLayout) findViewById(R.id.tb1);
        ba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddInvent.this, AddInventory.class));
            }
        });

        be.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddInvent.this, DeleteInventory.class));
            }
        });


        //setInTable();
    }



    public void setInTable(){
        mDatabase.child("Products").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    listDatos.clear();
                    for(DataSnapshot ds: dataSnapshot.getChildren()) {
                        String prName = ds.child("product").getValue().toString() + "\n"
                                + ds.child("concentra").getValue().toString() + "mg\n"
                                + ds.child("count").getValue().toString() + " en empaque";
                        String prCost = ds.child("price").getValue().toString() + " COP";
                        String prCount = ds.child("total").getValue().toString() + " Unidades en inventario";
                        onlyProducts.add(prName);
                        searchProd.add(prName);
                        idProduct.add(ds.toString());
                        listDatos.add(prName);
                        listDatos.add(prCost);
                        listDatos.add(prCount);
                    }
                    AdapterDatos adapter = new AdapterDatos(listDatos);
                    recycler.setAdapter(adapter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }




/*
    public void setInTable(){
        mDatabase.child("Products").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    productArray.clear();
                    data.clear();
                    data1.clear();
                    data2.clear();
                    for(DataSnapshot ds: dataSnapshot.getChildren()) {
                        String productNameArr = ds.child("product").getValue().toString();
                        String productPriceArr = ds.child("price").getValue().toString();
                        String productCountArr = ds.child("count").getValue().toString();

                        productArray.add(productNameArr);
                        data.add(productNameArr);
                        data1.add(productPriceArr);
                        data2.add(productCountArr);
                    }
                    setTableValues();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void setTableValues(){

        be.setText(Arrays.toString(new ArrayList[]{data}));
        TableRow row = new TableRow(this);
        TextView t1 = new TextView(this);
        TextView t2 = new TextView(this);
        TextView t3 = new TextView(this);

        for (int i = 0; i < data.size(); i++) {

            String pname = data.get(i);
            String prc = data1.get(i);
            String qtyy = data2.get(i);

            t1.setText(pname);
            t2.setText(prc);
            t3.setText(qtyy);

        }
        row.addView(t1);
        row.addView(t2);
        row.addView(t3);
        table.addView(row);
    }
*/

}
