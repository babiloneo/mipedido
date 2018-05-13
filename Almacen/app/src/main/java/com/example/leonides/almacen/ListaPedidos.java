package com.example.leonides.almacen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ListView;
import android.widget.Toast;

import com.example.leonides.almacen.DAO.DAOPedidos;
import com.example.leonides.almacen.Pojos.pedido;
import com.example.leonides.almacen.adapters.IndexAdapter;
import com.example.leonides.almacen.adapters.ListaPedidosAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListaPedidos extends AppCompatActivity {

    ListView listaPedidos;
    ListaPedidosAdapter adapter;
    ArrayList<pedido> listPedidos;
    DAOPedidos dao=new DAOPedidos();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pedidos);
        listaPedidos=(ListView)findViewById(R.id.listaPedidos);
        getListaDePedidos();
    }

    public void getListaDePedidos() {
        listPedidos= dao.consultaPedidos(getApplicationContext());
        adapter=new ListaPedidosAdapter(getApplicationContext(),R.layout.lisview_pedidos,listPedidos);
        listaPedidos.setAdapter(adapter);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub

        if (keyCode == event.KEYCODE_BACK) {
            Intent view = new Intent(ListaPedidos.this, inventario.class);
            startActivity(view);
            onBackPressed();
        }
        return super.onKeyDown(keyCode, event);
    }
}
