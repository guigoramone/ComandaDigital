package unoesc.edu.br.comandadigital;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;


public class MesasActivity extends Activity {

    private ListView lvProdutosMesa;
    private EditText eText;
    private ImageButton btPesquisar;
    private ImageButton btFecharMesa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesas);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        //declara os objetos do main text;
        lvProdutosMesa = (ListView) findViewById(R.id.lvMesas);
        eText = (EditText) findViewById(R.id.edPesuisaMesaFechada);
        btPesquisar = (ImageButton) findViewById(R.id.btPesquisarMesaOcupada);
        btFecharMesa = (ImageButton) findViewById(R.id.btFechaMesa);


        //finaliza a aplicação
        final ImageButton btSair = (ImageButton) findViewById(R.id.btSairMesas);
        btSair.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                finish();
            }
        });

        final ImageButton btPesquisar = (ImageButton) findViewById(R.id.btPesquisarMesaOcupada);
        btPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String mesa =  eText.getText().toString();
                new MyAsyncTask().execute(mesa);



            }
        });

        //encerra a mesa para fatura posterior
        final ImageButton btFecharMesa = (ImageButton) findViewById(R.id.btFechaMesa);
        btFecharMesa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String mesa =  eText.getText().toString();
                new FechaMesaTask().execute(mesa);


            }
        });

    }
    private class MyAsyncTask extends AsyncTask<String, Void, List<ItensMesa>> {

        boolean erro = false;

        private final ProgressDialog dialog = new ProgressDialog(
                MesasActivity.this);

        @Override
        protected void onProgressUpdate(Void... values) {

            this.dialog.setMessage("Aguarde...");
            this.dialog.show();

        }

        @Override
        protected List<ItensMesa> doInBackground(String... params) {

            List<ItensMesa> retorno = null;

            try {

                ItensMesaDAO conexao = new ItensMesaDAO();
                retorno = conexao.listaMesasAbertas(Integer.parseInt(params[0]));

            } catch (Exception e) {
                erro = true;
                e.printStackTrace();
            }

            return retorno;


        }

        @Override
        protected void onPostExecute(List<ItensMesa> result) {

            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }

            if (erro == false) {


                List<ItensMesa> listaProduto = result;
                ArrayAdapter<ItensMesa> adapter = new ArrayAdapter<ItensMesa>(
                        MesasActivity.this, android.R.layout.simple_list_item_1,
                        listaProduto);
                lvProdutosMesa.setAdapter(adapter);

                Double total = 0.0;

               for (ItensMesa itensMesa : result) {

                 total +=  itensMesa.getValor();

            }

                Toast.makeText(MesasActivity.this,"total da mesa é R$: "+ total,
                        Toast.LENGTH_LONG).show();



            } else {

                Toast.makeText(MesasActivity.this,
                        "Ocorreu um erro ao acessar o Web Service.",
                        Toast.LENGTH_LONG).show();

            }

        }

    }

    private class FechaMesaTask extends AsyncTask<String, Void, Boolean> {

        boolean erro = false;

        private final ProgressDialog dialog = new ProgressDialog(
                MesasActivity.this);

        @Override
        protected Boolean doInBackground(String... params) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MesasActivity.this, "Mesa Fechada!!!", Toast.LENGTH_SHORT).show();
                }
            });
            try {

                ItensMesaDAO conexao = new ItensMesaDAO();
                conexao.fechaMesa(Integer.parseInt(params[0]));



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


