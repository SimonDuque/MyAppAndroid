package com.example.myapp;

import static android.app.ProgressDialog.show;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapp.db.DbHelper;

public class MainActivity extends AppCompatActivity {

    EditText nombre, contraseña, recontra;
    Button registrar, iniciar;
    DbHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre=findViewById(R.id.nombre);
        contraseña=findViewById(R.id.contraseña);
        recontra=findViewById(R.id.recontra);
        registrar=findViewById(R.id.registrar);
        iniciar=findViewById(R.id.iniciar);
        DB= new DbHelper(this);

        Button btnCrearTabla;
        btnCrearTabla = findViewById(R.id.btnCrearTabla);
        btnCrearTabla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbHelper Dbhelper = new DbHelper(MainActivity.this);
                SQLiteDatabase db = Dbhelper.getWritableDatabase();

                if(db!=null){
                    Toast.makeText(MainActivity.this, "BASE DE DATOS CREADA", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this, "ERROR AL CREAR LA BASE DE DATOS", Toast.LENGTH_LONG).show();
                }
            }
        });

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHelper Dbhelper = new DbHelper(MainActivity.this);
                SQLiteDatabase db = Dbhelper.getWritableDatabase();

                String usuarios = nombre.getText().toString();
                String contra = contraseña.getText().toString();
                String recontraseña = recontra.getText().toString();

                if(TextUtils.isEmpty(usuarios) || TextUtils.isEmpty(contra) || TextUtils.isEmpty(recontraseña))
                    Toast.makeText(MainActivity.this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
                else{
                    if(contra.equals(recontraseña)){
                    Boolean validarNombre = DB.valNombre(usuarios);
                    if(validarNombre == false){
                        Boolean insertar = DB.insertarDatos(usuarios, contra, recontraseña);
                        if(insertar == true){
                            Toast.makeText(MainActivity.this, "Se ha registrado con éxito", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), InicioActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(MainActivity.this,"Registro fallido", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(MainActivity.this, "Usuario ya existente", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}