package com.example.leonides.almacen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leonides.almacen.DAO.DAOPedidos;
import com.example.leonides.almacen.DB.LocalStorage;
import com.example.leonides.almacen.Pojos.Producto;
import com.example.leonides.almacen.Pojos.pedido;
import com.example.leonides.almacen.adapters.IndexAdapter;
import com.example.leonides.almacen.adapters.ListaPedidosAdapter;

import java.util.ArrayList;

public class inventario extends AppCompatActivity {
    ListView Listaalmacen;
    LocalStorage storage=new LocalStorage();
    IndexAdapter adapter;
    Button pedido,consultar;
    TextView titulo,texto,texto2;
    EditText caja,caja2;
    ArrayList<Producto> indexResult;
    LinearLayout botones;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario);
        Listaalmacen=(ListView)findViewById(R.id.alma);
        pedido=(Button)findViewById(R.id.pedido);
        titulo=(TextView)findViewById(R.id.titulo);
        texto=(TextView)findViewById(R.id.texto);
        texto2=(TextView)findViewById(R.id.texto2);
        caja=(EditText)findViewById(R.id.caja);
        caja2=(EditText)findViewById(R.id.caja2);
        botones=(LinearLayout)findViewById(R.id.botones);
        consultar=(Button)findViewById(R.id.consultar);
        getAlmacen();
        pedidos();

    }

    private void pedidos() {
        pedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idAux=null;
                ArrayList<Producto> pedidos=LocalStorage.pedidos;
                double totalT=0;
                String texto=caja.getText().toString();
                String texto2=caja2.getText().toString();
                if(pedidos!=null && texto.isEmpty()!=true && texto2.isEmpty()!=true) {
                    ArrayList<Producto> aux = new ArrayList<>();
                    for (int x = 0; x < pedidos.size(); x++) {
                        Producto p = pedidos.get(x);
                        int contador = 0;
                        double total=0.0;
                        aux.add(p);
                        for (int y = 0; y < pedidos.size(); y++) {
                            Producto q = pedidos.get(y);
                            if (p.getId().equals(q.getId())) {
                                contador++;

                                pedidos.remove(y);
                                y = y - 1;
                            }
                        }
                        p.setStock(contador);
                        p.setTotalP(p.getPrecio()*contador);
                        totalT=totalT+p.getTotalP();
                    }

                    LocalStorage.pedidos = aux;
                    pedido nuevo =new pedido(caja2.getText().toString(),caja.getText().toString(),aux,totalT,"En proceso");
                    DAOPedidos p =new DAOPedidos();
                    boolean estado=p.nuevoPdido(nuevo,getApplicationContext());
                    if(estado){
                        Intent view = new Intent(inventario.this, estado.class);
                        Bundle datos=new Bundle();
                        datos.putString("id",caja2.getText().toString());
                        view.putExtras(datos);
                        view.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(view);
                        limpiar();
                    }else{
                        Toast.makeText(inventario.this,"La id ya existe",Toast.LENGTH_SHORT).show();
                        limpiar();
                    }

                }else{
                    Toast.makeText(inventario.this,"Llene todos los campos",Toast.LENGTH_SHORT).show();
                }
            }
        });

        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DAOPedidos dao= new DAOPedidos();
                ArrayList<pedido> listPedidos= dao.consultaPedidos(getApplicationContext());
                if(listPedidos!=null){
                    Intent view = new Intent(inventario.this, ListaPedidos.class);
                    startActivity(view);
                }else{
                    Toast.makeText(inventario.this,"No hay pedidos",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void limpiar() {
        LocalStorage.pedidos = null;
        caja.setText("");
        caja2.setText("");
    }

    public void getAlmacen(){

        if(LocalStorage.estado){
            texto.setVisibility(View.VISIBLE);
            caja.setVisibility(View.VISIBLE);
            texto2.setVisibility(View.VISIBLE);
            caja2.setVisibility(View.VISIBLE);
            titulo.setVisibility(View.INVISIBLE);
            botones.setVisibility(View.VISIBLE);
        }else{
            texto.setVisibility(View.INVISIBLE);
            caja.setVisibility(View.INVISIBLE);
            texto2.setVisibility(View.INVISIBLE);
            caja2.setVisibility(View.INVISIBLE);
            titulo.setVisibility(View.VISIBLE);
            botones.setVisibility(View.INVISIBLE);
        }

        indexResult=storage.consultar(getApplicationContext());
        adapter=new IndexAdapter(getApplicationContext(),R.layout.listview_index,indexResult);
        Listaalmacen.setAdapter(adapter);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub

        if (keyCode == event.KEYCODE_BACK) {
            if(LocalStorage.estado!=true){
                Intent view = new Intent(inventario.this,MainActivity.class);
                startActivity(view);
                onBackPressed();
            }else{
                Intent view = new Intent(inventario.this,menu.class);
                startActivity(view);
                onBackPressed();
            }

        }
        return super.onKeyDown(keyCode, event);
    }
}
