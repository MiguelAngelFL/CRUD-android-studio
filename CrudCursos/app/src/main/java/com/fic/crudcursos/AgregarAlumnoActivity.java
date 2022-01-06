package com.fic.crudcursos;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fic.crudcursos.capaaplicacion.AlumnoController;
import com.fic.crudcursos.capaaplicacion.CursoController;
import com.fic.crudcursos.capadatos.Alumnos;
import com.fic.crudcursos.capadatos.Curso;

public class AgregarAlumnoActivity extends AppCompatActivity {

    private Button btnAltaUsuarios, btnCancelarNuevoAlumno;
    private EditText etNombre, etApellidoPa,etApellidoMa,etCorreo, etTelefono;
    private AlumnoController alumnoController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alta_de_usuarios);


        etNombre = (EditText) findViewById(R.id.etNombreAlumno);
        etApellidoPa = (EditText) findViewById(R.id.etApellidoPa);
        etApellidoMa= (EditText) findViewById(R.id.etApellidoMa);
        etCorreo = (EditText) findViewById(R.id.etCorreo);
        etTelefono = (EditText) findViewById(R.id.etTelefono);
        btnAltaUsuarios = (Button) findViewById(R.id.btnAltaUsuarios);
        btnCancelarNuevoAlumno = (Button) findViewById(R.id.btnCancelarNuevoAlumno);


        alumnoController = new AlumnoController(AgregarAlumnoActivity.this);

        //Lógica para agregar un nuevo curso
        btnAltaUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombreAlumno = etNombre.getText().toString();
                String ApellidoPa = etApellidoPa.getText().toString();
                String ApellidoMa = etApellidoMa.getText().toString();
                String correo = etCorreo.getText().toString();
                String telefono = etTelefono.getText().toString();

                etNombre.setError(null);
                etApellidoPa.setError(null);
                etApellidoMa.setError(null);
                etCorreo.setError(null);
                etTelefono.setError(null);



                if (nombreAlumno.isEmpty()) {
                    etNombre.setError("Debe escribir el nombre del curso");
                    etNombre.requestFocus();
                    return;
                }

                if (ApellidoPa.isEmpty()) {
                    etApellidoPa.setError("Debe escribir el nombre de la institución");
                    etApellidoPa.requestFocus();
                    return;
                }
                if (ApellidoMa.isEmpty()) {
                    etApellidoMa.setError("Debe escribir el nombre de la institución");
                    etApellidoMa.requestFocus();
                    return;
                }

                if (correo.isEmpty()) {
                    etCorreo.setError("Debe escribir el nombre del curso");
                    etCorreo.requestFocus();
                    return;
                }
                if (telefono.isEmpty()) {
                    etTelefono.setError("Debe escribir el nombre del curso");
                    etTelefono.requestFocus();
                    return;
                }

                Alumnos alumno = new Alumnos(0, nombreAlumno, ApellidoPa, ApellidoMa, correo, telefono);


                long id = alumnoController.guardarAlumno(alumno);

                if (id == -1) {
                    Toast.makeText(AgregarAlumnoActivity.this, "Ocurrió un error al almacenar el nuevo curso", Toast.LENGTH_SHORT).show();
                } else {
                    finish();
                }
            }
        });


        //Se cancela y se cierra la actividad.
        btnCancelarNuevoAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}