package com.example.app_contador;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    public int contador;

    TextView textoResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textoResultado = (TextView) findViewById(R.id.ContadorTexto);
        contador = 0;
    }

    public void  incrementa_contador(View vista){
        contador++;

        textoResultado.setText(""+contador);
        System.out.println(contador);
        //mostrarResultado();
    }

    public void resta_contador(View vista){
        contador--;

        if(contador<0){

            CheckBox negativos = (CheckBox) findViewById(R.id.negativos);

            if(!negativos.isChecked()){
                contador = 0;
            }

        }

        textoResultado.setText(""+contador);
        //mostrarResultado();
    }

    public void resetea_contador(View vista){
        //contador = 0;
        //mostrarResultado();
        EditText numero_reset = (EditText) findViewById(R.id.n_reseteo);

        try{
            contador = Integer.parseInt(numero_reset.getText().toString());

        }catch (Exception  e){
            contador = 0;
        }


        numero_reset.setText("");

        textoResultado.setText(""+contador);
    }

   /* public void mostrarResultado(){
        //                                                Poder acceder al id de una vista
        TextView textoResultado = (TextView) findViewById(R.id.ContadorTexto);

        textoResultado.setText("" + contador);
    }*/


}