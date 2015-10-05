package unoesc.edu.br.comandadigital;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class CopaActivity extends Activity {

    private ListView lvProdutosCopa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copa);

        lvProdutosCopa = (ListView) findViewById(R.id.ltCopa);


        // Operações de rede não podem ser realizadas na thread principal
        new MyAsyncTask().execute();


        //finaliza a aplicação
        final ImageButton btSair = (ImageButton) findViewById(R.id.btsairtela);
        btSair.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                finish();
            }
        });

    }


    private class MyAsyncTask extends AsyncTask<Void, Void, ArrayList<Itens>> {

        boolean erro = false;

        private final ProgressDialog dialog = new ProgressDialog(
                CopaActivity.this);

        @Override
        protected void onProgressUpdate(Void... values) {

            this.dialog.setMessage("Aguarde...");
            this.dialog.show();

        }

        @Override
        protected ArrayList<Itens> doInBackground(Void... params) {

            ArrayList<Itens> retorno = null;

            try {

                ItensCopaDAO conexao = new ItensCopaDAO();
                retorno = conexao.listaProdutoCopa();

            } catch (Exception e) {
                erro = true;
                e.printStackTrace();
            }

            return retorno;


        }

        @Override
        protected void onPostExecute(ArrayList<Itens> result) {

            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }

            if (erro == false) {


                ArrayList<Itens> listaProdutoCopa = result;
                ArrayAdapter<Itens> adapter = new ArrayAdapter<Itens>(
                        CopaActivity.this, android.R.layout.simple_list_item_1,
                       listaProdutoCopa);
                lvProdutosCopa.setAdapter(adapter);

            } else {

                Toast.makeText(CopaActivity.this,
                        "Ocorreu um erro ao acessar o Web Service.",
                        Toast.LENGTH_LONG).show();

            }

        }






    }


}
