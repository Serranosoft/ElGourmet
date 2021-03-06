package com.example.manue.elgourmet.Fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manue.elgourmet.activities.InsertarComentarioActivity;
import com.squareup.picasso.Picasso;

import com.example.manue.elgourmet.Util.Receta;
import com.example.manue.elgourmet.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class InfoRecipeFragment extends Fragment {


    public InfoRecipeFragment() {
        // Required empty public constructor
    }


    ImageView imagen;
    TextView titulo;
    TextView porciones;
    TextView tiempo;
    Button link;
    Button comentarios;
    String url;
    String web;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_detalle_recetas, container, false);

        imagen = view.findViewById(R.id.detalleImagen);
        titulo = view.findViewById(R.id.detalleTitulo);
        porciones = view.findViewById(R.id.detallePorciones);
        tiempo = view.findViewById(R.id.detalleTiempo);
        link = view.findViewById(R.id.iconoLink);
        comentarios = view.findViewById(R.id.detalleBoton);
        link = view.findViewById(R.id.iconoLink);
        comentarios = view.findViewById(R.id.detalleBoton);
        return view;
    }


    public void mostrarReceta(final Receta receta){
        url = receta.getImagenUrl();
        Picasso.get().load(url).fit().into(imagen);

        titulo.setText(receta.getNombreReceta());
        porciones.setText("porciones: "+receta.getPorciones());
        tiempo.setText("tiempo de elaboración: " +receta.getTiempo()+" minutos");
        web = receta.getUrl();
        url = "https://spoonacular.com/recipeImages/"+receta.getImagenUrl();
        Picasso.get().load(url).fit().into(imagen);
        titulo.setText(receta.getNombreReceta());

        web = receta.getUrl();
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(web.equals("")){
                    Toast.makeText(getActivity(), "Esta receta no tiene página web de referencia o video tutorial", Toast.LENGTH_SHORT).show();
                }else{
                    Uri uri = Uri.parse(receta.getUrl());

                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }

            }
        });

        comentarios.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getContext(), InsertarComentarioActivity.class);
                startActivity(intent2);
            }
        });
    }

}
