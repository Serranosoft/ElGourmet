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
import com.example.manue.elgourmet.Util.Receta;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecetasFiltrarAdapter extends RecyclerView.Adapter<RecetasFiltrarAdapter.ViewHolder> {

    private List<Receta> recetas;
    private Context context;
    private OnItemClickListener listener;


    public RecetasFiltrarAdapter(List<Receta> recetas, Context context, OnItemClickListener listener) {
        this.recetas = recetas;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.receta,viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);




        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final Receta receta = recetas.get(i);

        viewHolder.nombreReceta.setText(receta.getNombreReceta());

        String url = receta.getImagenUrl();

        Picasso.get().load(url).fit().into(viewHolder.imagenUrl);


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(receta, i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recetas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nombreReceta;
        public ImageView imagenUrl;

        public ViewHolder(View itemView) {
            super(itemView);



            nombreReceta = itemView.findViewById(R.id.nombreReceta);
            imagenUrl = itemView.findViewById(R.id.imagenReceta);
        }
    }
    public interface OnItemClickListener{
        void onItemClick(Receta receta, int position);
    }
}
