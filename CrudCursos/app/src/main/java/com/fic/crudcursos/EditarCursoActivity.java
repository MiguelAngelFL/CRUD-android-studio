package com.fic.crudcursos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fic.crudcursos.capaaplicacion.CursoController;
import com.fic.crudcursos.capadatos.Curso;

public class EditarCursoActivity extends AppCompatActivity {

    private EditText etEditarNombreCurso, etEditarInstitucion;
    private Button btnGuardarCambios, btnCancelarEdicion;
    private Curso curso;
    private CursoController cursoController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_curso);


        // Recuperar datos que enviaron
        Bundle extras = getIntent().getExtras();
        // Si no hay datos (cosa rara) salimos
        if (extras == null) {
            finish();
            return;
        }
        cursoController = new CursoController(EditarCursoActivity.this);

        long idCurso = extras.getLong("idCurso");
        String nombreCurso = extras.getString("nombreCurso");
        String institucion = extras.getString("institucion");
        curso = new Curso(idCurso, nombreCurso, institucion);

        etEditarNombreCurso = findViewById(R.id.etEditarNombre);
        etEditarInstitucion = findViewById(R.id.etEditarInstitucion);
        btnCancelarEdicion = findViewById(R.id.btnCancelarEdicionCurso);
        btnGuardarCambios = findViewById(R.id.btnGuardarCambiosCurso);


        etEditarInstitucion.setText(curso.getInstitucion());
        etEditarNombreCurso.setText(curso.getNombre());

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
                // Remover previos errores si existen
                etEditarNombreCurso.setError(null);
                etEditarInstitucion.setError(null);
                // Crear la mascota con los nuevos cambios pero ponerle
                // el id de la anterior
                String nuevoNombre = etEditarNombreCurso.getText().toString();
                String nuevaInstitucion = etEditarInstitucion.getText().toString();
                if (nuevoNombre.isEmpty()) {
                    etEditarNombreCurso.setError("Escriba el nombre");
                    etEditarNombreCurso.requestFocus();
                    return;
                }
                if (nuevaInstitucion.isEmpty()) {
                    etEditarInstitucion.setError("Escriba la institución");
                    etEditarInstitucion.requestFocus();
                    return;
                }

                // Si llegamos hasta aquí es porque los datos ya están validados
                Curso cursoModificado = new Curso(curso.getId(), nuevoNombre, nuevaInstitucion);
                int filasModificadas = cursoController.modificarCurso(cursoModificado);
                if (filasModificadas != 1) {
                    // De alguna forma ocurrió un error porque se debió modificar únicamente una fila
                    Toast.makeText(EditarCursoActivity.this, "Error guardando cambios. Intente de nuevo.", Toast.LENGTH_SHORT).show();
                } else {
                    // Si las cosas van bien, volvemos a la principal
                    // cerrando esta actividad
                    finish();
                }
            }
        });

    }
}