package com.example.leonides.almacen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leonides.almacen.DAO.DAOPedidos;
import com.example.leonides.almacen.DB.LocalStorage;
import com.example.leonides.almacen.Pojos.Producto;
import com.example.leonides.almacen.Pojos.pedido;
import com.example.leonides.almacen.adapters.pedidosAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;

public class estado extends AppCompatActivity {

    ListView listaEstado;
    pedidosAdapter adapter;
    TextView totalP,estado;
    Button cancelar,finalizar;
    String id;
    DAOPedidos dao=new DAOPedidos();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estado);
        listaEstado=(ListView)findViewById(R.id.listaPedidos);
        cancelar=(Button)findViewById(R.id.est_cancelar);
        finalizar=(Button)findViewById(R.id.est_finalizar);
        totalP = (TextView)findViewById(R.id.est_total);
        estado=(TextView)findViewById(R.id.est_estado);

        getPedidos();
        funciones();
    }

    private void funciones() {
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean estado=dao.pedidoAActualizar(id,getApplicationContext(),"Cancelado");
                if(estado){
                    Intent nextViewIndex = new Intent(estado.this, ListaPedidos.class);
                    startActivity(nextViewIndex);
                    onBackPressed();
                    LocalStorage.pedidos=null;
                }else{
                    Toast.makeText(estado.this,"No se puede Cancelar",Toast.LENGTH_SHORT).show();
                }

            }
        });
        finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean estado= dao.pedidoAActualizar(id,getApplicationContext(),"Finalizado");
                if(estado){
                    Intent nextViewIndex = new Intent(estado.this, ListaPedidos.class);
                    startActivity(nextViewIndex);
                    onBackPressed();
                    LocalStorage.pedidos=null;
                }else{
                    Toast.makeText(estado.this,"No se puede Finalizar",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void getPedidos() {

        id = getIntent().getExtras().getString("id");

        pedido pedidos=dao.ConsultarPedidoId(id,getApplicationContext());
        ArrayList<Producto> pedido=pedidos.getProductos();
        adapter=new pedidosAdapter(getApplicationContext(),R.layout.listview_pedidos,pedido);
        listaEstado.setAdapter(adapter);

        totalP.setText("Total: "+Double.toString(pedidos.getTotal()));
        estado.setText("Estado: "+pedidos.getEstado());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub

        if (keyCode == event.KEYCODE_BACK) {
            Intent view = new Intent(estado.this, ListaPedidos.class);
            startActivity(view);
            onBackPressed();
        }

        return super.onKeyDown(keyCode, event);
    }
}
