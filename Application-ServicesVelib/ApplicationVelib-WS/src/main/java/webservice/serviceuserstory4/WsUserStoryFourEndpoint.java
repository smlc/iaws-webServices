package webservice.serviceuserstory4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import services.MediateurService;
import webservice.serviceuserstory4.beanRequeteUserStory4.AdresseType;
import webservice.serviceuserstory4.beanRequeteUserStory4.ObjectFactory;
import webservice.serviceuserstory4.beanRequeteUserStory4.RequestWs4Type;
import webservice.serviceuserstory4.beanRequeteUserStory4.ResponseWs4Type;

import javax.xml.bind.JAXBElement;
import java.util.Iterator;

/**
 * Created by lova on 12/04/16.
 */

@Endpoint
public class WsUserStoryFourEndpoint {

    private static final String NAMESPACE_URI = "http://iaws/ws/contractfirst/station";

    private MediateurService serviceApi;

    @Autowired
    public WsUserStoryFourEndpoint(MediateurService serviceApi) {

        this.serviceApi = serviceApi;
    }

    @PayloadRoot(localPart = "RequestWs4",namespace = NAMESPACE_URI)
    @ResponsePayload
    public JAXBElement<ResponseWs4Type> getTempsTrajetVelo (@RequestPayload JAXBElement<RequestWs4Type> request) {

        // Récupération des valeurs de la requête
        RequestWs4Type requestClient = request.getValue();

        // Récupération des adresses
        Iterator<AdresseType> it = requestClient.getAdresse().iterator();
        AdresseType address;
        String requestAddressStart = null;
        String requestAddressArrival = null;

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

        String responseTime = this.serviceApi.getTempsTrajetVelo(requestAddressStart, requestAddressArrival,
                requestClient.getAdresse().get(0).getVille());

        // Création de la réponse qui sera envoyée au client
        ResponseWs4Type responseClient = new ResponseWs4Type();
        responseClient.setTime(responseTime);

        return new ObjectFactory().createResponseWs4(responseClient);
    }
}
