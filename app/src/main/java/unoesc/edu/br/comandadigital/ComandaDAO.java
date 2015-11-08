package unoesc.edu.br.comandadigital;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

/**
 * Created by root on 06/11/15.
 */
public class ComandaDAO {

    private static final String URL = "http://192.168.43.8:8080/comandadigitalwebservices/services/MesaDAO?wsdl";
    private static final String NAMESPACE = "http://unoesc.com.br.edu";
    //casa: 192.168.1.24
    //unoesc 172.18.19.119

    private static final String ADD_MESA = "buscar";
    private static final String SOAP_ACTION = "http://unoesc.com.br.edu/buscar";


    public void abreMesa(int id)throws Exception {

        SoapObject request = new SoapObject(NAMESPACE, ADD_MESA);
        request.addProperty("id", id);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.implicitTypes = true;
        envelope.dotNet=true;
        envelope.setOutputSoapObject(request);

        HttpTransportSE http = new HttpTransportSE(URL);
        http.call(SOAP_ACTION, envelope);

        SoapObject response = (SoapObject) envelope.bodyIn;

        //Integer num_mesa = response.getProperty("id");



    }
}
