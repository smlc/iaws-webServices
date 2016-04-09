package webservice.serviceuserstory3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import services.MediateurService;
import webservice.serviceuserstory3.beanRequeteUserStory3.*;

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

/**
 * Created by lova on 09/04/16.
 */

@Endpoint
public class WsUserStoryThreeEndpoint {

    private static final String NAMESPACE_URI = "http://iaws/ws/contractfirst/station";

    private MediateurService serviceApi;

    @Autowired
    public WsUserStoryThreeEndpoint(MediateurService serviceApi) {

        this.serviceApi = serviceApi;
    }

    @PayloadRoot(localPart = "StationWs3Request",namespace = NAMESPACE_URI)
    @ResponsePayload
    public ResponseWs3Type getTempsTrajet(@RequestPayload JAXBElement<RequestWs3Type> request){

        // Récupération des valeurs de la requête
        RequestWs3Type requestClient = request.getValue();

        // Récupération des adresses
        Iterator<AdresseType>  it = requestClient.getAdresse().iterator();
        AdresseType address;
        String requestAddressStart = null;
        String requestAddressArrival = null;

        while(it.hasNext()){
            address = it.next();
            if (address.getAtt().equals("depart")) {
                requestAddressStart = String.format("%s %s, %s, %s",
                        address.getNumeroRue(),
                        address.getNomRue(),
                        address.getVille(),
                        address.getCodePostal());
            } else if (address.getAtt().equals("arrivee")) {
                requestAddressArrival = String.format("%s %s, %s, %s",
                        address.getNumeroRue(),
                        address.getNomRue(),
                        address.getVille(),
                        address.getCodePostal());
            }
        }

        String responseTime = this.serviceApi.getTempsTrajet(requestAddressStart, requestAddressArrival);

        // Création de la réponse qui sera envoyée au client
        ResponseWs3Type responseClient = new ResponseWs3Type();

        // Construction de la réponse
        XMLGregorianCalendar value = null;
        Date date;
        SimpleDateFormat simpleDateFormat;
        GregorianCalendar gregorianCalendar;

        simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        try {
            date = simpleDateFormat.parse(responseTime);
            gregorianCalendar = (GregorianCalendar)GregorianCalendar.getInstance();
            gregorianCalendar.setTime(date);
            value = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }

        responseClient.setTime(value);

        return responseClient;
    }
}
