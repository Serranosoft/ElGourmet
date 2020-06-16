package com.example.manue.elgourmet;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

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
}
