package com.example.leonides.almacen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leonides.almacen.DB.LocalStorage;

public class detalles extends AppCompatActivity {

    String nombre,noserie,id;
    int stock;
    double precio;
    TextView nom,pre,nos,sto;

    Button agregar,quitar;
    EditText agre,quit;

    LocalStorage storage=new LocalStorage();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);
        nom =(TextView)findViewById(R.id.det_nombre);
        pre =(TextView)findViewById(R.id.det_precio);
        nos =(TextView)findViewById(R.id.det_noserie);
        sto =(TextView)findViewById(R.id.det_stock);
        agregar=(Button)findViewById(R.id.button_agregar);
        quitar=(Button)findViewById(R.id.button_quitar);
        agre=(EditText)findViewById(R.id.caj_agregar);
        quit=(EditText)findViewById(R.id.caja_quitar);

        obtenerDatos();
        actualizar();
    }

    private void actualizar() {
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(agre.getText().toString().isEmpty()){
                    Toast.makeText(detalles.this,"Ingrese la cantidad de Entrada",Toast.LENGTH_SHORT).show();
                }else {
                    storage.ActualizarStock(getApplicationContext(), id + "", Integer.parseInt(agre.getText().toString()), "+");

                    Intent nextViewIndex = new Intent(detalles.this, inventario.class);
                    startActivity(nextViewIndex);
                    onBackPressed();
                }
            }
        });

        quitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quit.getText().toString().isEmpty()) {
                    Toast.makeText(detalles.this, "Ingrese la cantidad de Salida", Toast.LENGTH_SHORT).show();
                }else {
                    storage.ActualizarStock(getApplicationContext(), id + "", Integer.parseInt(quit.getText().toString()), "-");

                    Intent nextViewIndex = new Intent(detalles.this, inventario.class);
                    startActivity(nextViewIndex);
                    onBackPressed();
                }
            }
        });
    }

    private void obtenerDatos() {
        id = getIntent().getExtras().getString("id");
        nombre = getIntent().getExtras().getString("nombre");
        precio = getIntent().getExtras().getDouble("precio");
        noserie = getIntent().getExtras().getString("noserie");
        stock = getIntent().getExtras().getInt("stock");

        setDatos();
    }

    private void setDatos() {
        nom.setText("Nombre: "+nombre);
        pre.setText("Precio: "+precio);
        nos.setText("NoSerie: "+noserie);
        sto.setText("Stock: "+stock);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub

        if (keyCode == event.KEYCODE_BACK) {
            Intent view = new Intent(detalles.this,inventario.class);
            startActivity(view);
            onBackPressed();
        }
        return super.onKeyDown(keyCode, event);
    }
}
