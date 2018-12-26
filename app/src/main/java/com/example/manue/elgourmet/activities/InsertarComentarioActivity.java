package com.example.manue.elgourmet.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.manue.elgourmet.R;

public class InsertarComentarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_comentario);
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
}
