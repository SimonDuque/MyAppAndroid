package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapp.db.DbHelper;

public class LoginActivity extends AppCompatActivity {
    EditText nombre, contraseña;
    Button iniciar;
    DbHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nombre=findViewById(R.id.nombre2);
        contraseña=findViewById(R.id.contraseña2);
        iniciar = findViewById(R.id.iniciar2);
        DB = new DbHelper(this);

        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario = nombre.getText().toString();
                String contra = contraseña.getText().toString();

                if(TextUtils.isEmpty(usuario) || TextUtils.isEmpty(contra))
                    Toast.makeText(LoginActivity.this, "No puede haber campos vacíos", Toast.LENGTH_SHORT).show();
                else {
                    Boolean validarNombreContra = DB.valNombreContraseña(usuario, contra);
                    if(validarNombreContra == true){
                        Toast.makeText(LoginActivity.this, "Se ha iniciado sesión con éxito", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), InicioActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this, "Inicio de sesión fallido", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}