package com.example.manue.elgourmet.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.manue.elgourmet.Database.DataBaseRoom;
import com.example.manue.elgourmet.R;
import com.example.manue.elgourmet.Util.Comentario;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class InsertarComentarioFragment extends Fragment {


    EditText nombreComentario;
    EditText asuntoComentario;
    EditText comentarioComentario;
    Button botonComentario;

    String nombre;
    String asunto;
    String comentario;
    List<Comentario> listaComentarios = new ArrayList<Comentario>();
    private DataBaseRoom dbRoom;

    public InsertarComentarioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_insertar_comentario, container, false);
        nombreComentario = view.findViewById(R.id.comentarioNombre);
        asuntoComentario = view.findViewById(R.id.comentarioAsunto);
        comentarioComentario = view.findViewById(R.id.comentarioComentario);
        botonComentario = view.findViewById(R.id.comentarioBoton);

        dbRoom= DataBaseRoom.getINSTANCE(getActivity());

        new GetAsyncProducts().execute();
        nombre = nombreComentario.toString();
        asunto = asuntoComentario.toString();
        comentario = botonComentario.toString();
        botonComentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarComentarioDb(nombre, asunto, comentario);

            }
        });
        // Inflate the layout for this fragment
        return view;




    }

    private class GetAsyncProducts extends AsyncTask<Void, Void, List<Comentario>> {


        @Override
        protected List<Comentario> doInBackground(Void... voids) {

            List<Comentario> comentarios= dbRoom.comentarioDAO().getComentarios();
            return comentarios;
        }

        @Override
        protected void onPostExecute(List<Comentario> products) {
            listaComentarios.addAll(products);

        }
    }

    private class AsyncAddProductDB  extends AsyncTask<Comentario, Void, Long> {

        Comentario comentario;

        @Override
        protected Long doInBackground(Comentario... comentarios) {

            long id=-1;

            if (comentarios.length!=0) {
                String name = comentarios[0].getNombre();
                Log.d("Product", name);
                comentario = comentarios[0];
                id = dbRoom.comentarioDAO().insertComentario(comentarios[0]);
                comentario.setId(id);
            }

            return id;
        }

        @Override
        protected void onPostExecute(Long id) {
            if (id == -1){
                Toast.makeText(getContext(), "Error al introducir el comentario en la base de datos", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Comentario insertado con exito!", Toast.LENGTH_SHORT).show();
                listaComentarios.add(0,comentario);

            }
        }
    }

    private void guardarComentarioDb(String nombre, String asunto, String comentario){
        Comentario c = new Comentario(nombre, asunto, comentario);
        new AsyncAddProductDB().execute(c);



    }

}
