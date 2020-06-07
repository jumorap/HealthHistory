package com.health;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AddInvent extends AppCompatActivity {

    public Button ba, be;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_invent);

        ba = (Button) findViewById(R.id.ba);
        be = (Button) findViewById(R.id.be);

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
}
