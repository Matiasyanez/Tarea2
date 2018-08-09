package com.example.matias.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn;
    private CheckBox chb;
    private Switch swt;
    private final String SALUDO = "hola a todos desde otro activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("saludo", SALUDO);
                startActivity(intent);
            }
        });

        chb = (CheckBox) findViewById(R.id.checkBoxchoro);
        chb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "CheckBox", Toast.LENGTH_SHORT).show();
            }
        });

        swt = (Switch) findViewById(R.id.switch1);
        swt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Switch", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void Metodo(View v) {
        Toast.makeText(MainActivity.this, "click en el boton", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(MainActivity.this, "boton presionado", Toast.LENGTH_SHORT).show();
    }
}
