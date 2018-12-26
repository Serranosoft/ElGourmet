package com.example.manue.elgourmet.fragments;


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

import com.android.volley.RequestQueue;
import com.example.manue.elgourmet.activities.DetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import com.example.manue.elgourmet.Util.Receta;
import com.example.manue.elgourmet.R;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class detallefragment extends Fragment {


    public detallefragment() {
        // Required empty public constructor
    }



    private List<Receta> recetaLista;
    private RequestQueue mRequestQueue;

    ImageView imagen;
    TextView titulo;
    TextView ingredientes;
    TextView elaboracion;
    Button link;
    Button comentarios;


    private static final String api = "https://www.themealdb.com/api/json/v1/1/latest.php";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_detalle_recetas, container, false);


        recetaLista = new ArrayList<>();
        imagen = view.findViewById(R.id.detalleImagen);
        titulo = view.findViewById(R.id.detalleTitulo);
        ingredientes = view.findViewById(R.id.detalleIngredientes);
        elaboracion = view.findViewById(R.id.detalleElaboracion);
        link = view.findViewById(R.id.iconoLink);
        comentarios = view.findViewById(R.id.detalleBoton);
        return view;
    }


    public void mostrarReceta(final Receta receta){

        String url = receta.getImagenUrl();
        Picasso.get().load(url).fit().into(imagen);
        titulo.setText(receta.getNombreReceta());
        for (int i = 0; i<20;i++){
            ingredientes.append(receta.getIngredientes()[i]+" ,");
        }
        elaboracion.setText(receta.getInstruccionesReceta());

        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(receta.getUrl().equals("")){
                    Toast.makeText(getActivity(), "Esta receta no tiene página web de referencia o video tutorial", Toast.LENGTH_SHORT).show();
                }else{
                    Uri uri = Uri.parse(receta.getUrl());

                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }

            }
        });

        /*.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsActivity., InsertarComentarioFragment.class);
            }
        });*/





    }




}
