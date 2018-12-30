package com.example.manue.elgourmet.activities;

import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.manue.elgourmet.Database.DataBaseRoom;
import com.example.manue.elgourmet.R;
import com.example.manue.elgourmet.Util.Comentario;
import com.example.manue.elgourmet.Util.MiReceta;
import com.example.manue.elgourmet.adapters.ComentariosAdapter;
import com.example.manue.elgourmet.adapters.MisRecetasAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class misRecetasActivity extends AppCompatActivity implements MisRecetasAdapter.OnButtonClickedListener{

    RecyclerView recyclerView;
    private MisRecetasAdapter misRecetasAdapter;
    List<MiReceta> listaMisRecetas = new ArrayList<>();
    private DataBaseRoom dbRoom;
    private MisRecetasAdapter.OnButtonClickedListener listener;

    private AlertDialog.Builder builder;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_recetas);



        FloatingActionButton fab = findViewById(R.id.fabAgregarReceta);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                createPopUp();

            }
        });

       /* Button eliminar = findViewById(R.id.misrecetasEliminar);
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onButtonCliked();
                eliminarRecetaDb();
                onButtonCliked(v,);

            }
        });*/

        dbRoom= DataBaseRoom.getInstance(this);
        new GetAsyncProducts().execute();

        recyclerView = findViewById(R.id.listaMisRecetas);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        misRecetasAdapter = new MisRecetasAdapter(listaMisRecetas, this, this);
        recyclerView.setAdapter(misRecetasAdapter);

    }
    private void createPopUp() {

        builder = new AlertDialog.Builder(this);
        View view= LayoutInflater.from(this).inflate(R.layout.popup_insertar_receta, null);

        builder.setView(view);

        dialog= builder.create();
        dialog.show();

        final EditText nombreReceta= view.findViewById(R.id.popupInsertarRecetaNombre);
        final EditText ingredientesReceta= view.findViewById(R.id.popupInsertarRecetaIngredientes);
        final EditText elaboracaionReceta= view.findViewById(R.id.popupInsertarRecetaElaboracion);
        final EditText imagenReceta= view.findViewById(R.id.popupInsertarRecetaImagen);
        Button agregarReceta= view.findViewById(R.id.popupInsertarRecetaAñadir);
        Button cancelarReceta = view.findViewById(R.id.popupInsertarRecetaCancelar);
        final ImageView preview = view.findViewById(R.id.preview);

        final String url = "http://i68.tinypic.com/53oh34.jpg";
        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imagenReceta.getText().toString().equals("")){
                    Picasso.get().load(url).fit().into(preview);
                }else{
                    Picasso.get().load(imagenReceta.getText().toString()).fit().into(preview);
                }
            }
        });

        agregarReceta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                guardarRecetaDb(imagenReceta.getText().toString(), nombreReceta.getText().toString(), ingredientesReceta.getText().toString(), elaboracaionReceta.getText().toString());
                dialog.dismiss();

            }
        });

        cancelarReceta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }


    private class GetAsyncProducts extends AsyncTask<Void, Void, List<MiReceta>> {


        @Override
        protected List<MiReceta> doInBackground(Void... voids) {

            List<MiReceta> misRecetas= dbRoom.misRecetasDAO().getRecetas();
            return misRecetas;
        }

        @Override
        protected void onPostExecute(List<MiReceta> misRecetas) {
            listaMisRecetas.addAll(misRecetas);
            misRecetasAdapter.notifyDataSetChanged();


        }
    }

    private class AsyncAddProductDB extends AsyncTask<MiReceta, Void, Long> {

        MiReceta miReceta;

        @Override
        protected Long doInBackground(MiReceta... misRecetas) {

            long id=-1;

            if (misRecetas.length!=0) {
                miReceta = misRecetas[0];
                id = dbRoom.misRecetasDAO().insertReceta(misRecetas[0]);
                miReceta.setId(id);
            }

            return id;
        }

        @Override
        protected void onPostExecute(Long id) {
            if (id == -1){
                Toast.makeText(misRecetasActivity.this, "Error al introducir la receta", Toast.LENGTH_SHORT).show();
            } else {
                listaMisRecetas.add(0,miReceta);
                Toast.makeText(misRecetasActivity.this, "Receta insertada con exito!", Toast.LENGTH_SHORT).show();
                misRecetasAdapter.notifyItemInserted(0);

            }
        }
    }

    private class AsynDeleteProductDB extends AsyncTask <MiReceta, Void, Integer> {
        private int position;
        public AsynDeleteProductDB(int position) {
            this.position=position;
        }

        @Override
        protected Integer doInBackground(MiReceta... misRecetas) {

            int deletedrows=0;

            if (misRecetas.length!=0) {

                deletedrows=dbRoom.misRecetasDAO().deleteReceta(misRecetas[0]);

            }

            return deletedrows;

        }

        @Override
        protected void onPostExecute(Integer deletedRows) {
            if (deletedRows == 0){
                Toast.makeText(misRecetasActivity.this, "Error al eliminar la receta", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(misRecetasActivity.this, "Receta eliminada!", Toast.LENGTH_SHORT).show();
                listaMisRecetas.remove(position);
                misRecetasAdapter.notifyItemRemoved(position);
                misRecetasAdapter.notifyItemRangeRemoved(position, misRecetasAdapter.getItemCount());
            }
        }
    }

    private void guardarRecetaDb(String imagen, String nombre, String ingredientes, String elaboracion){
        MiReceta miReceta = new MiReceta(imagen, nombre, ingredientes, elaboracion);
        new AsyncAddProductDB().execute(miReceta);
    }

    @Override
    public void onButtonCliked(View v, int position) {

        if (v.getId()==R.id.misrecetasEliminar) {
            eliminarRecetaDb(position);

            Toast.makeText(this, "position: "+ position, Toast.LENGTH_SHORT).show();
        }
    }

    private void eliminarRecetaDb(int position){
        MiReceta miReceta = listaMisRecetas.get(position);

        new AsynDeleteProductDB(position).execute(miReceta);
    }
}
