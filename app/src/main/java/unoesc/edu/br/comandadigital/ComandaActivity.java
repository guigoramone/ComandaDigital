package unoesc.edu.br.comandadigital;

import android.app.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class ComandaActivity extends Activity {

    private ListView lvProdutos;
    private ListView lvComanda;
    private ImageButton ibtaddproduto;
    private ImageButton ibtdeletaproduto;
    private ImageButton ibtsairtela;
    private EditText etmesa;
    private EditText etproduto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comanda);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        lvProdutos = (ListView) findViewById(R.id.ltprodcomanda);
        lvComanda = (ListView) findViewById(R.id.ltcomanada);
        ibtaddproduto = (ImageButton) findViewById(R.id.ibtadditem);
        ibtdeletaproduto = (ImageButton) findViewById(R.id.ibtdeletaitem);
        ibtsairtela = (ImageButton) findViewById(R.id.btsairtela);

        // Operações de rede não podem ser realizadas na thread principal

        new  ProdutoTask().execute();


       ibtsairtela = (ImageButton) findViewById(R.id.ibtsairComanda);
        ibtsairtela.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                finish();
            }
        });

        ibtaddproduto = (ImageButton) findViewById(R.id.ibtadditem);
        ibtaddproduto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                finish();
            }
        });

        ibtdeletaproduto = (ImageButton) findViewById(R.id.ibtdeletaitem);
        ibtdeletaproduto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                finish();
            }
        });




                //chamar metodo clear limpar a listview;

                /* public void clearItens() {
        // clear the data
        itens.clear();
    }*/





    }


    private class ProdutoTask extends AsyncTask<Void, Void, ArrayList<Produto>> {

        boolean erro = false;

        private final ProgressDialog dialog = new ProgressDialog(
                ComandaActivity.this);

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
                ArrayAdapter<Produto> adapter = new ArrayAdapter<>(
                        ComandaActivity.this, android.R.layout.simple_list_item_1,
                        listaProduto);
                lvProdutos.setAdapter(adapter);

            } else {

                Toast.makeText(ComandaActivity.this,
                        "Ocorreu um erro ao acessar o Web Service.",
                        Toast.LENGTH_LONG).show();

            }

        }

    }

    private class MesaTask extends AsyncTask<String, Void, Boolean> {

        boolean erro = false;

        private final ProgressDialog dialog = new ProgressDialog(
                ComandaActivity.this);

        @Override
        protected Boolean doInBackground(String... params) {

            try {

                ComandaDAO conexao = new ComandaDAO();
                conexao.abreMesa(Integer.parseInt(params[0]));



            } catch (Exception e) {
                erro = true;
                e.printStackTrace();
            }

            return true;
        }

        @Override
        protected void onProgressUpdate(Void... values) {

            this.dialog.setMessage("Aguarde...");
            this.dialog.show();

        }

    }


    }

