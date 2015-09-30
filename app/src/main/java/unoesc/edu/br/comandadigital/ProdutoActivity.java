package unoesc.edu.br.comandadigital;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.StrictMode;
import android.support.v4.app.ListFragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ProdutoActivity extends ActionBarActivity {

    private ListView lvProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        lvProdutos = (ListView) findViewById(R.id.ltProdutos);


        // Operações de rede não podem ser realizadas na thread principal
        new MyAsyncTask().execute();

    }


    private class MyAsyncTask extends AsyncTask<Void, Void, ArrayList<Produto>> {

        boolean erro = false;

        private final ProgressDialog dialog = new ProgressDialog(
                ProdutoActivity.this);

        @Override
        protected void onProgressUpdate(Void... values) {

            this.dialog.setMessage("Aguarde...");
            this.dialog.show();

        }

        @Override
        protected ArrayList<Produto> doInBackground(Void... params) {

            ArrayList<Produto> retorno = null;

            try {

                ProdutoDAO conexao = new ProdutoDAO();
                retorno = conexao.listaProduto();

            } catch (Exception e) {
                erro = true;
                e.printStackTrace();
            }

            return retorno;


        }

        @Override
        protected void onPostExecute(ArrayList<Produto> result) {

            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }

            if (erro == false) {

              
               ArrayList<Produto> listaProduto = result;
                ArrayAdapter<Produto> adapter = new ArrayAdapter<Produto>(
                        ProdutoActivity.this, android.R.layout.simple_list_item_1,
                        listaProduto);
               lvProdutos.setAdapter(adapter);

            } else {

                Toast.makeText(ProdutoActivity.this,
                        "Ocorreu um erro ao acessar o Web Service.",
                        Toast.LENGTH_LONG).show();

            }

        }

    }


}








