package com.tesji.agendatesji;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.tesji.agendatesjimodelo.ConexionSQLite;

public class AltaAlumno extends AppCompatActivity {

    EditText txtNoControl;
    EditText txtNombre;
    EditText txtApellido1;
    EditText txtApellido2;
    EditText txtEmail;
    EditText txtTelefono;
    Spinner cmbCarreras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alta_alumno);

        txtNoControl = (EditText) findViewById(R.id.etNoControlAltaAlum);
        txtNombre = (EditText) findViewById(R.id.etNombreAltaAlum);
        txtApellido1 = (EditText) findViewById(R.id.etApeUnoAltaAlum);
        txtApellido2 = (EditText) findViewById(R.id.etApeDosAltaAlum);
        txtEmail = (EditText) findViewById(R.id.etEmailAltaAlum);
        txtTelefono = (EditText) findViewById(R.id.etTelAltaAlum);
        cmbCarreras = (Spinner) findViewById(R.id.spCarreras);

        //llenando el combo con los datos de la tabla carrera
        llenarCarreras();
    }
    //Método para llenar el Spinner (combo) con las carreras
    private  void llenarCarreras(){
        ConexionSQLite conexion = new ConexionSQLite(
                this,
                "agendaTesji.db",
                null, 1);

        SQLiteDatabase bd = conexion.getReadableDatabase();

        Cursor fila = bd.rawQuery("SELECT * FROM carrera", null);
        int cont = fila.getCount();

        String array[] = new String[cont+1];
        array[0] = "--Seleccione Carrera--";
        fila.moveToFirst();

        int x=1;

        while(x <= cont){
            array[x] = fila.getString(1);
            x++;
            fila.moveToNext();
        }

        ArrayAdapter<String> adaptador=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,array);
        cmbCarreras.setAdapter(adaptador);
        bd.close();
    }

    public void guardarAlumno(View v){
        ConexionSQLite conexion = new ConexionSQLite(
                this,
                "agendaTesji.db",
                null, 1);

        SQLiteDatabase bd = conexion.getReadableDatabase();
        //Extraer la clave de la carrera
        String buscClaveCarrera = cmbCarreras.getSelectedItem().toString();
        Cursor claveCarrera = bd.rawQuery("SELECT clvCarrera FROM carrera WHERE nombCarrera='"+buscClaveCarrera+"'",null);
        int cont = claveCarrera.getCount();

        String array[] = new String[cont];
        claveCarrera.moveToFirst();

        int x=0;

        while(x < cont){
            array[x]=claveCarrera.getString(0);
            x++;
            claveCarrera.moveToNext();
        }

//________________________  Guardando Alumno

        if(cont > 0){  //Validando que si seleccionó Carrera

            String noControl = txtNoControl.getText().toString();
            String nombre = txtNombre.getText().toString();
            String apellido1 = txtApellido1.getText().toString();
            String apellido2 = txtApellido2.getText().toString();
            String email = txtEmail.getText().toString();
            String tel = txtTelefono.getText().toString();
            String idCarrera = array[0];//id extraido de la tabla depto que coincide con el seleccionado del spiner

            ContentValues registro = new ContentValues();
            registro.put("noControl", noControl);//Base datos
            registro.put("nombre", nombre);
            registro.put("apellido1", apellido1);
            registro.put("apellido2", apellido2);
            registro.put("email", email);
            registro.put("tel", tel);
            registro.put("clvCarrera", idCarrera);

            bd.insert("alumno", null, registro);
            limpiar();

            Toast.makeText(this, "Se almacenaron los datos del Alumno", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Seleccione Carrera", Toast.LENGTH_LONG).show();
            cmbCarreras.requestFocus();
        }
        bd.close();
    }

    public  void cancelar(View v){
        limpiar();
    }

    public void regresar(View v){
        finish();
    }

    private void limpiar(){
        txtNoControl.setText(null);
        txtNombre.setText(null);
        txtApellido1.setText(null);
        txtApellido2.setText(null);
        txtEmail.setText(null);
        txtTelefono.setText(null);
        cmbCarreras.setSelection(0);
        txtNoControl.requestFocus();
    }
}
