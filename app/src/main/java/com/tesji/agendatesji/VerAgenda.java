package com.tesji.agendatesji;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tesji.agendatesjimodelo.ConexionSQLite;

import java.util.List;

public class VerAgenda extends AppCompatActivity {
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ver_agenda);
        list = (ListView) findViewById(R.id.listAgenda);

        ConexionSQLite conexion = new ConexionSQLite(
                this,
                "agendaTesji.db",
                null, 1);

        SQLiteDatabase bd = conexion.getReadableDatabase();

        Cursor fila = bd.rawQuery("SELECT a.noControl, a.nombre || ' ' || a.apellido1 || ' ' || a.apellido2, a.email, a.tel, c.nombCarrera FROM alumno AS a INNER JOIN carrera AS c ON a.clvCarrera = c.clvCarrera", null);
        int cont = fila.getCount();

        String array[] = new String[cont];
        fila.moveToFirst();

        int x=0;

        while(x<cont){
            array[x] = fila.getString(0) + " | "
                    + fila.getString(1) + " | "
                    + fila.getString(2) + " | "
                    + fila.getString(3) + " | "
                    + fila.getString(4);
            x++;
            fila.moveToNext();
        }

        ArrayAdapter<String> adaptador=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,array);
        list.setAdapter(adaptador);
        bd.close();
    }
}
