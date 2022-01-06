package com.fic.crudcursos.capadatos;

public class Alumnos {

    private long id;
    private String nombre;
    private String apellidoPa;
    private String apellidoMa;
    private String correo;
    private String  telefono;


    public Alumnos(long id, String nombre, String apellidoPa, String apellidoMa, String correo, String telefono){

        this.id = id;
        this.nombre= nombre;
        this.apellidoPa= apellidoPa;
        this.apellidoMa = apellidoMa;
        this.correo = correo;
        this.telefono = telefono;

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


    public String getApellidoPa(){return apellidoPa;}

    public void setApellidoPa(String apellidoPa){this.apellidoPa = apellidoPa;}


    public String getApellidoMa(){return apellidoMa;}

    public void setApellidoMa(String apellidoMa) { this.apellidoMa = apellidoMa; }


    public String getCorreo() { return correo; }

    public void setCorreo(String correo) { this.correo = correo; }


    public String getTelefono() { return telefono; }

    public void setTelefono(String telefono) { this.telefono = telefono; }


    @Override
    public String toString() {
        return "ListaAlumnos{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidoPa='" + apellidoPa + '\'' +
                ", apellidoMa='" + apellidoMa + '\'' +
                ", correo='" + correo + '\'' +
                ", telefono=" + telefono +
                '}';
    }
}
