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
import java.util.Objects;

public class AddInvent extends AppCompatActivity {

    public Button ba, be;
    public static FirebaseAuth mAuthD;
    //Database
    public static DatabaseReference mDatabase;

    public RecyclerView recycler;
    public ArrayList<String> listDatos = new ArrayList<String>();

    public static ArrayList<String> onlyProducts = new ArrayList<String>();
    public static ArrayList<String> searchProd = new ArrayList<String>();
    public static ArrayList<String> idProduct = new ArrayList<String>();

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
    }



    public void setInTable(){
        mDatabase.child("Products").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Tree tree = new Tree();
                if(dataSnapshot.exists()) {
                    listDatos.clear();
                    for(DataSnapshot ds: dataSnapshot.getChildren()) {
                        String prName = Objects.requireNonNull(ds.child("product").getValue()).toString() + "\n"
                                + Objects.requireNonNull(ds.child("concentra").getValue()).toString() + "mg\n"
                                + Objects.requireNonNull(ds.child("count").getValue()).toString() + " en empaque";
                        String prCost = Objects.requireNonNull(ds.child("price").getValue()).toString()+ " COP";
                        String prCount = Objects.requireNonNull(ds.child("total").getValue()).toString();
                        /*onlyProducts.add(prName);
                        searchProd.add(prName);
                        idProduct.add(ds.toString());
                        listDatos.add(prName);
                        listDatos.add(prCost);
                        listDatos.add(prCount);*/

                        tree.insert(Integer.parseInt(prCount), prName, prCost);


                    }
                    tree.recorrer(tree.root);
                    AdapterDatos adapter = new AdapterDatos(listDatos);
                    recycler.setAdapter(adapter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public class Tree{

        private class Node{
            public Node father;
            public Node right;
            public Node left;

            public int key;
            public Object contentData;
            public Object totalUnit;

            public Node(int position){
                key = position;
                right = null;
                left = null;
                father = null;
                contentData = null;
                totalUnit = null;
            }
        }

        Node root;
        public Tree(){
            root = null;
        }

        public void insert(int i, Object text, Object totalU){
            Node n = new Node(i);
            n.contentData = text;
            n.totalUnit = totalU;

            if(root == null) root = n;
            else {
                Node aux = root;
                while (aux != null){
                    n.father = aux;
                    if(n.key >= aux.key) aux = aux.right;
                    else aux = aux.left;
                }
                if(n.key < n.father.key) n.father.left = n;
                else n.father.right = n;
            }
        }

        public void recorrer(Node n){
            if (n != null){
                recorrer(n.left);
                listDatos.add((String) n.contentData);
                listDatos.add((String) n.totalUnit);
                listDatos.add(n.key + " Unidades en inventario");
                //listDatos.add(prCount);
                //https://www.youtube.com/watch?v=22AE6WklXBg
                recorrer(n.right);
            }
        }

    }


}
