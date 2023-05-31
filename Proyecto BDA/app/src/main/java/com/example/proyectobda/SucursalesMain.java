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

public class SucursalesMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sucursales_main);

        TodoHelper dbHelper = new TodoHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                TodoContract.SucursalesTab.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        List listSucursales = new ArrayList<>();
        while (cursor.moveToNext()){
            String id = cursor.getString(cursor.getColumnIndexOrThrow(BaseColumns._ID));
            String nombre = cursor.getString(cursor.getColumnIndexOrThrow(TodoContract.SucursalesTab.COLUMN_NOMBRE));
            String ciudad = cursor.getString(cursor.getColumnIndexOrThrow(TodoContract.SucursalesTab.COLUMN_CIUDAD));
            String codigoP = cursor.getString(cursor.getColumnIndexOrThrow(TodoContract.SucursalesTab.COLUMN_PCODIGO));
            String sucursal = id+", "+nombre+", "+ciudad+", "+ codigoP;
            listSucursales.add(sucursal);
        }

        //Prueba
        cursor.close();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                listSucursales
        );

        TodoHelper insertDb = new TodoHelper(getApplicationContext());
        insertDb.insertRe("Le fruit du Xalapa", "Xalapa", "PF1");
        insertDb.insertRe("Le fruit du Coatepec", "Coatepec", "PF2");
        insertDb.insertRe("Le fruit du Coatza", "Coatzacoalcos", "PF3");
        insertDb.insertRe("Le fruit du Perote", "Perote", "PF4");
        insertDb.insertRe("Le fruit du Xico", "Xico", "PF5");


        ListView listSuc = (ListView) findViewById(R.id.listaSucursales);
        listSuc.setAdapter(arrayAdapter);
    }

    public void Atras(View view){
        Intent intent = new Intent(this ,MainActivity.class);
        startActivity(intent);
    }
    public void SucurProduc(View view){
        Intent intent = new Intent(this,sucursales_y_productos.class);
        startActivity(intent);
    }
}