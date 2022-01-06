package com.fic.crudcursos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fic.crudcursos.capaaplicacion.CursoController;
import com.fic.crudcursos.capadatos.Curso;

public class AgregarCursoActivity extends AppCompatActivity {

    private Button btnAgregarCurso, btnCancelarNuevoCurso;
    private EditText etNombre, etInstitucion;
    private CursoController cursoController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_curso);


        etNombre = (EditText) findViewById(R.id.etNombreCurso);
        etInstitucion = (EditText) findViewById(R.id.etInstitucionCurso);
        btnAgregarCurso = (Button) findViewById(R.id.btnAgregarCurso);
        btnCancelarNuevoCurso = (Button) findViewById(R.id.btnCancelarNuevoCurso);


        cursoController = new CursoController(AgregarCursoActivity.this);

        //Lógica para agregar un nuevo curso
        btnAgregarCurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombreCurso = etNombre.getText().toString();
                String institucion = etInstitucion.getText().toString();

                etNombre.setError(null);
                etInstitucion.setError(null);

                if (nombreCurso.isEmpty()) {
                    etNombre.setError("Debe escribir el nombre del curso");
                    etNombre.requestFocus();
                    return;
                }

                if (institucion.isEmpty()) {
                    etInstitucion.setError("Debe escribir el nombre de la institución");
                    etInstitucion.requestFocus();
                    return;
                }


                Curso curso = new Curso(0, nombreCurso, institucion);


                long id = cursoController.guardarCurso(curso);

                if (id == -1) {
                    Toast.makeText(AgregarCursoActivity.this, "Ocurrió un error al almacenar el nuevo curso", Toast.LENGTH_SHORT).show();
                } else {
                    finish();
                }
            }
        });


        //Se cancela y se cierra la actividad.
        btnCancelarNuevoCurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}