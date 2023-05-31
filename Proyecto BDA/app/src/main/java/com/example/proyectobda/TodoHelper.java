package com.example.proyectobda;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TodoHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION =1;
    public static final String DATABASE_NAME = "Fruteria.db";
    public static final String SQL_CREATE_PRODUCTOS =
            "CREATE TABLE "+ TodoContract.ProductosTab.TABLE_NAME+
                    "("+TodoContract.ProductosTab._ID+" INTEGER PRIMARY KEY, "+
                    TodoContract.ProductosTab.COLUMN_NOMBRE+" Text, "+
                    TodoContract.ProductosTab.COLUMN_CANTIDAD+" INTEGER, "+
                    TodoContract.ProductosTab.COLUMN_COSTO+" INTEGER, "+
                    TodoContract.ProductosTab.COLUMN_CODIGO+" TEXT"+")";

    public static final String SQL_CREATE_SUCURSALES =
            "CREATE TABLE "+TodoContract.SucursalesTab.TABLE_NAME+
                    "("+TodoContract.SucursalesTab._ID+" INTEGER PRIMARY KEY, "+
                    TodoContract.SucursalesTab.COLUMN_NOMBRE+" TEXT, "+
                    TodoContract.SucursalesTab.COLUMN_CIUDAD+" TEXT, "+
                    TodoContract.SucursalesTab.COLUMN_PCODIGO+" TEXT"+")";

    public static final String SQL_CREATE_PROVEEDORES =
            "CREATE TABLE "+TodoContract.ProveedoresTab.TABLE_NAME+
                    "("+TodoContract.ProveedoresTab._ID+" INTEGER PRIMARY KEY, "+
                    TodoContract.ProveedoresTab.COLUMN_NOMBRE+" TEXT, "+
                    TodoContract.ProveedoresTab.COLUMN_CIUDAD+" TEXT, "+
                    TodoContract.ProveedoresTab.COLUMN_PCODIGO+" TEXT"+")";

    public TodoHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public static final String SQL_DELETE_PRODUCTOS = "DROP TABLE IF EXISTS "+TodoContract.ProductosTab.TABLE_NAME;
    public static final String SQL_DELETE_PROVEEDORES = "DROP TABLE IF EXISTS "+TodoContract.ProveedoresTab.TABLE_NAME;
    public static final String SQL_DELETE_SUCURSALES = "DROP TABLE IF EXISTS "+TodoContract.SucursalesTab.TABLE_NAME;

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(SQL_CREATE_PRODUCTOS);
        db.execSQL(SQL_CREATE_PROVEEDORES);
        db.execSQL(SQL_CREATE_SUCURSALES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(SQL_DELETE_PRODUCTOS);
        db.execSQL(SQL_DELETE_PROVEEDORES);
        db.execSQL(SQL_DELETE_SUCURSALES);
        onCreate(db);
    }

    public void insertProve(String nombre, String ciudad , String codigo){


        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM "+TodoContract.ProveedoresTab.TABLE_NAME +" WHERE Nombre = ?";
        Cursor cursor = db.rawQuery(query, new String[]{nombre});

        if(cursor.getCount() == 0) {
            ContentValues values = new ContentValues();
            values.put(TodoContract.ProveedoresTab.COLUMN_NOMBRE, nombre);
            values.put(TodoContract.ProveedoresTab.COLUMN_CIUDAD, ciudad);
            values.put(TodoContract.ProveedoresTab.COLUMN_PCODIGO, codigo);
            db.insert(TodoContract.ProveedoresTab.TABLE_NAME, null, values);
        }
        cursor.close();
        db.close();
    }

    public void insertRe(String nombre, String ciudad , String codigo){

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM "+TodoContract.SucursalesTab.TABLE_NAME+" WHERE Nombre = ?";
        Cursor cursor = db.rawQuery(query, new String[]{nombre});

        if(cursor.getCount() == 0) {
            ContentValues values = new ContentValues();
            values.put(TodoContract.SucursalesTab.COLUMN_NOMBRE, nombre);
            values.put(TodoContract.SucursalesTab.COLUMN_CIUDAD, ciudad);
            values.put(TodoContract.SucursalesTab.COLUMN_PCODIGO, codigo);
            db.insert(TodoContract.SucursalesTab.TABLE_NAME, null, values);
        }
        cursor.close();
        db.close();
    }

    public void insertProdu(String nombre, Integer cantidad , Integer costo, String codigo){

        SQLiteDatabase db = this.getWritableDatabase();

        //Borrado por pruebas
        String query = "SELECT * FROM "+TodoContract.ProductosTab.TABLE_NAME+" WHERE Nombre = ?";
        Cursor cursor = db.rawQuery(query, new String[]{nombre});

        if(cursor.getCount() == 0) {
            ContentValues values = new ContentValues();
            values.put(TodoContract.ProductosTab.COLUMN_NOMBRE, nombre);
            values.put(TodoContract.ProductosTab.COLUMN_CANTIDAD, cantidad);
            values.put(TodoContract.ProductosTab.COLUMN_COSTO, costo);
            values.put(TodoContract.ProductosTab.COLUMN_CODIGO, codigo);
            db.insert(TodoContract.ProductosTab.TABLE_NAME, null, values);
        }
        cursor.close();
        db.close();
    }
}
