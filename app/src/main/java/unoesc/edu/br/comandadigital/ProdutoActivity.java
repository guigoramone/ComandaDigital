package unoesc.edu.br.comandadigital;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class ProdutoActivity extends Activity {

    private ListView lvProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        lvProdutos = (ListView) findViewById(R.id.ltProdutos);


        // Operações de rede não podem ser realizadas na thread principal
        new ProdutoTask().execute();

    }


    private class ProdutoTask extends AsyncTask<Void, Void, ArrayList<Produto>> {

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








