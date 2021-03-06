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

    private static final String URL = "http://192.168.43.8:8080/comandadigitalwebservices/services/ItensDAO?wsdl";
    private static final String NAMESPACE = "http://unoesc.com.br.edu";
    //casa: 192.168.1.24
    //unoesc 172.18.19.119

    private static final String METHOD_NAME = "listarCopa";
    private static final String SOAP_ACTION = "http://unoesc.com.br.edu/listarCopa";
    private static final String ENVIA_COPA_MESA = "enviaCopaMesa";
    private static final String RETORNA_COPA_MESA = "retornaCopaMesa";
    private static final String SOAP_ACTION2 = "http://unoesc.com.br.edu/enviaCopaMesa";
    private static final String SOAP_ACTION3 = "http://unoesc.com.br.edu/retornaCopaMesa";


    public ArrayList<ItensCopa> listaProdutoCopa() throws Exception {


        SoapObject request = new SoapObject(NAMESPACE,METHOD_NAME);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.implicitTypes = true;
        envelope.dotNet=true;
        envelope.setOutputSoapObject(request);

        HttpTransportSE http = new HttpTransportSE(URL);
        http.call(SOAP_ACTION, envelope);

        SoapObject response = (SoapObject) envelope.bodyIn;

        ArrayList<ItensCopa> itens = new ArrayList<ItensCopa>();

        for (int i = 0; i < response.getPropertyCount(); i ++){

            SoapObject soapObject = (SoapObject) response.getProperty(i);

            ItensCopa itn = new ItensCopa();
            itn.setId(Integer.parseInt(soapObject.getProperty("id").toString()));
            SoapObject s = (SoapObject) soapObject.getProperty("mesa");
            itn.setNume_mesa(Integer.parseInt(s.getProperty("id").toString()));
            SoapObject sp = (SoapObject) soapObject.getProperty("produto");
            itn.setProduto(sp.getProperty("descricao").toString());
            itn.setEnviado(soapObject.getProperty("enviado").toString());




            itens.add(itn);
        }
        return itens;
    }

    public void enviaCopaMesa(int id)throws Exception {

        SoapObject request = new SoapObject(NAMESPACE, ENVIA_COPA_MESA);
        request.addProperty("id", id);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.implicitTypes = true;
        envelope.dotNet=true;
        envelope.setOutputSoapObject(request);

        HttpTransportSE http = new HttpTransportSE(URL);
        http.call(SOAP_ACTION2, envelope);

        SoapObject response = (SoapObject) envelope.bodyIn;



    }
    public void retornaCopaMesa(int id)throws Exception {

        SoapObject request = new SoapObject(NAMESPACE, RETORNA_COPA_MESA);
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
