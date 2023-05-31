package com.example.proyectobda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class producto_proveedor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_proveedor);

        TodoHelper todoHelper = new TodoHelper(getApplicationContext());
        SQLiteDatabase db = todoHelper.getReadableDatabase();

        Cursor cursorS = db.query(
                TodoContract.ProveedoresTab.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        Cursor cursorP = db.query(
                TodoContract.ProductosTab.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        List listaprovePro = new ArrayList<>();

        while (cursorS.moveToNext()&&cursorP.moveToNext()) {
            String proveedor = cursorS.getString(cursorS.getColumnIndexOrThrow(TodoContract.ProveedoresTab.COLUMN_NOMBRE));
            String producto = cursorP.getString(cursorP.getColumnIndexOrThrow(TodoContract.ProductosTab.COLUMN_NOMBRE));
            String provePro = "Proveedor internacional : "+proveedor+ " Nombre del producto: "+producto;
            listaprovePro.add(provePro);
        }

        cursorS.close();
        cursorP.close();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                listaprovePro
        );

        ListView listProSu = (ListView) findViewById(R.id.listaproduProve);
        listProSu.setAdapter(arrayAdapter);
    }

    public void Productos (View view){
        Intent intent = new Intent(this,ProductosMain.class);
        startActivity(intent);
    }

    public void Proveedores (View view){
        Intent intent = new Intent(this,ProveedoresMain.class);
        startActivity(intent);
    }
}