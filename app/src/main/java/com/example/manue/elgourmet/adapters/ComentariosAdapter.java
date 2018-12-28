package com.example.manue.elgourmet.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.manue.elgourmet.R;
import com.example.manue.elgourmet.Util.Comentario;

import java.util.List;

public class ComentariosAdapter extends RecyclerView.Adapter<ComentariosAdapter.ViewHolder> {

    private Context mContext;
    private List<Comentario> comentarios;

    public ComentariosAdapter(Context mContext, List<Comentario> mComment) {
        this.mContext = mContext;
        this.comentarios = mComment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.comentario, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        final Comentario comentario = comentarios.get(i);

        viewHolder.comment.setText(comentario.getComentario());

    }

    @Override
    public int getItemCount() {
        return comentarios.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView comment;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            comment = itemView.findViewById(R.id.comment);
        }


    }

}
