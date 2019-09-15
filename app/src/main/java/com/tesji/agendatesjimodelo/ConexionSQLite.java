package com.tesji.agendatesjimodelo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexionSQLite extends SQLiteOpenHelper {
    public ConexionSQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE carrera(clvCarrera INTEGER PRIMARY KEY AUTOINCREMENT, nombCarrera TEXT UNIQUE NOT NULL, descripcion TEXT UNIQUE NOT NULL)");
        db.execSQL("CREATE TABLE alumno(noControl TEXT PRIMARY KEY, nombre TEXT NOT NULL, apellido1 TEXT NOT NULL, apellido2 TEXT, email TEXT UNIQUE NOT NULL, tel TEXT UNIQUE NOT NULL, clvCarrera INTEGER NOT NULL, FOREIGN KEY(clvCarrera) REFERENCES carrera(clvCarrera))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
