package com.example.manue.elgourmet.activities;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.manue.elgourmet.R;
import com.example.manue.elgourmet.Util.Receta;
import com.example.manue.elgourmet.fragments.RecetasFragment;
import com.example.manue.elgourmet.fragments.detallefragment;


public class MainActivity extends AppCompatActivity implements RecetasFragment.OnRecetaSelected {

    boolean isMultiPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setMultiPanel();

    }

        @Override
        public void onChange(Receta receta) {


            if(!isMultiPanel){
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra("Receta", receta);
                startActivity(intent);
            }else{
                detallefragment detailsFragment = (detallefragment) getSupportFragmentManager().findFragmentById(R.id.detalleFragment);
                detailsFragment.mostrarReceta(receta);

            }


        }

    private void setMultiPanel(){
        isMultiPanel = (getSupportFragmentManager().findFragmentById(R.id.detalleFragment) != null);
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
                Intent intent3 = new Intent(MainActivity.this, misRecetasActivity.class);
                startActivity(intent3);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}


