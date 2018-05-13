package com.example.leonides.almacen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.leonides.almacen.DB.LocalStorage;
import com.example.leonides.almacen.Pojos.Producto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText id,nombre,precio,serie,stock;
    Button insert,almacen;
    LocalStorage storage=new LocalStorage();
    ArrayList<Producto> productos;
    private static final String TAG = "PRUEBA";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id=(EditText)findViewById(R.id.id);
        nombre=(EditText)findViewById(R.id.nombre);
        precio=(EditText)findViewById(R.id.precio);
        serie=(EditText)findViewById(R.id.serie);
        insert=(Button)findViewById(R.id.insert);
        stock=(EditText)findViewById(R.id.stock);
        productos=new ArrayList<Producto>();
        almacen=(Button)findViewById(R.id.almacen);
        Guardar();

    }

    private void Guardar() {
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id.getText().toString().isEmpty() || nombre.getText().toString().isEmpty() || precio.getText().toString().isEmpty() ||
                        serie.getText().toString().isEmpty() || stock.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,"Llene todos los campos",Toast.LENGTH_SHORT).show();
                }else{
                    //Almacen almacen = new Almacen();
                    Producto producto = new Producto(id.getText().toString(),nombre.getText().toString(),Double.valueOf(precio.getText().toString()).doubleValue(),serie.getText().toString());
                    producto.setStock(Integer.parseInt(stock.getText().toString()));
                    productos.add(producto);


                    boolean estado = storage.guardar(getApplicationContext(),productos,id.getText().toString());
                    productos.remove(productos.size()-1);
                    if(estado){
                        Toast.makeText(MainActivity.this,"Producto Guardado", Toast.LENGTH_SHORT).show();
                        limpiarCajas();
                    }else{
                        Toast.makeText(MainActivity.this,"La Id ya existe",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        almacen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(storage.consultar(getApplicationContext())!=null){
                    Intent nextViewIndex = new Intent(MainActivity.this,inventario.class);
                    startActivity(nextViewIndex);
                    LocalStorage.estado=false;
                }else{
                    Toast.makeText(MainActivity.this,"ALmacen vacio",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void limpiarCajas() {
        id.setText("");
        nombre.setText("");
        precio.setText("");
        serie.setText("");
        stock.setText("");
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub

        if (keyCode == event.KEYCODE_BACK) {
            Intent view = new Intent(MainActivity.this,menu.class);
            startActivity(view);
            onBackPressed();
        }
        return super.onKeyDown(keyCode, event);
    }
}
