package com.example.myapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NOMBRE = "basedatosDefinitiva.db";
    public static final String TABLE_CONTACTOS = "t_usuarios";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, 6);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL ("CREATE TABLE " + TABLE_CONTACTOS + "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL UNIQUE," + "contraseña TEXT NOT NULL," + "recontra TEXT NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL ("DROP TABLE " + TABLE_CONTACTOS);
        onCreate(sqLiteDatabase);
    }

    public Boolean insertarDatos(String nombre, String contraseña, String recontra){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("nombre", nombre);
        values.put("contraseña", contraseña);
        values.put("recontra", recontra);

        long resultado = db.insert("t_usuarios", null, values);
        if(resultado == -1) return false;
        else
            return true;
    }

    public Boolean valNombre(String nombre){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from t_usuarios where nombre=?", new String[]{nombre});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean valNombreContraseña(String nombre, String contraseña){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from t_usuarios where nombre=? and contraseña=?", new String[] {nombre, contraseña});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}
