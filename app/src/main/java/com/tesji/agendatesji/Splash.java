package com.tesji.agendatesji;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends AppCompatActivity {
    private final int DURACION = 4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        cargarSplash();
    }

    private void cargarSplash(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Enlazar al Home
                Intent i = new Intent(Splash.this, Home.class);
                startActivity(i);
            }
        },DURACION);
    }
}
