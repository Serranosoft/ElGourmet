package com.example.manue.elgourmet.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.manue.elgourmet.R;
import com.example.manue.elgourmet.Util.Receta;
import com.example.manue.elgourmet.fragments.detallefragment;

public class DetailsActivity  extends AppCompatActivity {

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
                Intent intent = new Intent(DetailsActivity.this, AgregarActivity.class);
                startActivity(intent);
                return true;
            case R.id.misrecetas:
                Toast.makeText(this, "Prueba misrecetas", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
