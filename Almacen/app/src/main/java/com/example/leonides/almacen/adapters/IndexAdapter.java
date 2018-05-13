package com.example.leonides.almacen.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;


import com.example.leonides.almacen.DB.LocalStorage;
import com.example.leonides.almacen.Pojos.Producto;
import com.example.leonides.almacen.R;
import com.example.leonides.almacen.detalles;

import java.util.ArrayList;


public class IndexAdapter extends ArrayAdapter<Producto>{

    private ArrayList<Producto> lista;
    private Context context;
    private int resource;
    private static final String TAG = "PRUEBA";
    public static ArrayList<Producto> pedido=new ArrayList<>();

    public IndexAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<Producto> objects) {
        super(context, resource,objects);

        this.lista=objects;
        this.context=context;
        this.resource=resource;
        this.pedido.clear();
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View v=null;

        final Producto item=lista.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v=inflater.inflate(resource,parent,false);
        Button stock=(Button)v.findViewById(R.id.desc);
        Button añadir =(Button)v.findViewById(R.id.añadir);

        TextView id =(TextView)v.findViewById(R.id.id);
        TextView nombre =(TextView)v.findViewById(R.id.nombre);
        TextView precio = (TextView)v.findViewById(R.id.precio);
        id.setText(item.getId());
        nombre.setText(item.getNombre());
        precio.setText(Double.toString(item.getPrecio()));

        añadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pedido.add(item);
                LocalStorage.pedidos=pedido;
            }
        });
        if(LocalStorage.estado){
            stock.setVisibility(View.INVISIBLE);
            añadir.setVisibility(View.VISIBLE);
        }else{
            stock.setVisibility(View.VISIBLE);
            añadir.setVisibility(View.INVISIBLE);
        }
        stock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ViewDescripcion=new Intent(context,detalles.class);
                Bundle datos=new Bundle();
                datos.putString("id",item.getId());
                Log.i("ID del intent:",item.getId()+"");
                datos.putString("nombre",item.getNombre());
                datos.putDouble("precio",item.getPrecio());
                datos.putString("noserie",item.getNoSerie());
                datos.putInt("stock",item.getStock());
                ViewDescripcion.putExtras(datos);
                ViewDescripcion.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(ViewDescripcion);
                }
        });

        return v;
    }
}