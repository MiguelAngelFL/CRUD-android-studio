package com.fic.crudcursos.capaaplicacion;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fic.crudcursos.capadatos.AccesoDatos;
import com.fic.crudcursos.capadatos.AccesoDatosAlumnos;
import com.fic.crudcursos.capadatos.Alumnos;
import com.fic.crudcursos.capadatos.Curso;

import java.util.ArrayList;

public class AlumnoController {

   // private final AccesoDatosAlumnos AccesoDatosAlumnos;
    public AccesoDatosAlumnos accesoDatosAlumnos;
    public final String NOMBRE_TABLA = "alumnos";

    public AlumnoController(Context context){
        accesoDatosAlumnos = new AccesoDatosAlumnos(context);
    }

    public long guardarAlumno(Alumnos alumnos){
        SQLiteDatabase db = accesoDatosAlumnos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("nombre",alumnos.getNombre());
        valores.put("apellidoPa", alumnos.getApellidoPa());
        valores.put("apellidoMa", alumnos.getApellidoMa());
        valores.put("correo", alumnos.getCorreo());
        valores.put("telefono", alumnos.getTelefono());

        return db.insert(NOMBRE_TABLA,null,valores);
    }

    public int modificarAlumno(Alumnos alumno){
        SQLiteDatabase db = accesoDatosAlumnos.getWritableDatabase();
        ContentValues valores =  new ContentValues();
        valores.put("nombre",alumno.getNombre());
        valores.put("apellidoPa", alumno.getApellidoPa());
        valores.put("apellidoMa", alumno.getApellidoMa());
        valores.put("correo", alumno.getCorreo());
        valores.put("telefono", alumno.getTelefono());
        String filtro = "id = ?";
        String[] argumentos =  {String.valueOf(alumno.getId())};
        return db.update(NOMBRE_TABLA,valores,filtro,argumentos);
    }

    public int eliminarAlumno(Alumnos alumno){
        SQLiteDatabase db =  accesoDatosAlumnos.getWritableDatabase();
        String[] argumentos = {String.valueOf(alumno.getId())};
        return db.delete(NOMBRE_TABLA,"id = ?",argumentos);
    }

    public ArrayList<Alumnos> obtenerAlumnos(){
        ArrayList<Alumnos> alumnos =  new ArrayList<>();

        //Se necesita para leer los datos
        SQLiteDatabase db = accesoDatosAlumnos.getReadableDatabase();

        //SELECT id,nombre, institucion from cursos;
        String[] columnasConsulta = {"id","nombre","ApellidoPa","ApellidoMa","correo","telefono"};

        Cursor cursor = db.query(NOMBRE_TABLA,columnasConsulta,null,null,null,null,null);

        if(cursor == null){
            return alumnos;
        }

        if(cursor.moveToFirst() == false){
            return alumnos;
        }

        do{
            long idAlumno = cursor.getLong(0);
            String nombre = cursor.getString(1);
            String apellidoPa = cursor.getString(2);
            String apellidoMa = cursor.getString(3);
            String correo = cursor.getString(4);
            String telefono = cursor.getString(5);

            Alumnos alumnodb = new Alumnos(idAlumno,nombre,apellidoPa,apellidoMa,correo,telefono);

            alumnos.add(alumnodb);

        }while(cursor.moveToNext());


        cursor.close();

        return alumnos;
    }
}
