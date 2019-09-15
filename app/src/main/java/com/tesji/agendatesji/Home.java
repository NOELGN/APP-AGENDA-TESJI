package com.tesji.agendatesji;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
    }
    public void irAltaCarrera(View v){
        Intent i = new Intent(Home.this, AltaCarrera.class);
        startActivity(i);
    }
    public void irReporteCarrera(View v){
        Intent i = new Intent(Home.this, ReporteCarrera.class);
        startActivity(i);
    }
    public void irBuscActElimCarrera(View v){
        Intent i = new Intent(Home.this, BuscActElimCarrera.class);
        startActivity(i);
    }
    public void irAltaAlumno(View v){
        Intent i = new Intent(Home.this, AltaAlumno.class);
        startActivity(i);
    }
    public void irVerAgenda(View v){
        Intent i = new Intent(Home.this, VerAgenda.class);
        startActivity(i);
    }
}
