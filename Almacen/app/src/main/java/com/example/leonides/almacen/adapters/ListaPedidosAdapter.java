package com.example.leonides.almacen.adapters;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.leonides.almacen.Pojos.Producto;
import com.example.leonides.almacen.Pojos.pedido;
import com.example.leonides.almacen.R;
import com.example.leonides.almacen.estado;
import com.example.leonides.almacen.inventario;

import java.util.ArrayList;


public class ListaPedidosAdapter extends ArrayAdapter<pedido>{

    private ArrayList<pedido> lista;
    private Context context;
    private int resource;
    private static final String TAG = "PRUEBA";

    public ListaPedidosAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<pedido> objects) {
        super(context, resource,objects);

        this.lista=objects;
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View v=null;

        final pedido item=lista.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v=inflater.inflate(resource,parent,false);

        TextView tienda =(TextView)v.findViewById(R.id.lis_tienda);
        TextView estado = (TextView)v.findViewById(R.id.lis_estado);
        TextView id = (TextView)v.findViewById(R.id.lis_id);
        Button añadir=(Button)v.findViewById(R.id.añadir);

        tienda.setText(item.getTienda());
        estado.setText((item.getEstado()));
        id.setText(item.getId());

        añadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent view = new Intent(context, com.example.leonides.almacen.estado.class);
                Bundle datos=new Bundle();
                datos.putString("id",item.getId());
                view.putExtras(datos);
                view.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(view);
            }
        });

        return v;
    }
}