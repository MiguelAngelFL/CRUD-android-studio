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

import com.fic.crudcursos.capaaplicacion.CursoController;
import com.fic.crudcursos.capadatos.Curso;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fabAgregarCurso;
    private List<Curso> listaCursos;
    private RecyclerView recyclerViewCursos;
    private AdaptadorCursos adaptadorCursos;
    private CursoController cursoController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fabAgregarCurso = findViewById(R.id.fabAgregarCurso);
        recyclerViewCursos = findViewById(R.id.rvCursos);
        cursoController = new CursoController(MainActivity.this);

        fabAgregarCurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Simplemente cambiamos de actividad
                Intent intent = new Intent(getApplicationContext(), AgregarCursoActivity.class);
                startActivity(intent);
            }
        });

        listaCursos = new ArrayList<>();
        adaptadorCursos = new AdaptadorCursos(listaCursos);

        RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewCursos.setLayoutManager(mlayoutManager);
        recyclerViewCursos.setItemAnimator(new DefaultItemAnimator());
        recyclerViewCursos.setAdapter(adaptadorCursos);

        refrescarListaCursos();


        recyclerViewCursos.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerViewCursos, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                //touch sencillo

                Curso cursoSeleccionado = listaCursos.get(position);

                Intent intent = new Intent(MainActivity.this, EditarCursoActivity.class);
                intent.putExtra("idCurso", cursoSeleccionado.getId());
                intent.putExtra("nombreCurso",cursoSeleccionado.getNombre());
                intent.putExtra("institucion",cursoSeleccionado.getInstitucion());
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {
                //touch largo
                final Curso cursoSeleccionado = listaCursos.get(position);

                AlertDialog dialog = new AlertDialog
                        .Builder(MainActivity.this)
                        .setPositiveButton("Sí, eliminar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                cursoController.eliminarCurso(cursoSeleccionado);
                                refrescarListaCursos();
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setTitle("Confirmar información")
                        .setMessage("¿Eliminar el curso " + cursoSeleccionado.getNombre() + " ?")
                        .create();
                dialog.show();
            }
        }));


    }

    @Override
    protected void onResume() {
        super.onResume();
        refrescarListaCursos();
    }

    public void refrescarListaCursos(){

        if(adaptadorCursos == null) {
            return;
        }

        listaCursos = cursoController.obtenerCursos();
        adaptadorCursos.setListaCursos(listaCursos);
        adaptadorCursos.notifyDataSetChanged();
    }
}