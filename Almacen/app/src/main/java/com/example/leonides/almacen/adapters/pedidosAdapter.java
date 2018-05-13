package com.example.leonides.almacen.adapters;


import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.leonides.almacen.Pojos.Producto;
import com.example.leonides.almacen.R;

import java.util.ArrayList;


public class pedidosAdapter extends ArrayAdapter<Producto>{

    private ArrayList<Producto> lista;
    private Context context;
    private int resource;
    private static final String TAG = "PRUEBA";

    public pedidosAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<Producto> objects) {
        super(context, resource,objects);

        this.lista=objects;
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View v=null;

        final Producto item=lista.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v=inflater.inflate(resource,parent,false);
        //Button stock=(Button)v.findViewById(R.id.desc);
        //Button añadir =(Button)v.findViewById(R.id.añadir);

        TextView nombre =(TextView)v.findViewById(R.id.ped_nombre);
        TextView cantidad = (TextView)v.findViewById(R.id.ped_cantidad);
        TextView precio = (TextView)v.findViewById(R.id.ped_precio);
        TextView total = (TextView)v.findViewById(R.id.ped_total);

        nombre.setText(item.getNombre());
        cantidad.setText(Integer.toString(item.getStock()));
        precio.setText(Double.toString(item.getPrecio()));
        total.setText(Double.toString(item.getTotalP()));


        return v;
    }
}