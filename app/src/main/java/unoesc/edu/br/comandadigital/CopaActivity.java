package unoesc.edu.br.comandadigital;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class CopaActivity extends Activity {

    private ListView lvProdutosCopa;
    private EditText pesquisacopa;
    private ImageButton btEnvia;
    private ImageButton btNaoEnvia;
    private ImageButton btAtualiza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copa);

        lvProdutosCopa = (ListView) findViewById(R.id.ltCopa);
        pesquisacopa = (EditText)findViewById(R.id.edtCopa);
        btEnvia = (ImageButton)findViewById(R.id.ibtConfirma);
        btNaoEnvia = (ImageButton)findViewById(R.id.ibtDesfaz);
        btAtualiza = (ImageButton)findViewById(R.id.btAtualizarCopa);


        // Operações de rede não podem ser realizadas na thread principal
        new MyAsyncTask().execute();


        //finaliza a aplicação
        final ImageButton btSair = (ImageButton) findViewById(R.id.btsairtela);
        btSair.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                finish();
            }
        });

        btEnvia = (ImageButton) findViewById(R.id.ibtConfirma);
        btEnvia.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String item =  pesquisacopa.getText().toString();
                new CopaTaskUpdate().execute(item);



            }
        });

        btNaoEnvia = (ImageButton) findViewById(R.id.ibtDesfaz);
        btNaoEnvia.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {



                String item =  pesquisacopa.getText().toString();
                new CopaTaskReturn().execute(item);


            }
        });

        btAtualiza = (ImageButton) findViewById(R.id.btAtualizarCopa);
        btAtualiza.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                new  MyAsyncTask().execute();


            }
        });

    }


    private class MyAsyncTask extends AsyncTask<Void, Void, ArrayList<ItensCopa>> {

        boolean erro = false;

        private final ProgressDialog dialog = new ProgressDialog(
                CopaActivity.this);

        @Override
        protected void onProgressUpdate(Void... values) {

            this.dialog.setMessage("Aguarde...");
            this.dialog.show();

        }

        @Override
        protected ArrayList<ItensCopa> doInBackground(Void... params) {

            ArrayList<ItensCopa> retorno = null;

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
        protected void onPostExecute(ArrayList<ItensCopa> result) {

            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }

            if (erro == false) {


                ArrayList<ItensCopa> listaProdutoCopa = result;
                ArrayAdapter<ItensCopa> adapter = new ArrayAdapter<ItensCopa>(
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
    private class CopaTaskUpdate extends AsyncTask<String, Void, Boolean> {


        boolean erro = false;

        private final ProgressDialog dialog = new ProgressDialog(
                CopaActivity.this);

        @Override
        protected Boolean doInBackground(String... params) {

           runOnUiThread(new Runnable() {
               @Override
               public void run() {
                   Toast.makeText(CopaActivity.this, "Bebida Enviada Para Mesa!!!", Toast.LENGTH_SHORT).show();
               }
           });


            try {

                ItensCopaDAO conexao = new ItensCopaDAO();
                conexao.enviaCopaMesa(Integer.parseInt(params[0]));



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

    private class CopaTaskReturn extends AsyncTask<String, Void, Boolean> {

        boolean erro = false;

        private final ProgressDialog dialog = new ProgressDialog(
                CopaActivity.this);

        @Override
        protected Boolean doInBackground(String... params) {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(CopaActivity.this, "Bebida Retonada da Mesa!!!", Toast.LENGTH_SHORT).show();
                }
            });
            try {

                ItensCopaDAO conexao = new ItensCopaDAO();
                conexao.retornaCopaMesa(Integer.parseInt(params[0]));



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

    @Override

    public void onConfigurationChanged(Configuration novaConfig){
        super.onConfigurationChanged(novaConfig);

    }

}
