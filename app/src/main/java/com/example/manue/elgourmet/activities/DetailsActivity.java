package com.example.manue.elgourmet.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.manue.elgourmet.R;
import com.example.manue.elgourmet.Util.Receta;
import com.example.manue.elgourmet.fragments.detallefragment;

public class DetailsActivity  extends AppCompatActivity {


    private Button comentarios;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent intent = getIntent();

        if(intent != null){
            Receta receta = (Receta) intent.getSerializableExtra("Receta");
            detallefragment fragment = (detallefragment) getSupportFragmentManager().findFragmentById(R.id.detalleFragment);
            fragment.mostrarReceta(receta);
        }

        /*comentarios = findViewById(R.id.detalleBoton);
        comentarios.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(DetailsActivity.this, InsertarComentarioActivity.class);
                startActivity(intent2);
            }
        });*/

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
            case R.id.misrecetas:
                Intent intent3 = new Intent(DetailsActivity.this, misRecetasActivity.class);
                startActivity(intent3);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
