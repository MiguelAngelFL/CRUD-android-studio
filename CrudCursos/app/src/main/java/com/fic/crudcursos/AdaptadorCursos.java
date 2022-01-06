package com.fic.crudcursos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fic.crudcursos.capadatos.Curso;

import org.w3c.dom.Text;

import java.util.List;

public class AdaptadorCursos extends RecyclerView.Adapter<AdaptadorCursos.ViewHolder> {

    private List<Curso> listaCursos;


    public AdaptadorCursos(List<Curso> cursos){
        this.listaCursos = cursos;
    }

    public void setListaCursos(List<Curso> listaDeCursos){
        this.listaCursos = listaDeCursos;
    }

    @NonNull
    @Override
    public AdaptadorCursos.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View cursosView = inflater.inflate(R.layout.fila_curso,parent, false);

        AdaptadorCursos.ViewHolder viewHolder = new AdaptadorCursos.ViewHolder(cursosView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorCursos.ViewHolder holder, int position) {
        Curso curso = listaCursos.get(position);

        TextView nombreTextView = holder.nombreTextView;
        nombreTextView.setText(curso.getNombre());

        TextView institucionTextView = holder.institucionTextView;
        institucionTextView.setText(curso.getInstitucion());

    }

    @Override
    public int getItemCount() {
        return listaCursos.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView nombreTextView;
        public TextView institucionTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nombreTextView = (TextView) itemView.findViewById(R.id.tvNombre);
            institucionTextView = (TextView) itemView.findViewById(R.id.tvInstitucion);
        }
    }

}
