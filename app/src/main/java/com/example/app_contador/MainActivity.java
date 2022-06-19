package com.example.app_contador;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    public int contador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contador = 0;
    }

    public void  incrementa_contador(View vista){
        contador++;
        System.out.println(contador);
        mostrarResultado();
    }

    public void resta_contador(View vista){
        contador--;
        mostrarResultado();
    }

    public void resetea_contador(View vista){
        contador = 0;
        mostrarResultado();
    }

    public void mostrarResultado(){
        //                                                Poder acceder al id de una vista
        TextView textoResultado = (TextView) findViewById(R.id.contador_pulsasiones);

        textoResultado.setText("Contador: " + contador);
    }
}