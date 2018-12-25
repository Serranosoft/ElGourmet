package com.example.manue.elgourmet;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


    public class MainActivity extends AppCompatActivity implements RecetasFragment.OnRecetaSelected {




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
    }
