package unoesc.edu.br.comandadigital;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

/**
 * Created by root on 04/10/15.
 */
public class ItensCopaDAO {

    private static final String URL = "http://192.168.1.24:8080/comandadigitalwebservices/services/ItensDAO?wsdl";
    private static final String NAMESPACE = "http://unoesc.com.br.edu";
    //casa: 192.168.1.24
    //unoesc 172.18.19.119

    private static final String METHOD_NAME = "listaProdCopa";
    private static final String SOAP_ACTION = "http://unoesc.com.br.edu/listaProdCozinha";


    public ArrayList<Itens> listaProdutoCopa() throws Exception {


        SoapObject request = new SoapObject(NAMESPACE,METHOD_NAME);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.implicitTypes = true;
        envelope.dotNet=true;
        envelope.setOutputSoapObject(request);

        HttpTransportSE http = new HttpTransportSE(URL);
        http.call(SOAP_ACTION, envelope);

        SoapObject response = (SoapObject) envelope.bodyIn;

        ArrayList<Itens> itens = new ArrayList<Itens>();

        for (int i = 0; i < response.getPropertyCount(); i ++){

            SoapObject soapObject = (SoapObject) response.getProperty(i);

            Itens itn = new Itens();
            itn.setId(Integer.parseInt(soapObject.getProperty("id").toString()));
            itn.setNume_mesa(Integer.parseInt(soapObject.getProperty("nume_mesa").toString()));
            itn.setProduto(soapObject.getProperty("produto").toString());
            itn.setValor(Double.parseDouble(soapObject.getProperty("valor").toString()));
           itn.setCategoria(Integer.parseInt(soapObject.getProperty("categoria").toString()));
            itn.setEnviado(soapObject.getProperty("enviado").toString());
            itn.setMesa_status(soapObject.getProperty("mesa_status").toString());


            itens.add(itn);
        }
        return itens;
    }
}
