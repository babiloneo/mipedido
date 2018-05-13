package com.example.leonides.almacen.DB;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.leonides.almacen.Pojos.Producto;
import com.example.leonides.almacen.Pojos.pedido;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;


public class LocalStorage {

    private static final String PREFS_NAME = "ALUMNOSDB";
    public static ArrayList<Producto> pedidos;
    public static double total;
    public static boolean estado;
    public boolean guardar(Context context, ArrayList<Producto> datos,String id) {

        Log.i("ID a guradar",id);
        boolean estado=false;
        SharedPreferences settings =
                context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        if (exist(context,id)!=true) {
            SharedPreferences.Editor editor = settings.edit();

            String resgistros=vacio(context,datos);

            editor.putString("almacen", resgistros);
            Log.i("JSON: ",resgistros);
            editor.commit();
            estado=true;
        }
        return estado;
    }


    public ArrayList<Producto> consultar(Context context){
        GsonBuilder builder = new GsonBuilder();
        builder.serializeNulls();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

                SharedPreferences settings =
                        context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = settings.edit();
        String jsonAlmacen = settings.getString("almacen",null);
        ArrayList<Producto> copiarpoductos=null;
        if(jsonAlmacen!=null) {
            Producto[] arrayProductos = gson.fromJson(jsonAlmacen, Producto[].class);
            copiarpoductos = new ArrayList<>(Arrays.asList(arrayProductos));
        }

        return copiarpoductos;
    }

    public boolean ActualizarStock(Context context,String id,int stock,String accion){

        GsonBuilder builder = new GsonBuilder();
        builder.serializeNulls();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        SharedPreferences settings =
                context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);

        String jsonAlmacen = settings.getString("almacen",null);
        Producto[] arrayProductos=gson.fromJson(jsonAlmacen,Producto[].class);
        ArrayList<Producto> copiarpoductos=new ArrayList<>(Arrays.asList(arrayProductos));

        for(int x=0;x<copiarpoductos.size();x++){
            Producto p =copiarpoductos.get(x);
            Log.i("ID de array:",p.getId()+"ID a Buscar: "+id);

            if(p.getId().equals(id)){
                int valorActual=p.getStock();
                if(accion.equals("+")){
                    valorActual=valorActual+stock;
                }else{
                    valorActual=valorActual-stock;

                }
                p.setStock(valorActual);
            }

        }
        String jsonProductos =gson.toJson(copiarpoductos);
        SharedPreferences.Editor editor = settings.edit();

        editor.putString("almacen", jsonProductos);
        editor.commit();

        return true;
        }

        public boolean exist(Context context, String id){
            boolean estado=false;
            GsonBuilder builder = new GsonBuilder();
            builder.serializeNulls();
            builder.setPrettyPrinting();
            Gson gson = builder.create();

            SharedPreferences settings =
                    context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);

            String jsonAlmacen = settings.getString("almacen",null);
            if(jsonAlmacen!=null){
                Producto[] arrayProductos=gson.fromJson(jsonAlmacen,Producto[].class);
                ArrayList<Producto> copiarpoductos=new ArrayList<>(Arrays.asList(arrayProductos));

                for(int x=0;x< copiarpoductos.size();x++){
                    Producto p =copiarpoductos.get(x);
                    if(p.getId().equals(id)){
                        estado=true;
                    }
                }
            }
            return estado;
        }
    private String vacio(Context context ,ArrayList<Producto> datos) {
        GsonBuilder builder = new GsonBuilder();
        builder.serializeNulls();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        String jsonProductos=null;
        SharedPreferences settings =
                context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);

        String jsonAlmacen = settings.getString("almacen",null);
        if(jsonAlmacen!=null) {

            Producto[] arrayProductos = gson.fromJson(jsonAlmacen, Producto[].class);
            ArrayList<Producto> copiarpoductos = new ArrayList<>(Arrays.asList(arrayProductos));

            for (int x = 0; x < datos.size(); x++) {
                Producto p = datos.get(x);
                copiarpoductos.add(p);
            }
            jsonProductos = gson.toJson(copiarpoductos);
        }
        else{
            jsonProductos = gson.toJson(datos);

        }
        return jsonProductos;
    }
    public boolean GuardarPedido(pedido Pedido, Context context) {

        boolean estado=false;
        GsonBuilder builder = new GsonBuilder();
        builder.serializeNulls();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        SharedPreferences settings =
                context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);

        String jsonPedidos = settings.getString("pedidos",null);
        if(jsonPedidos!=null) {
            pedido[] arrayProductos = gson.fromJson(jsonPedidos, pedido[].class);
            ArrayList<pedido> ListaPedidos = new ArrayList<>(Arrays.asList(arrayProductos));
            ListaPedidos.add(Pedido);
            String pedidos = gson.toJson(ListaPedidos);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("pedidos", pedidos);
            editor.commit();
            Log.i("mis pedidos",pedidos);

        }
        else{
            ArrayList<pedido> ListaPedidos = new ArrayList<>();
            ListaPedidos.add(Pedido);
            String pedidos = gson.toJson(ListaPedidos);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("pedidos", pedidos);
            editor.commit();
        }
        return estado;
    }

    public String misPedidos(Context context){


        SharedPreferences settings =
                context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = settings.edit();
        String pedidos = settings.getString("pedidos",null);

        return pedidos;
    }

    public boolean actualizarPedido(String datosNuevos, Context context){

        SharedPreferences settings =
                context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("pedidos", datosNuevos);
        editor.commit();

        return estado;
    }
}