package com.example.proyectobda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Sucursal(View view){
        Intent intent = new Intent(this, SucursalesMain.class);
        startActivity(intent);
    }

    public void Proveedor(View view){
        Intent intent = new Intent(this, ProveedoresMain.class);
        startActivity(intent);
    }

    public void Producto(View view){
        Intent intent = new Intent(this,ProductosMain.class);
        startActivity(intent);
    }
}