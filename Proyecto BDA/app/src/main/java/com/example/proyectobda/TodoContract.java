package com.example.proyectobda;

import android.provider.BaseColumns;

public class TodoContract {
    private TodoContract(){

    }

    public static class ProductosTab implements BaseColumns {
        public static final String TABLE_NAME = "Producto";
        public static final String COLUMN_NOMBRE = "Nombre";
        public static final String COLUMN_CANTIDAD = "Cantidad";
        public static final String COLUMN_COSTO = "Costo";
        public static final String COLUMN_CODIGO = "Codigo";
    }

    public static class SucursalesTab implements BaseColumns{
        public static final String TABLE_NAME = "Sucursal";
        public static final String COLUMN_NOMBRE = "Nombre";
        public static final String COLUMN_CIUDAD = "Ciudad";
        public static final String COLUMN_PCODIGO = "CodigoProducto";
    }

    public static class ProveedoresTab implements BaseColumns{
        public static final String TABLE_NAME = "Proveedor";
        public static final String COLUMN_NOMBRE = "Nombre";
        public static final String COLUMN_CIUDAD = "Ciudad";
        public static final String COLUMN_PCODIGO = "CodigoDeProducto";
    }

}
