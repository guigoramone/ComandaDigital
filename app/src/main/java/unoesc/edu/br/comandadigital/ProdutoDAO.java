package unoesc.edu.br.comandadigital;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpResponseException;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

/**
 * Created by root on 20/09/15.
 */
public class ProdutoDAO {

    private static final String URL = "http://192.168.43.8:8080/comandadigitalwebservices/services/ProdutoDAO?wsdl";
    private static final String NAMESPACE = "http://unoesc.com.br.edu";
    //casa: 192.168.1.24
    //unoesc 172.18.19.119

    private static final String METHOD_NAME = "listarTudo";
    private static final String SOAP_ACTION = "http://unoesc.com.br.edu/listarTudo";


    public ArrayList<Produto> listaProduto() throws Exception {


        SoapObject request = new SoapObject(NAMESPACE,METHOD_NAME);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.implicitTypes = true;
        envelope.dotNet=true;
        envelope.setOutputSoapObject(request);
        
        HttpTransportSE http = new HttpTransportSE(URL);
        http.call(SOAP_ACTION, envelope);

        SoapObject response = (SoapObject) envelope.bodyIn;

        ArrayList<Produto> produtos = new ArrayList<>();

        for (int i = 0; i < response.getPropertyCount(); i ++){

            SoapObject soapObject = (SoapObject) response.getProperty(i);

            Produto p = new Produto();
            p.setCodi(Integer.parseInt(soapObject.getProperty("codi").toString()));
            p.setDescricao(soapObject.getProperty("descricao").toString());
            p.setPreco(Double.parseDouble(soapObject.getProperty("preco").toString()));

            produtos.add(p);
        }
        return produtos;
    }



}
