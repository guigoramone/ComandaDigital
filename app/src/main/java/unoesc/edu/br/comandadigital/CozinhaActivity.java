package unoesc.edu.br.comandadigital;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class CozinhaActivity extends Activity {


    private ListView lvProdutosCozinha;
    private EditText pesquisacozinha;
    private ImageButton btEnvia;
    private ImageButton btNaoEnvia;
    private ImageButton btAtualiza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cozinha);

        lvProdutosCozinha = (ListView) findViewById(R.id.ltCozinha);
        pesquisacozinha = (EditText)findViewById(R.id.edtitenscozinha);
        btEnvia = (ImageButton)findViewById(R.id.ibtaddcozinha);
        btNaoEnvia = (ImageButton)findViewById(R.id.ibtdeletecozinha);
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

        btEnvia = (ImageButton) findViewById(R.id.ibtaddcozinha);
        btEnvia.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String item =  pesquisacozinha.getText().toString();
                new CozinhaTaskUpdate().execute(item);



            }
        });

        btNaoEnvia = (ImageButton) findViewById(R.id.ibtdeletecozinha);
        btNaoEnvia.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String item =  pesquisacozinha.getText().toString();
                new CozinhaTaskReturn().execute(item);


            }
        });

        btAtualiza = (ImageButton) findViewById(R.id.btAtualizarCopa);
        btAtualiza.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


              new  MyAsyncTask().execute();


            }
        });

    }


    private class MyAsyncTask extends AsyncTask<Void, Void, ArrayList<ItensCozinha>> {

        boolean erro = false;

        private final ProgressDialog dialog = new ProgressDialog(
                CozinhaActivity.this);

        @Override
        protected void onProgressUpdate(Void... values) {

            this.dialog.setMessage("Aguarde...");
            this.dialog.show();

        }

        @Override
        protected ArrayList<ItensCozinha> doInBackground(Void... params) {

            ArrayList<ItensCozinha> retorno = null;

            try {

                ItensCozinhaDAO conexao = new ItensCozinhaDAO();
                retorno = conexao.listaProdutoCozinha();

            } catch (Exception e) {
                erro = true;
                e.printStackTrace();
            }

            return retorno;


        }

        @Override
        protected void onPostExecute(ArrayList<ItensCozinha> result) {

            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }

            if (erro == false) {


                ArrayList<ItensCozinha> listaProdutoCozinha = result;
                ArrayAdapter<ItensCozinha> adapter = new ArrayAdapter<ItensCozinha>(
                        CozinhaActivity.this, android.R.layout.simple_list_item_1,
                        listaProdutoCozinha);
                lvProdutosCozinha.setAdapter(adapter);

            } else {

                Toast.makeText(CozinhaActivity.this,
                        "Ocorreu um erro ao acessar o Web Service.",
                        Toast.LENGTH_LONG).show();

            }

        }

    }

    private class CozinhaTaskUpdate extends AsyncTask<String, Void, Boolean> {

        boolean erro = false;

        private final ProgressDialog dialog = new ProgressDialog(
                CozinhaActivity.this);

        @Override
        protected Boolean doInBackground(String... params) {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(CozinhaActivity.this, "Lanche Enviado Para Mesa!!!", Toast.LENGTH_SHORT).show();
                }
            });
            try {

                ItensCozinhaDAO conexao = new ItensCozinhaDAO();
                conexao.enviaCozinhaMesa(Integer.parseInt(params[0]));



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

    private class CozinhaTaskReturn extends AsyncTask<String, Void, Boolean> {

        boolean erro = false;

        private final ProgressDialog dialog = new ProgressDialog(
                CozinhaActivity.this);

        @Override
        protected Boolean doInBackground(String... params) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(CozinhaActivity.this, "Lanche Retonado da Mesa!!!", Toast.LENGTH_SHORT).show();
                }
            });
            try {

                ItensCozinhaDAO conexao = new ItensCozinhaDAO();
                conexao.retornaCozinhaMesa(Integer.parseInt(params[0]));



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
