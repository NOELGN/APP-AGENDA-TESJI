package com.tesji.agendatesji;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.tesji.agendatesjimodelo.ConexionSQLite;

public class AltaCarrera extends AppCompatActivity {
    EditText txtCarrera;
    EditText txtDescripcion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alta_carrera);
        txtCarrera =  (EditText) findViewById(R.id.etCarreraAlta);
        txtDescripcion = (EditText) findViewById(R.id.etDescripcion);
    }
    public void guardarCarrera(View v) {
        //Crear conexion con SQLite
        ConexionSQLite conexion = new ConexionSQLite(this, "agendaTesji.db", null, 1);
        //Crear objeto de la clase SQLiteDatebase y darle permiso de escritura
        SQLiteDatabase db = conexion.getWritableDatabase();
        //Extraer los datos del formulario (Actividad)
        String carrera = txtCarrera.getText().toString();
        String descrip = txtDescripcion.getText().toString();
        //Validamos que las cajas de texto no esten vacias
        if (carrera.trim().length() == 0) {
            txtCarrera.setError("Debe ingresar Carrera");
            txtCarrera.requestFocus();
        } else if (descrip.trim().length() == 0) {
            txtDescripcion.setError("Debe ingresar Descripción");
            txtDescripcion.requestFocus();
        } else {
            //Agregando datos a la tabla carrera
            //Preparamos el registro con un objeto ContentValues
            ContentValues registro = new ContentValues();
            registro.put("nombCarrera", carrera);
            registro.put("descripcion", descrip);
            //Insertar en la tabla carrera
            db.insert("carrera", null, registro);
            Toast.makeText(this, "Carrera almacenada correctamente",
                    Toast.LENGTH_SHORT).show();
            limpiar();
            //Cerramos la conexion
            conexion.close();
        }
    }
    public void regresar(View v){
        finish();
    }

    public void cancelar(View v){
        limpiar();
    }

    private void limpiar(){
        txtCarrera.setText(null);
        txtDescripcion.setText(null);
        txtCarrera.requestFocus();
    }
}
