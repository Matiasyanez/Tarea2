package com.example.matias.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    private TextView texto;
    private Button btnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        texto = (TextView) findViewById(R.id.textView);
        btnNext = (Button) findViewById(R.id.button2);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            String saludo = bundle.getString("saludo");
            Toast.makeText(SecondActivity.this, saludo, Toast.LENGTH_LONG).show();
            texto.setText(saludo);
        }
        else{
            Toast.makeText(SecondActivity.this, "esta vacio", Toast.LENGTH_LONG).show();
        }
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });
    }
}
