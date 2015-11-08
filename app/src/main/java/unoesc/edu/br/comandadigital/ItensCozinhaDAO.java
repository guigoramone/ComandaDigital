package unoesc.edu.br.comandadigital;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

/**
 * Created by root on 04/10/15.
 */
public class ItensCozinhaDAO {

    private static final String URL = "http://192.168.43.8:8080/comandadigitalwebservices/services/ItensDAO?wsdl";
    private static final String NAMESPACE = "http://unoesc.com.br.edu";
    //casa: 192.168.1.24
    //unoesc 172.18.19.119

    private static final String LISTA_TUDO = "listarCozinha";
    private static final String SOAP_ACTION = "http://unoesc.com.br.edu/listarCozinha";
    private static final String ENVIA_COZINHA_MESA = "enviaCozinhaMesa";
    private static final String RETORNA_COZINHA_MESA = "retornaCozinhaMesa";
    private static final String SOAP_ACTION2 = "http://unoesc.com.br.edu/enviaCozinhaMesa";
    private static final String SOAP_ACTION3 = "http://unoesc.com.br.edu/retornaCozinhaMesa";


    public ArrayList<ItensCozinha> listaProdutoCozinha() throws Exception {


        SoapObject request = new SoapObject(NAMESPACE, LISTA_TUDO);


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.implicitTypes = true;
        envelope.dotNet=true;
        envelope.setOutputSoapObject(request);

        HttpTransportSE http = new HttpTransportSE(URL);
        http.call(SOAP_ACTION, envelope);

        SoapObject response = (SoapObject) envelope.bodyIn;

        ArrayList<ItensCozinha> itens = new ArrayList<ItensCozinha>();

        for (int i = 0; i < response.getPropertyCount(); i ++){

            SoapObject soapObject = (SoapObject) response.getProperty(i);

            ItensCozinha itnc = new ItensCozinha();
            itnc.setId(Integer.parseInt(soapObject.getProperty("id").toString()));
            SoapObject s = (SoapObject) soapObject.getProperty("mesa");
            itnc.setNume_mesa(Integer.parseInt(s.getProperty("id").toString()));
            SoapObject sp = (SoapObject) soapObject.getProperty("produto");
            itnc.setProduto(sp.getProperty("descricao").toString());
            itnc.setEnviado(soapObject.getProperty("enviado").toString());



            itens.add(itnc);
        }
        return itens;
    }

    public void enviaCozinhaMesa(int id)throws Exception {

        SoapObject request = new SoapObject(NAMESPACE, ENVIA_COZINHA_MESA);
        request.addProperty("id", id);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.implicitTypes = true;
        envelope.dotNet=true;
        envelope.setOutputSoapObject(request);

        HttpTransportSE http = new HttpTransportSE(URL);
        http.call(SOAP_ACTION2, envelope);

        SoapObject response = (SoapObject) envelope.bodyIn;



    }
    public void retornaCozinhaMesa(int id)throws Exception {

        SoapObject request = new SoapObject(NAMESPACE, RETORNA_COZINHA_MESA);
        request.addProperty("id", id);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.implicitTypes = true;
        envelope.dotNet=true;
        envelope.setOutputSoapObject(request);

        HttpTransportSE http = new HttpTransportSE(URL);
        http.call(SOAP_ACTION3, envelope);

        SoapObject response = (SoapObject) envelope.bodyIn;



    }
}
