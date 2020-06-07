package com.health;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class AddInventory extends AppCompatActivity {

    private FirebaseAuth mAuthD;
    //Database
    private DatabaseReference mDatabase;

    public ArrayList<String> data = new ArrayList<String>();
    public ArrayList<String> data1 = new ArrayList<String>();
    public ArrayList<String> data2 = new ArrayList<String>();
    public EditText ed1, ed2, ed3;
    public Button b1;
    public ArrayList<String> productArray = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory);

        ed1 = findViewById(R.id.ed1);
        ed2 = findViewById(R.id.ed2);
        ed3 = findViewById(R.id.ed3);
        b1 = findViewById(R.id.btn1);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });
    }

    public ArrayList<String> add(){
        Map<String, Object> mapHash = new HashMap<>();
        String prodname = ed1.getText().toString().trim();
        String price = ed2.getText().toString().trim();
        String qty = ed3.getText().toString().trim();
        if(!prodname.equals("") || !price.equals("") || !qty.equals("")) {
            mapHash.put("prudct", prodname);
            mapHash.put("price", price);
            mapHash.put("count", qty);
            data.add(prodname);
            //Este es el arreglo que guarda los productos, pero se reinicia cada vez que termina >:|
            productArray.add(prodname);
            data1.add(price);
            data2.add(qty);
            TableLayout table = (TableLayout) findViewById(R.id.tb1);
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
            ed1.setText("");
            ed2.setText("");
            ed3.setText("");
            ed1.requestFocus();
        } else Toast.makeText(AddInventory.this, "Por favor, complete todos los campos", Toast.LENGTH_LONG).show();
        return productArray;
    }
//Metodo que se usa para llamar el arreglo de productos llenos :)
    public ArrayList<String> getArray() {
        productArray.add("P1");
        productArray.add("P2");
        return productArray;
    }
}
