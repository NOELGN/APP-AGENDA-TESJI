package com.tesji.agendatesji;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tesji.agendatesjimodelo.ConexionSQLite;

public class ReporteCarrera extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reporte_carrera);
        ListView list;
        list = (ListView) findViewById(R.id.listCarreras);

        ConexionSQLite conexion = new ConexionSQLite(
                this,
                "agendaTesji.db",
                null, 1);

        SQLiteDatabase bd = conexion.getReadableDatabase();

        Cursor fila = bd.rawQuery("SELECT * FROM carrera", null);
        int cont = fila.getCount();

        String array[] = new String[cont];
        fila.moveToFirst();

        int x=0;

        while(x<cont){
            array[x] = fila.getString(0) + " | "
                       + fila.getString(1) + " | "
                       + fila.getString(2);
            x++;
            fila.moveToNext();
        }

        ArrayAdapter<String> adaptador=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,array);
        list.setAdapter(adaptador);
        bd.close();
    }
}
