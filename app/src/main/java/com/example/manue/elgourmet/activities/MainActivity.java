package com.example.manue.elgourmet.activities;


import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manue.elgourmet.Database.DataBaseRoom;
import com.example.manue.elgourmet.R;
import com.example.manue.elgourmet.Util.Comentario;
import com.example.manue.elgourmet.Util.Receta;
import com.example.manue.elgourmet.fragments.RecetasFragment;

import java.util.List;


public class MainActivity extends AppCompatActivity implements RecetasFragment.OnRecetaSelected {


    private DataBaseRoom dbRoom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

        @Override
        public void onChange(Receta receta) {
            Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
            intent.putExtra("Receta", receta);
            startActivity(intent);
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
                Intent intent = new Intent(MainActivity.this, AgregarActivity.class);
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


