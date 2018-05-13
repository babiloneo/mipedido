package com.example.leonides.almacen.Pojos;

public class Producto {

    private String id;
    private String nombre;
    private String noSerie;
    private double precio;
    private int stock;
    private double totalP;

    public double getTotalP() {
        return totalP;
    }

    public void setTotalP(double totalP) {
        this.totalP = totalP;
    }

    public Producto(String id, String nombre, double precio, String noSerie)
    {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.noSerie = noSerie;
    }
    public Producto(){

    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }
    public void setPrecio(double precio)
    {
        this.precio = precio;
    }
    public void setStock(int cantidad)
    {
        stock = cantidad;
    }
    public String getNombre()
    {
        return nombre;
    }
    public String getId()
    {
        return id;
    }
    public String getNoSerie()
    {
        return noSerie;
    }
    public double getPrecio()
    {
        return precio;
    }
    public int getStock()
    {
        return stock;
    }
    public void incrementarStock(int cantidad)
    {
        stock = stock + cantidad;
    }
    public boolean disminuirStock(int cantidad)
    {
        if ((stock - cantidad) >= 0)
        {
            stock = stock - cantidad;
            return true;
        }
        return false;
    }

}
