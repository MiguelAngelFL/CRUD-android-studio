package com.fic.crudcursos.capadatos;

public class Curso {

    private long id;
    private String nombre;
    private String institucion;

    public Curso(long id,String nombre, String institucion){
        this.id = id;
        this.nombre = nombre;
        this.institucion = institucion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "id='" + id + '\'' +
                "nombre='" + nombre + '\'' +
                ", institucion='" + institucion + '\'' +
                '}';
    }
}
