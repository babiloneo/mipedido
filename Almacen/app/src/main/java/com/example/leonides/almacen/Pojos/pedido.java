package com.example.leonides.almacen.Pojos;

import java.util.ArrayList;

/**
 * Created by LEONIDES on 10/05/2018.
 */

public class pedido {
    private String id;
    private String tienda;
    private ArrayList<Producto> productos;
    private double Total;
    private String estado;

    public pedido(String id, String tienda, ArrayList<Producto> productos, double total, String estado) {
        this.id = id;
        this.tienda = tienda;
        this.productos = productos;
        Total = total;
        this.estado = estado;
    }

    public pedido(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTienda() {
        return tienda;
    }

    public void setTienda(String tienda) {
        this.tienda = tienda;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        Total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
