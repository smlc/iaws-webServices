package webservice.serviceuserstory3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import services.MediateurService;
import webservice.serviceuserstory3.beanRequeteUserStory3.AdresseType;
import webservice.serviceuserstory3.beanRequeteUserStory3.ObjectFactory;
import webservice.serviceuserstory3.beanRequeteUserStory3.RequestWs3Type;
import webservice.serviceuserstory3.beanRequeteUserStory3.ResponseWs3Type;


import javax.xml.bind.JAXBElement;
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

    @PayloadRoot(localPart = "RequestWs3",namespace = NAMESPACE_URI)
    @ResponsePayload
    public JAXBElement<ResponseWs3Type> getTempsTrajet (@RequestPayload JAXBElement<RequestWs3Type> request) {

        // Récupération des valeurs de la requête
        RequestWs3Type requestClient = request.getValue();

        // Récupération des adresses
        Iterator<AdresseType> it = requestClient.getAdresse().iterator();
        AdresseType address;
        String requestAddressStart = null;
        String requestAddressArrival = null;
        String responseTime = null;

        while(it.hasNext()){
            address = it.next();
            if (address.getAtt().equals("depart")) {
                requestAddressStart = String.format("%s, %s, %s, %s",
                        address.getNumeroRue(),
                        address.getNomRue(),
                        address.getVille(),
                        address.getCodePostal());
            } else if (address.getAtt().equals("arrivee")) {
                requestAddressArrival = String.format("%s, %s, %s, %s",
                        address.getNumeroRue(),
                        address.getNomRue(),
                        address.getVille(),
                        address.getCodePostal());
            }
        }

        responseTime = this.serviceApi.getTempsTrajet(requestAddressStart, requestAddressArrival,
                requestClient.getAdresse().get(0).getVille());

        // Création de la réponse qui sera envoyée au client
        ResponseWs3Type responseClient = new ResponseWs3Type();
        responseClient.setTime(responseTime);

        return new ObjectFactory().createResponseWs3(responseClient);
    }
}
