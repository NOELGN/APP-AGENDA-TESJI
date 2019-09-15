package com.tesji.agendatesji;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.tesji.agendatesjimodelo.ConexionSQLite;

public class BuscActElimCarrera extends AppCompatActivity {
    EditText txtNoCarrera;
    EditText txtNombCarrera;
    EditText txtDescripcion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busc_act_elim_carrera);
        txtNoCarrera = (EditText) findViewById(R.id.etNoCarreraBusc);
        txtNombCarrera = (EditText) findViewById(R.id.etNombCarreraBusc);
        txtDescripcion = (EditText) findViewById(R.id.etDescripcionBusc);
    }

    public void buscar(View v){
        ConexionSQLite conexion = new ConexionSQLite(this, "agendaTesji.db", null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        String id = txtNoCarrera.getText().toString();

        Cursor fila = db.rawQuery("SELECT * FROM carrera WHERE clvCarrera="+id,null);

        if(fila.moveToFirst()){
            txtNombCarrera.setText(fila.getString(1));
            txtDescripcion.setText(fila.getString(2));
        }else{
            Toast.makeText(this, "No existe Carrera con el ID: "+id, Toast.LENGTH_LONG).show();
        }
        db.close();
    }

    public void actualizar(View v){
        ConexionSQLite conexion = new ConexionSQLite(this, "agendaTesji.db", null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        String id = txtNoCarrera.getText().toString();
        String carrera = txtNombCarrera.getText().toString();
        String descripcion = txtDescripcion.getText().toString();

        ContentValues registro = new ContentValues();

        registro.put("nombCarrera", carrera);
        registro.put("descripcion", descripcion);

        int cant = db.update("carrera", registro, "clvCarrera="+id, null);
        db.close();

        if(cant==1){
            Toast.makeText(this, "Se modificó Carrera con Clave= "+id, Toast.LENGTH_LONG).show();
            limpiar();
        }else{
            Toast.makeText(this, "No existe Carrera con Clave= "+id, Toast.LENGTH_LONG).show();
        }
        db.close();
    }

    public void eliminar(View v){
        ConexionSQLite conexion = new ConexionSQLite(this, "agendaTesji.db", null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        String id = txtNoCarrera.getText().toString();

        int cant = db.delete("carrera", "clvCarrera="+id, null);
        db.close();

        if(cant==1){
            Toast.makeText(this, "Se Eliminó la Carrera con Clave: "+id, Toast.LENGTH_LONG).show();
            limpiar();
        }
        else{
            Toast.makeText(this, "No se puede borrar porque no existe la Carrera", Toast.LENGTH_LONG).show();
        }
        db.close();
    }

    public void regresar(View v){

        finish();
    }

    private void limpiar(){
        txtNoCarrera.setText(null);
        txtNombCarrera.setText(null);
        txtDescripcion.setText(null);
        txtNoCarrera.requestFocus();
    }
}
