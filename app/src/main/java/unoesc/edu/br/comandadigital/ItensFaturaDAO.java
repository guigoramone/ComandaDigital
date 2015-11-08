package unoesc.edu.br.comandadigital;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 04/10/15.
 */
public class ItensFaturaDAO {

    private static final String URL = "http://192.168.43.8:8080/comandadigitalwebservices/services/ItensDAO?wsdl";
    private static final String NAMESPACE = "http://unoesc.com.br.edu";
    //casa: 192.168.1.24
    //unoesc 192.168.43.8

    private static final String LISTA_FECHADAS = "listarMesaFechada";
    private static final String ENCERRA_MESA ="encerraMesa";
    private static final String SOAP_ACTION1 = "http://unoesc.com.br.edu/listarMesaFechada";
    private static final String SOAP_ACTION2="http://unoesc.com.br.edu/encerraMesa";

    //METHOD_NAME



    public List <ItensFatura> listaMesasEncerradas(int id) throws Exception{


        SoapObject request = new SoapObject(NAMESPACE, LISTA_FECHADAS);

        request.addProperty("id", id);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.implicitTypes = true;
        envelope.dotNet=true;
        envelope.setOutputSoapObject(request);

        HttpTransportSE http = new HttpTransportSE(URL);
        http.call(SOAP_ACTION1, envelope);

        SoapObject response = (SoapObject) envelope.bodyIn;

        ArrayList<ItensFatura> ItensFatura = new ArrayList<>();

        for (int i = 0; i < response.getPropertyCount(); i ++) {

            SoapObject soapObject = (SoapObject) response.getProperty(i);

            ItensFatura itnm = new ItensFatura();


            SoapObject mesasoapbject = (SoapObject) soapObject.getProperty("mesa");
            itnm.setNume_mesa(Integer.parseInt(mesasoapbject.getProperty("id").toString()));


            SoapObject produtosoapobject = (SoapObject) soapObject.getProperty("produto");
            itnm.setProduto(produtosoapobject.getProperty("descricao").toString());
            itnm.setValor(Double.parseDouble(produtosoapobject.getProperty("preco").toString()));


            itnm.getValor();


            ItensFatura.add(itnm);



            /*ItensFatura itnm = new ItensFatura();
            itnm.setNume_mesa(Integer.parseInt(soapObject.getProperty("nume_mesa").toString()));
            itnm.setProduto(soapObject.getProperty("produto").toString());
            itnm.setValor(Double.parseDouble(soapObject.getProperty("valor").toString()));

            itnm.getValor();


            ItensFatura.add(itnm);*/

        }
        return ItensFatura;
    }

    public void encerraMesa(int id)throws Exception {

        SoapObject request = new SoapObject(NAMESPACE, ENCERRA_MESA);
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