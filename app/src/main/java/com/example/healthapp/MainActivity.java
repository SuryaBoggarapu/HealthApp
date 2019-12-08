package com.example.healthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText et;
    Button btn,btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homecreen);
        et = findViewById(R.id.editText);
        btn = findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter the Product ID", Toast.LENGTH_LONG).show();
                } else {
                    int id = Integer.parseInt(et.getText().toString());


                    Intent myIntent = new Intent(MainActivity.this, RemoveProduct.class);
                    myIntent.putExtra("ID", id);
                    startActivity(myIntent);
                }
            }
        });

        btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String qid = btn2.getText().toString();
                Intent i = new Intent(MainActivity.this, ListCategory.class);
                startActivity(i);
            }
        });
    }}


