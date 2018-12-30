package com.example.manue.elgourmet.adapters;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.manue.elgourmet.R;
import com.example.manue.elgourmet.Util.MiReceta;
import com.example.manue.elgourmet.Util.Receta;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MisRecetasAdapter extends RecyclerView.Adapter<MisRecetasAdapter.ViewHolder> {


    private List<MiReceta> misRecetas;
    private Context context;
    private OnButtonClickedListener listener;

    public MisRecetasAdapter(List<MiReceta> recetas, Context context, OnButtonClickedListener listener) {
        this.misRecetas = recetas;
        this.context = context;
        this.listener=listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.mis_recetas, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        final MiReceta miReceta = misRecetas.get(i);

        viewHolder.nombreReceta.setText(miReceta.getNombreReceta());
        viewHolder.ingredienteReceta.setText("• "+miReceta.getIngredientes()+"\n"+"\n");
        viewHolder.elaboracionReceta.setText(miReceta.getInstruccionesReceta());



        String url = "http://i68.tinypic.com/53oh34.jpg";
        if(miReceta.getImagenUrl().equals("")){
            Picasso.get().load(url).fit().into(viewHolder.imagenUrl);
        }else{
            Picasso.get().load(miReceta.getImagenUrl()).fit().into(viewHolder.imagenUrl);
        }




    }

    @Override
    public int getItemCount() {
        return misRecetas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nombreReceta;
        public ImageView imagenUrl;
        public TextView ingredienteReceta;
        public TextView elaboracionReceta;

        public ImageView preview;
        public Button borrarReceta;

        public ViewHolder(View itemView) {
            super(itemView);



            nombreReceta = itemView.findViewById(R.id.misrecetasTitulo);
            imagenUrl = itemView.findViewById(R.id.misrecetasImagen);
            ingredienteReceta = itemView.findViewById(R.id.misrecetasIngredientes);
            elaboracionReceta = itemView.findViewById(R.id.misrecetasElaboracion);
            borrarReceta = itemView.findViewById(R.id.misrecetasEliminar);
            preview = itemView.findViewById(R.id.preview);


            borrarReceta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onButtonCliked(v, getAdapterPosition());
                }
            });
        }
    }
    public interface OnButtonClickedListener{
        void onButtonCliked(View v, int position);
    }
}
