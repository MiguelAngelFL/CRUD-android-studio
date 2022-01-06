package com.fic.crudcursos;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fic.crudcursos.capaaplicacion.AlumnoController;
import com.fic.crudcursos.capaaplicacion.CursoController;
import com.fic.crudcursos.capadatos.Alumnos;
import com.fic.crudcursos.capadatos.Curso;

public class EditarAlumnoActivity extends AppCompatActivity {

    private EditText etEditarNombreAlumno, etEditarApellidoPa, etEditarApellidoMa, etEditarCorreo, etEditarTelefono;
    private Button btnGuardarCambios, btnCancelarEdicion;
    private Alumnos alumno;
    private AlumnoController alumnoController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_alumno);


        // Recuperar datos que enviaron
        Bundle extras = getIntent().getExtras();
        // Si no hay datos (cosa rara) salimos
        if (extras == null) {
            finish();
            return;
        }
        alumnoController = new AlumnoController(EditarAlumnoActivity.this);

        long idAlumno = extras.getLong("idAlumno");
        String nombreAlumno = extras.getString("nombreAlumno");
        String apellidoPa = extras.getString("apellidoPa");
        String apellidoMa = extras.getString("apellidoMa");
        String correo = extras.getString("correo");
        String telefono = extras.getString("telefono");

        alumno = new Alumnos(idAlumno, nombreAlumno, apellidoPa,apellidoMa,correo, telefono);

        etEditarNombreAlumno = findViewById(R.id.etEditarNombre);
        etEditarApellidoPa = findViewById(R.id.etEditarApellidoPa);
        etEditarApellidoMa = findViewById(R.id.etEditarApellidoMa);
        etEditarCorreo = findViewById(R.id.etEditarCorreo);
        etEditarTelefono = findViewById(R.id.etEditarTelefono);

        btnCancelarEdicion = findViewById(R.id.btnCancelarEdicionCurso);
        btnGuardarCambios = findViewById(R.id.btnGuardarCambiosCurso);

        etEditarNombreAlumno.setText(alumno.getNombre());
        etEditarApellidoPa.setText(alumno.getApellidoPa());
        etEditarApellidoMa.setText(alumno.getApellidoMa());
        etEditarCorreo.setText(alumno.getCorreo());
        etEditarTelefono.setText(alumno.getTelefono());


        // Listener del click del botón para salir, simplemente cierra la actividad
        btnCancelarEdicion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        // Listener del click del botón que guarda cambios
        btnGuardarCambios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                // Remover previos errores si existen
                etEditarNombreAlumno.setError(null);
                etEditarApellidoPa.setError(null);
                etEditarApellidoMa.setError(null);
                etEditarCorreo.setError(null);
                etEditarTelefono.setError(null);
*/

                // Crear la mascota con los nuevos cambios pero ponerle
                // el id de la anterior
                String nuevoNombre = etEditarNombreAlumno.getText().toString();
                String NuevoApellidoPa = etEditarApellidoPa.getText().toString();
                String NuevoApellidoMa = etEditarApellidoMa.getText().toString();
                String NuevoCorreo = etEditarCorreo.getText().toString();
                String NuevoTelefono = etEditarTelefono.getText().toString();


/*
if (nuevoNombre.isEmpty()) {
                    etEditarNombreAlumno.setError("Escriba el nombre");
                    etEditarNombreAlumno.requestFocus();
                    return;
                }
                if (NuevoApellidoPa.isEmpty()) {
                    etEditarApellidoPa.setError("Escriba la institución");
                    etEditarApellidoPa.requestFocus();
                    return;
                }

                if (NuevoApellidoMa.isEmpty()) {
                    etEditarApellidoMa.setError("Escriba la institución");
                    etEditarApellidoMa.requestFocus();
                    return;
                }
                if (NuevoCorreo.isEmpty()) {
                    etEditarCorreo.setError("Escriba la institución");
                    etEditarCorreo.requestFocus();
                    return;
                }

                if (NuevoTelefono.isEmpty()) {
                    etEditarTelefono.setError("Escriba la institución");
                    etEditarTelefono.requestFocus();
                    return;
                }
*/




                // Si llegamos hasta aquí es porque los datos ya están validados
                Alumnos alumnoModificado = new Alumnos(alumno.getId(), nuevoNombre, NuevoApellidoPa, NuevoApellidoMa, NuevoCorreo, NuevoTelefono);
                int filasModificadas = alumnoController.modificarAlumno(alumnoModificado);
                if (filasModificadas != 1) {
                    // De alguna forma ocurrió un error porque se debió modificar únicamente una fila
                    Toast.makeText(EditarAlumnoActivity.this, "Error guardando cambios. Intente de nuevo.", Toast.LENGTH_SHORT).show();
                } else {
                    // Si las cosas van bien, volvemos a la principal
                    // cerrando esta actividad
                    finish();
                }
            }
        });

    }
}