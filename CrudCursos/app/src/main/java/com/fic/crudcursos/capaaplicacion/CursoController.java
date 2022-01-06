package com.fic.crudcursos.capaaplicacion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fic.crudcursos.capadatos.AccesoDatos;
import com.fic.crudcursos.capadatos.Curso;

import java.util.ArrayList;

public class CursoController {

    public AccesoDatos accesoDatos;
    public final String NOMBRE_TABLA = "cursos";

    public CursoController(Context context){
        accesoDatos = new AccesoDatos(context);
    }

    public long guardarCurso(Curso curso){
        SQLiteDatabase db = accesoDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("nombre",curso.getNombre());
        valores.put("institucion", curso.getInstitucion());
        return db.insert(NOMBRE_TABLA,null,valores);
    }

    public int modificarCurso(Curso curso){
        SQLiteDatabase db = accesoDatos.getWritableDatabase();
        ContentValues valores =  new ContentValues();
        valores.put("nombre",curso.getNombre());
        valores.put("institucion", curso.getInstitucion());
        String filtro = "id = ?";
        String[] argumentos =  {String.valueOf(curso.getId())};
        return db.update(NOMBRE_TABLA,valores,filtro,argumentos);
    }

    public int eliminarCurso(Curso curso){
        SQLiteDatabase db =  accesoDatos.getWritableDatabase();
        String[] argumentos = {String.valueOf(curso.getId())};
        return db.delete(NOMBRE_TABLA,"id = ?",argumentos);
    }

    public ArrayList<Curso> obtenerCursos(){
        ArrayList<Curso> cursos =  new ArrayList<>();

        //Se necesita para leer los datos
        SQLiteDatabase db = accesoDatos.getReadableDatabase();

        //SELECT id,nombre, institucion from cursos;
        String[] columnasConsulta = {"id","nombre","institucion"};

        Cursor cursor = db.query(NOMBRE_TABLA,columnasConsulta,null,null,null,null,null);

        if(cursor == null){
            return cursos;
        }

        if(cursor.moveToFirst() == false){
            return cursos;
        }

        do{
            long idCurso = cursor.getLong(0);
            String nombre = cursor.getString(1);
            String institucion = cursor.getString(2);

            Curso cursodb = new Curso(idCurso,nombre,institucion);

            cursos.add(cursodb);

        }while(cursor.moveToNext());


        cursor.close();

        return cursos;
    }
}
