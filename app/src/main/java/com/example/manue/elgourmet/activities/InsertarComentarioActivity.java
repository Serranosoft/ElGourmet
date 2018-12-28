package com.example.manue.elgourmet.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manue.elgourmet.Database.DataBaseRoom;
import com.example.manue.elgourmet.R;
import com.example.manue.elgourmet.Util.Comentario;
import com.example.manue.elgourmet.adapters.ComentariosAdapter;

import java.util.ArrayList;
import java.util.List;

public class InsertarComentarioActivity extends AppCompatActivity {
    EditText comentarioComentario;
    TextView post;

    RecyclerView recyclerView;
    private ComentariosAdapter comentariosAdapter;


    String comentario;
    List<Comentario> listaComentarios = new ArrayList<>();
    private DataBaseRoom dbRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentarios);

        comentarioComentario = findViewById(R.id.comentario);
        post = findViewById(R.id.post);

        dbRoom= DataBaseRoom.getInstance(this);
        new GetAsyncProducts().execute();

        recyclerView = findViewById(R.id.recyclerViewComentario);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        comentariosAdapter = new ComentariosAdapter(this, listaComentarios);
        recyclerView.setAdapter(comentariosAdapter);

        comentario  = comentarioComentario.toString();
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(comentarioComentario.getText().toString().equals("")){
                    Toast.makeText(InsertarComentarioActivity.this, "No puedes enviar comentarios vacios", Toast.LENGTH_SHORT).show();
                }else{
                    guardarComentarioDb(comentarioComentario.getText().toString());

                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.favoritos:
                Toast.makeText(this, "Prueba favoritos", Toast.LENGTH_LONG).show();
                return true;
            case R.id.agregarReceta:
                Intent intent = new Intent(InsertarComentarioActivity.this, AgregarActivity.class);
                startActivity(intent);
                return true;
            case R.id.misrecetas:
                Toast.makeText(this, "Prueba misrecetas", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class GetAsyncProducts extends AsyncTask<Void, Void, List<Comentario>> {


        @Override
        protected List<Comentario> doInBackground(Void... voids) {

            List<Comentario> comentarios= dbRoom.comentarioDAO().getComentarios();
            return comentarios;
        }

        @Override
        protected void onPostExecute(List<Comentario> comentarios) {
            listaComentarios.addAll(comentarios);
            comentariosAdapter.notifyDataSetChanged();


        }
    }

    private class AsyncAddProductDB  extends AsyncTask<Comentario, Void, Long> {

        Comentario comentario;

        @Override
        protected Long doInBackground(Comentario... comentarios) {

            long id=-1;

            if (comentarios.length!=0) {
                comentario = comentarios[0];
                id = dbRoom.comentarioDAO().insertComentario(comentarios[0]);
                comentario.setId(id);
                comentariosAdapter.notifyItemInserted(0);
            }

            return id;
        }

        @Override
        protected void onPostExecute(Long id) {
            if (id == -1){
                Toast.makeText(InsertarComentarioActivity.this, "Error al introducir el comentario en la base de datos", Toast.LENGTH_SHORT).show();
            } else {
                listaComentarios.add(0,comentario);
                Toast.makeText(InsertarComentarioActivity.this, "Comentario insertado con exito!", Toast.LENGTH_SHORT).show();


            }
        }
    }

    private void guardarComentarioDb(String comentario){
        Comentario c = new Comentario(comentario);
        new AsyncAddProductDB().execute(c);



    }




}
