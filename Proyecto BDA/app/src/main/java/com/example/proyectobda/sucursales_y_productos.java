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

public class sucursales_y_productos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sucursales_yproductos);

        TodoHelper todoHelper = new TodoHelper(getApplicationContext());
        SQLiteDatabase db = todoHelper.getReadableDatabase();

        Cursor cursorS = db.query(
            TodoContract.SucursalesTab.TABLE_NAME,
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

        List listaSuPro = new ArrayList<>();

            while (cursorS.moveToNext()&&cursorP.moveToNext()) {
               String sucursal = cursorS.getString(cursorS.getColumnIndexOrThrow(TodoContract.SucursalesTab.COLUMN_NOMBRE));
               String producto = cursorP.getString(cursorP.getColumnIndexOrThrow(TodoContract.ProductosTab.COLUMN_NOMBRE));
               String sucPro = "Sucursal distribuidora: "+sucursal+ " Nombre del producto: "+producto;
               listaSuPro.add(sucPro);
        }

        cursorS.close();
        cursorP.close();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                listaSuPro
        );

        ListView listProSu = (ListView) findViewById(R.id.listaSucurProdu);
        listProSu.setAdapter(arrayAdapter);
    }

    public void Sucursal(View view ){
        Intent intent = new Intent(this, SucursalesMain.class);
        startActivity(intent);
    }

    public void Producto(View view){
        Intent intent = new Intent(this, ProductosMain.class);
        startActivity(intent);
    }
}