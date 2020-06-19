package com.example.manue.elgourmet.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.manue.elgourmet.R;

public class InitializingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent mainIntent = new Intent(InitializingActivity.this, LoginActivity.class);
                InitializingActivity.this.startActivity(mainIntent);
                InitializingActivity.this.finish();
            }
        }, 1850);
    }
}
