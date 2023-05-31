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

public class ProductosMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos_main);

        TodoHelper dbHelper = new TodoHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                TodoContract.ProductosTab.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        List listProductos = new ArrayList<>();
        while (cursor.moveToNext()){
            String id = cursor.getString(cursor.getColumnIndexOrThrow(BaseColumns._ID));
            String nombre = cursor.getString(cursor.getColumnIndexOrThrow(TodoContract.ProductosTab.COLUMN_NOMBRE));
            String costo = cursor.getString(cursor.getColumnIndexOrThrow(TodoContract.ProductosTab.COLUMN_COSTO));
            String codigo = cursor.getString(cursor.getColumnIndexOrThrow(TodoContract.ProductosTab.COLUMN_CODIGO));
            String cantidad = cursor.getString(cursor.getColumnIndexOrThrow(TodoContract.ProductosTab.COLUMN_CANTIDAD));
            String producto = id+", "+nombre+", $"+costo+", "+codigo+", "+cantidad+"KG";
            listProductos.add(producto);
        }

        //Prueba
        cursor.close();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                listProductos
        );


        TodoHelper inserPro = new TodoHelper(getApplicationContext());

        inserPro.insertProdu("Tangelo",50,1500,"PF1");
        inserPro.insertProdu("Gulupa",20,1000,"PF2");
        inserPro.insertProdu("Rambutan",75,3500,"PF3");
        inserPro.insertProdu("Caju",45,1200,"PF4");
        inserPro.insertProdu("Mamoncillo",100,500,"PF5");

        ListView listPro = (ListView) findViewById(R.id.listaProductos);
        listPro.setAdapter(arrayAdapter);

    }

    public void Atras(View view){
        Intent intent = new Intent(this ,MainActivity.class);
        startActivity(intent);
    }

    public void producProve(View view){
        Intent intent = new Intent(this,producto_proveedor.class);
        startActivity(intent);
    }

    public void sucurProduc(View view){
        Intent intent = new Intent(this,sucursales_y_productos.class);
        startActivity(intent);
    }

}