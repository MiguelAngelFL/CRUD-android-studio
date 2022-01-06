package com.fic.crudcursos.capadatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AccesoDatosAlumnos extends SQLiteOpenHelper {

    private static final String NOMBRE_BASE_DATOS = "alumnos";
    private static final String NOMBRE_TABLA_ALUMNOS = "alumnos";
    private static final int VERSION_BASE_DE_DATOS = 1;

    public AccesoDatosAlumnos(Context context){
        super(context,NOMBRE_BASE_DATOS,null,VERSION_BASE_DE_DATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(String.format("CREATE TABLE  %s (id integer primary key autoincrement, nombre text, ApellidoPa text, ApellidoMa text, correo text, telefono text)",NOMBRE_TABLA_ALUMNOS));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){}
}
