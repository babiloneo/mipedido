package com.example.leonides.almacen;

import com.example.leonides.almacen.Pojos.Producto;

import java.util.HashMap;

public class Almacen {
    private HashMap<String, Producto> productos = new HashMap<>();

    public Almacen()
    {

    }

    public boolean agregarProducto(Producto producto)
    {
        if (!productos.containsKey(producto.getId()))
        {
            productos.put(producto.getId(), producto);
            return true;
        }
        return false;
    }
    public void entrada(String idProducto, int cantidad)
    {
        if (productos.containsKey(idProducto))
        {
            productos.get(idProducto).incrementarStock(cantidad);
        }
    }
    public boolean salida(String idProducto, int cantidad)
    {
        if (productos.containsKey(idProducto))
        {
            return productos.get(idProducto).disminuirStock(cantidad);
        }
        return false;
    }
}


