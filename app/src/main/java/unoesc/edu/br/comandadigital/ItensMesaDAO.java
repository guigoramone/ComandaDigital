package unoesc.edu.br.comandadigital;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 04/10/15.
 */
public class ItensMesaDAO {

    private static final String URL = "http://192.168.43.8:8080/comandadigitalwebservices/services/ItensDAO?wsdl";
    private static final String NAMESPACE = "http://unoesc.com.br.edu";
    //casa: 192.168.1.24
    //unoesc 192.168.43.8

    private static final String  LISTA_ABERTA= "listarMesaOcupada";
    private static final String FECHA_MESA="fechaMesa";
    private static final String SOAP_ACTION1 = "http://unoesc.com.br.edu/listarMesaOcupada";
    private static final String SOAP_ACTION2="http://unoesc.com.br.edu/fechaMesa";

    //METHOD_NAME



    public List <ItensMesa> listaMesasAbertas(int id) throws Exception{


        SoapObject request = new SoapObject(NAMESPACE,LISTA_ABERTA);

        request.addProperty("id", id);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.implicitTypes = true;
        envelope.dotNet=true;
        envelope.setOutputSoapObject(request);

        HttpTransportSE http = new HttpTransportSE(URL);
        http.call(SOAP_ACTION1, envelope);

        SoapObject response = (SoapObject) envelope.bodyIn;

        ArrayList<ItensMesa> ItensMesas = new ArrayList<>();

        for (int i = 0; i < response.getPropertyCount(); i ++) {

            SoapObject soapObject = (SoapObject) response.getProperty(i);

            ItensMesa itnm = new ItensMesa();


            SoapObject mesasoapbject = (SoapObject) soapObject.getProperty("mesa");
            itnm.setNume_mesa(Integer.parseInt(mesasoapbject.getProperty("id").toString()));


            SoapObject produtosoapobject = (SoapObject) soapObject.getProperty("produto");
            itnm.setProduto(produtosoapobject.getProperty("descricao").toString());
            itnm.setValor(Double.parseDouble(produtosoapobject.getProperty("preco").toString()));


            itnm.getValor();


            ItensMesas.add(itnm);

        }
        return ItensMesas;
    }

    public void fechaMesa(int id)throws Exception {

        SoapObject request = new SoapObject(NAMESPACE, FECHA_MESA);
        request.addProperty("id", id);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.implicitTypes = true;
        envelope.dotNet=true;
        envelope.setOutputSoapObject(request);

        HttpTransportSE http = new HttpTransportSE(URL);
        http.call(SOAP_ACTION2, envelope);

        SoapObject response = (SoapObject) envelope.bodyIn;



    }
}






