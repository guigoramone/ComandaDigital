package unoesc.edu.br.comandadigital;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;


public class FaturaActivity extends Activity {


    private ListView lvMesa;
    private EditText eText;
    private ImageButton btPesquisar;
    private ImageButton btEncerraMesa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fatura);


        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        lvMesa = (ListView) findViewById(R.id.ltMesas);
        eText = (EditText) findViewById(R.id.edPesuisaMesaFechada);
        btPesquisar = (ImageButton) findViewById(R.id.btPesquisarMesaFechada);
        btEncerraMesa = (ImageButton) findViewById(R.id.btFatura);


        //finaliza a aplicação
        final ImageButton btSair = (ImageButton) findViewById(R.id.btSairMesas);
        btSair.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                finish();
            }
        });


        btPesquisar = (ImageButton) findViewById(R.id.btPesquisarMesaFechada);
        btPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String mesa =  eText.getText().toString();
                new MyAsyncTask().execute(mesa);



            }
        });

        btEncerraMesa = (ImageButton) findViewById(R.id.btFatura);
        btEncerraMesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String mesa =  eText.getText().toString();
                new EncerraMesaTask().execute(mesa);



            }
        });
    }
    private class MyAsyncTask extends AsyncTask<String, Void, List<ItensFatura>> {

        boolean erro = false;

        private final ProgressDialog dialog = new ProgressDialog(
                FaturaActivity.this);

        @Override
        protected void onProgressUpdate(Void... values) {

            this.dialog.setMessage("Aguarde...");
            this.dialog.show();

        }

        @Override
        protected List<ItensFatura> doInBackground(String... params) {

            List<ItensFatura> retorno = null;

            try {

                ItensFaturaDAO conexao = new ItensFaturaDAO();
                retorno = conexao.listaMesasEncerradas(Integer.parseInt(params[0]));

            } catch (Exception e) {
                erro = true;
                e.printStackTrace();
            }

            return retorno;


        }

        @Override
        protected void onPostExecute(List<ItensFatura> result) {

            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }

            if (erro == false) {


                List<ItensFatura> listaProduto = result;
                ArrayAdapter<ItensFatura> adapter = new ArrayAdapter<ItensFatura>(
                        FaturaActivity.this, android.R.layout.simple_list_item_1,
                        listaProduto);
                lvMesa.setAdapter(adapter);

                Double total = 0.0;

                for (ItensFatura itensFatura : result) {

                    total +=  itensFatura.getValor();

                }

                Toast.makeText(FaturaActivity.this, "total da mesa é R$: " + total,
                        Toast.LENGTH_LONG).show();



            } else {

                Toast.makeText(FaturaActivity.this,
                        "Ocorreu um erro ao acessar o Web Service.",
                        Toast.LENGTH_LONG).show();

            }

        }

    }

    private class EncerraMesaTask extends AsyncTask<String, Void, Boolean> {

        boolean erro = false;

        private final ProgressDialog dialog = new ProgressDialog(
                FaturaActivity.this);

        @Override
        protected Boolean doInBackground(String... params) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(FaturaActivity.this, "Mesa Encerrada!!!", Toast.LENGTH_SHORT).show();
                }
            });
            try {

                ItensFaturaDAO conexao = new ItensFaturaDAO();
                conexao.encerraMesa(Integer.parseInt(params[0]));



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
