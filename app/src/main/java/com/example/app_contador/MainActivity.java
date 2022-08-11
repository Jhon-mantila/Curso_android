package com.example.app_contador;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.ref.PhantomReference;

public class MainActivity extends Activity {

    public int contador;

    TextView textoResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textoResultado = (TextView) findViewById(R.id.ContadorTexto);

        contador = 0;

        textoResultado.setText(" "+ contador);

        /*utilizando eventos para ocultar teclado con el boton de ok del teclado*/
        EventoTelcado teclado = new EventoTelcado();

        EditText campo_reseto = (EditText) findViewById(R.id.n_reseteo);

        campo_reseto.setOnEditorActionListener(teclado);
    }

    //para la persistencia de datos con BUNDLE
   /* public void onSaveInstanceState(Bundle estado){

        estado.putInt("cuenta", contador);
        //pasarle al metodo el bandle, este llamado hace referencia al metodo de la clase padre no al metodo
        //acabado de hacer
        super.onSaveInstanceState(estado);
    }
    //recuperar los datos (persistencia de datos) bundle
    public void onRestoreInstanceState(Bundle estado){
        //lo mismo llamo al metodo de la clase padre no el q acabo de crear para recuperar los datos
        super.onRestoreInstanceState(estado);

        contador = estado.getInt("cuenta");

        textoResultado.setText(""+contador);

    }*/

    //para la persistencia de datos con SharedPreferences
    public void onPause(){

        super.onPause();

        //1. Crear u obtener objeto SharedPreferences
        SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(this);

        //2. Hacer editable el obj SharedPreference
        SharedPreferences.Editor mieditor = datos.edit();

        //3.Establecer información a almacenar
        mieditor.putInt("cuenta", contador);

        //4. Transferir la información a SharedPreference
        mieditor.apply();

    }

    //recuperar los datos (persistencia de datos) sharedPreferences
    public void onResume(){
        super.onResume();
        //debemos acceder al objecto almacenado en el sharedpreferences
        SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(this);
        //el segundo parametro hace referencia en caso no haber encontrado el valor
        contador = datos.getInt("cuenta",0);

        textoResultado.setText("" + contador);

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

        //OCULTANDO TECLADO con el boton resetear
        InputMethodManager miteclado = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        miteclado.hideSoftInputFromWindow(numero_reset.getWindowToken(), 0);
    }

   /* public void mostrarResultado(){
        //                                                Poder acceder al id de una vista
        TextView textoResultado = (TextView) findViewById(R.id.ContadorTexto);

        textoResultado.setText("" + contador);
    }*/

    private class EventoTelcado implements TextView.OnEditorActionListener{

        @Override
        public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
            //Si le damos boton ok en el teclado llama la function resetea_contador()
            if(actionId == EditorInfo.IME_ACTION_DONE){
                resetea_contador(null);
            }

            return false;
        }
    }

}