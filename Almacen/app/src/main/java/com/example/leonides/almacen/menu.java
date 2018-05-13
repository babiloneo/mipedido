package com.example.leonides.almacen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;

import com.example.leonides.almacen.DB.LocalStorage;

public class menu extends AppCompatActivity {

    ImageButton almacen, pedidos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        almacen =(ImageButton)findViewById(R.id.men_almacen);
        pedidos =(ImageButton)findViewById(R.id.men_pedido);
        MiMenu();
    }

    private void MiMenu() {
        almacen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent view = new Intent(menu.this, MainActivity.class);
                startActivity(view);
            }
        });

        pedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocalStorage.estado=true;
                Intent view = new Intent(menu.this,inventario.class);
                startActivity(view);

            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub

        if (keyCode == event.KEYCODE_BACK) {
            onBackPressed();
        }
        return super.onKeyDown(keyCode, event);
    }
}
