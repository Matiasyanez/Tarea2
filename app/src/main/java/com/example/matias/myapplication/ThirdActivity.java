package com.example.matias.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {
    private EditText editTextPhone;
    private EditText editTextWeb;
    private ImageButton imageButtonPhone;
    private ImageButton imageButtonWeb;
    private ImageButton imageButtonCamera;
    private final int PHONE_CALL_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        editTextWeb = (EditText) findViewById(R.id.editTextWeb);
        imageButtonPhone = (ImageButton) findViewById(R.id.imageButtonPhone);
        imageButtonWeb = (ImageButton) findViewById(R.id.imageButtonWeb);
        imageButtonCamera = (ImageButton) findViewById(R.id.imageButtonCamera);

        imageButtonPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nroTelefono = editTextPhone.getText().toString();
                if (nroTelefono != null) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        NewerVersions();
                    } else {
                        olderVersions(nroTelefono);
                    }
                }
            }

            private void olderVersions(String nroTelefono) {
                Intent intentLlamar = new Intent(Intent.ACTION_CALL, Uri.parse("nro:" + nroTelefono));
                if (verificarPermiso(Manifest.permission.CALL_PHONE)) {
                    startActivity(intentLlamar);
                } else {
                    Toast.makeText(ThirdActivity.this, "sin permiso para llamadas", Toast.LENGTH_SHORT).show();
                }
            }

            private void NewerVersions() {
                //requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PHONE_CALL_CODE);
            }
        });

        imageButtonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCamara = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivity(intentCamara);
            }
        });

    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case PHONE_CALL_CODE:
                String permission = permissions[0];
                int result = grantResults[0];
                if (permission.equals(Manifest.permission.CALL_PHONE)) {
                    //comprobar aceptado o denegado el permiso
                    if (result == PackageManager.PERMISSION_GRANTED) {
                        //Tiene permiso
                        String nroTelefono = editTextPhone.getText().toString();
                        Intent intentLlamar = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + nroTelefono));
                        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                            return;
                        startActivity(intentLlamar);
                    } else {
                        //No tiene permiso
                        Toast.makeText(ThirdActivity.this, "sin permiso para llamadas", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private boolean verificarPermiso(String permission) {
        int resultado = checkCallingOrSelfPermission(permission);
        return resultado == PackageManager.PERMISSION_GRANTED;
    }
}
