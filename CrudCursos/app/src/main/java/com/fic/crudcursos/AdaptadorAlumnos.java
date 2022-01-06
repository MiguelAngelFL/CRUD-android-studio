package com.fic.crudcursos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fic.crudcursos.capadatos.Alumnos;
import com.fic.crudcursos.capadatos.Curso;

import java.util.List;

public class AdaptadorAlumnos extends RecyclerView.Adapter<AdaptadorAlumnos.ViewHolder>  {

    private List<Alumnos> listaAlumnos;


    public AdaptadorAlumnos(List<Alumnos> alumnos){
        this.listaAlumnos = alumnos;
    }

    public void setListaAlumnos(List<Alumnos> listaAlumnos){
        this.listaAlumnos = listaAlumnos;
    }

    @NonNull
    @Override
    public AdaptadorAlumnos.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View alumnosView = inflater.inflate(R.layout.fila_alumno,parent, false);

        AdaptadorAlumnos.ViewHolder viewHolder = new AdaptadorAlumnos.ViewHolder(alumnosView);

        return viewHolder;
    }




    @Override
    public void onBindViewHolder(@NonNull AdaptadorAlumnos.ViewHolder holder, int position) {
        Alumnos alumnos = listaAlumnos.get(position);

        TextView nombreTextView = holder.nombreTextView;
        nombreTextView.setText(alumnos.getNombre());

        TextView apellidoPaTextView = holder.apellidoPaTextView;
        apellidoPaTextView.setText(alumnos.getApellidoPa());

        TextView apellidoMaTextView = holder.apellidoMaTextView;
        apellidoMaTextView.setText(alumnos.getApellidoMa());

        TextView correoTextView = holder.correoTextView;
        correoTextView.setText(alumnos.getCorreo());

        TextView telefonoTextView = holder.telefonoTextView;
        telefonoTextView.setText( alumnos.getTelefono());

    }

    @Override
    public int getItemCount() {
        return listaAlumnos.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView nombreTextView;
        public TextView apellidoPaTextView;
        public TextView apellidoMaTextView;
        public TextView correoTextView;
        public TextView telefonoTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nombreTextView = (TextView) itemView.findViewById(R.id.tvNombreAlumno);
            apellidoPaTextView = (TextView) itemView.findViewById(R.id.tvApellidoPa);
            apellidoMaTextView=(TextView)itemView.findViewById(R.id.tvApellidoMa);
            correoTextView=(TextView)itemView.findViewById(R.id.tvCorreo);
            telefonoTextView=(TextView)itemView.findViewById(R.id.tvTelefono);
        }
    }
}
