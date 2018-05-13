package com.example.leonides.almacen.DAO;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.leonides.almacen.DB.LocalStorage;
import com.example.leonides.almacen.ListaPedidos;
import com.example.leonides.almacen.Pojos.Producto;
import com.example.leonides.almacen.Pojos.pedido;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;

public class DAOPedidos {
    LocalStorage storage =new LocalStorage();

    public boolean nuevoPdido(pedido Npedido, Context context){
        boolean estado=false;
        pedido p =ConsultarPedidoId(Npedido.getId(),context);
        if(p==null){
            storage.GuardarPedido(Npedido,context);
            estado=true;
        }else{
            estado=false;
        }

        return estado;
    }

    public ArrayList<pedido> consultaPedidos(Context context){
        GsonBuilder builder = new GsonBuilder();
        builder.serializeNulls();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        String pedidos=storage.misPedidos(context);
        ArrayList<pedido> ListaPedidos=null;

        if(pedidos!=null) {
            pedido[] arrayPedidos = gson.fromJson(pedidos, pedido[].class);
            ListaPedidos = new ArrayList<>(Arrays.asList(arrayPedidos));

        }
        return ListaPedidos;
    }

    public pedido ConsultarPedidoId(String id, Context context){

        pedido encontradop=null;
        ArrayList<pedido> pedidos=consultaPedidos(context);
        if(pedidos!=null){
            for(int x=0;x<pedidos.size();x++){
                pedido p = pedidos.get(x);
                if(p.getId().equals(id)){
                    encontradop=new pedido(p.getId(),p.getTienda(),p.getProductos(),p.getTotal(),p.getEstado());
                }
            }
        }

        return encontradop;
    }

    public boolean pedidoAActualizar(String id, Context context,String estado){
        String datos=storage.misPedidos(context);
        boolean result=false;
        GsonBuilder builder = new GsonBuilder();
        builder.serializeNulls();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        pedido[] arrayPedidos=gson.fromJson(datos,pedido[].class);
        ArrayList<pedido> copiarpedidos=new ArrayList<>(Arrays.asList(arrayPedidos));

        for(int x=0;x<copiarpedidos.size();x++){
            pedido p =copiarpedidos.get(x);

            if(p.getId().equals(id)){
                if(p.getEstado().equals("En proceso")){
                    p.setEstado(estado);
                    result=true;
                }else{
                    result=false;
                }
            }
        }
        String actualizado =gson.toJson(copiarpedidos);

        storage.actualizarPedido(actualizado,context);

        return result;
    }
}
