package unoesc.edu.br.comandadigital;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




   if(Build.VERSION.SDK_INT > 9){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
//chama a tela de comanda
        final ImageButton btComanda = (ImageButton)findViewById(R.id.btComanda);
        btComanda.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intencao = new Intent(MainActivity.this, ProdutoActivity.class);
                startActivity(intencao);
            }
        });

        //chama a tela de mesas
        final ImageButton btMesas = (ImageButton)findViewById(R.id.btMesa);
        btMesas.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intencao = new Intent(MainActivity.this, MesasActivity.class);
                startActivity(intencao);
            }
        });


        //chama a tela de Fatura
        final ImageButton btFatura = (ImageButton)findViewById(R.id.btFatura);
        btFatura.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intencao = new Intent(MainActivity.this, FaturaActivity.class);
                startActivity(intencao);
            }
        });


        //chama a tela de Cozinha
        final ImageButton btCozinha = (ImageButton)findViewById(R.id.btCozinha);
        btCozinha.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intencao = new Intent(MainActivity.this, CozinhaActivity.class);
                startActivity(intencao);
            }
        });

        //chama a tela de Copa
        final ImageButton btCopa = (ImageButton)findViewById(R.id.btcopa);
        btCopa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intencao = new Intent(MainActivity.this, CopaActivity.class);
                startActivity(intencao);
            }
        });
//finaliza a aplicação
      final ImageButton btSair = (ImageButton) findViewById(R.id.btSair);
        btSair.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                finish();
            }
        });




    }

}




