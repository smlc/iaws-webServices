package webservice.serviceuserstory3;

import domain.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import services.MediateurService;
import webservice.serviceuserstory3.beanRequeteUserStory3.*;

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Response;
import java.util.Iterator;
import java.util.List;

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
    public JAXBElement<ResponseWs3Type> getTempsTrajet(@RequestPayload JAXBElement<RequestWs3Type> request){

        // Récupération des valeurs de la requête
        RequestWs3Type requestClient = request.getValue();

        // Récupération des adresses
        String requestAddressStart = String.format("%s %s, %s, %s",
                requestClient.getAdresse().get(0).getNumeroRue(),
                requestClient.getAdresse().get(0).getNomRue(),
                requestClient.getAdresse().get(0).getVille(),
                requestClient.getAdresse().get(0).getCodePostal());

        String requestAddressArrival = String.format("%s %s, %s, %s",
                requestClient.getAdresse().get(1).getNumeroRue(),
                requestClient.getAdresse().get(1).getNomRue(),
                requestClient.getAdresse().get(1).getVille(),
                requestClient.getAdresse().get(1).getCodePostal());

        XMLGregorianCalendar time = this.serviceApi.getTempsTrajet(requestAddressStart, requestAddressArrival);

        // Création de la réponse qui sera envoyée au client
        ResponseWs3Type responseClient = new ResponseWs3Type();

        responseClient.setTime(time);

        return null;
    }
}
