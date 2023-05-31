package com.example.proyectobda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ProveedoresMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proveedores_main);

        TodoHelper dbHelper = new TodoHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                TodoContract.ProveedoresTab.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        List listProveedores = new ArrayList<>();
        while (cursor.moveToNext()){
            String id = cursor.getString(cursor.getColumnIndexOrThrow(BaseColumns._ID));
            String nombre = cursor.getString(cursor.getColumnIndexOrThrow(TodoContract.ProveedoresTab.COLUMN_NOMBRE));
            String ciudad = cursor.getString(cursor.getColumnIndexOrThrow(TodoContract.ProveedoresTab.COLUMN_CIUDAD));
            String codigoP = cursor.getString(cursor.getColumnIndexOrThrow(TodoContract.ProveedoresTab.COLUMN_PCODIGO));
            String proveedor = id+", "+nombre+", "+ciudad+", "+codigoP;
            listProveedores.add(proveedor);
        }

        //Prueba
        cursor.close();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                listProveedores
        );

        TodoHelper inserHelper = new TodoHelper(getApplicationContext());
        inserHelper.insertProve("Montes peruanos", "Peru","PF1");
        inserHelper.insertProve("Praderas colombianas", "Colombia","PF2");
        inserHelper.insertProve("Lago chino", "China","PF3");
        inserHelper.insertProve("Sopa brasileira", "Brasil","PF4");
        inserHelper.insertProve("Olores argentinos", "Argentina","PF5");

        ListView listProve = (ListView) findViewById(R.id.listaProveedores);
        listProve.setAdapter(arrayAdapter);
    }

    public void Atras(View view){
        Intent intent = new Intent(this ,MainActivity.class);
        startActivity(intent);
    }

    public void producProve(View view){
        Intent intent = new Intent(this,producto_proveedor.class);
        startActivity(intent);
    }
}