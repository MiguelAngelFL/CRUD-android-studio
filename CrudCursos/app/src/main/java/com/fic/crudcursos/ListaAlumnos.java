package com.fic.crudcursos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.fic.crudcursos.capaaplicacion.AlumnoController;
import com.fic.crudcursos.capaaplicacion.CursoController;
import com.fic.crudcursos.capadatos.Alumnos;
import com.fic.crudcursos.capadatos.Curso;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ListaAlumnos extends AppCompatActivity {

    private FloatingActionButton fabAgregarAlumno;
    private List<Alumnos> listaAlumnos;
    private RecyclerView recyclerViewAlumnos;
    private AdaptadorAlumnos adaptadorAlumnos;
    private AlumnoController alumnoController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_alumnos);

        fabAgregarAlumno = findViewById(R.id.fabAgregarAlumno);
        recyclerViewAlumnos = findViewById(R.id.rvAlumnos);
        alumnoController = new AlumnoController(ListaAlumnos.this);

        fabAgregarAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Simplemente cambiamos de actividad
                Intent intent = new Intent(getApplicationContext(), AgregarAlumnoActivity.class);
                startActivity(intent);
            }
        });

        listaAlumnos = new ArrayList<>();
        adaptadorAlumnos = new AdaptadorAlumnos(listaAlumnos);

        RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewAlumnos.setLayoutManager(mlayoutManager);
        recyclerViewAlumnos.setItemAnimator(new DefaultItemAnimator());
        recyclerViewAlumnos.setAdapter(adaptadorAlumnos);

        refrescarListaAlumnos();


        recyclerViewAlumnos.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerViewAlumnos, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                //touch sencillo

                Alumnos alumnoSeleccionado = listaAlumnos.get(position);

                Intent intent = new Intent(ListaAlumnos.this, EditarAlumnoActivity.class);
                intent.putExtra("idAlumno", alumnoSeleccionado.getId());
                intent.putExtra("nombreAlumno",alumnoSeleccionado.getNombre());
                intent.putExtra("ApellidoPa",alumnoSeleccionado.getApellidoPa());
                intent.putExtra("ApelllidoMa", alumnoSeleccionado.getApellidoMa());
                intent.putExtra("correo",alumnoSeleccionado.getCorreo());
                intent.putExtra("telefono", alumnoSeleccionado.getTelefono());
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {
                //touch largo
                final Alumnos alumnoSeleccionado = listaAlumnos.get(position);

                AlertDialog dialog = new AlertDialog
                        .Builder(ListaAlumnos.this)
                        .setPositiveButton("Sí, eliminar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                alumnoController.eliminarAlumno(alumnoSeleccionado);
                                refrescarListaAlumnos();
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setTitle("Confirmar información")
                        .setMessage("¿Eliminar el Alumno " + alumnoSeleccionado.getNombre() + " ?")
                        .create();
                dialog.show();
            }
        }));


    }

    @Override
    protected void onResume() {
        super.onResume();
        refrescarListaAlumnos();
    }

    public void refrescarListaAlumnos(){

        if(adaptadorAlumnos == null) {
            return;
        }

        listaAlumnos = alumnoController.obtenerAlumnos();
       adaptadorAlumnos.setListaAlumnos(listaAlumnos);
        adaptadorAlumnos.notifyDataSetChanged();
    }


}