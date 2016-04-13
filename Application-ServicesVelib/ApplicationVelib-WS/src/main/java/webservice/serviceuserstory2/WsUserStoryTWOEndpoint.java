package webservice.serviceuserstory2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import services.MediateurService;
import webservice.serviceuserstory1.beanRequete.StationReponseType;
import webservice.serviceuserstory1.beanRequete.StationRequestType;
import webservice.serviceuserstory2.beanRequeteUserStory2.EnumRisque;
import webservice.serviceuserstory2.beanRequeteUserStory2.ReponseWS2;

import javax.xml.bind.JAXBElement;


/**
 * Created by mars on 03/04/16.
 */

@Endpoint
public class WsUserStoryTWOEndpoint {

    private static final String NAMESPACE_URI = "http://iaws/ws/contractfirst/station";

    private MediateurService serviceApi;

    @Autowired
    public WsUserStoryTWOEndpoint(MediateurService serviceApi) {
        this.serviceApi = serviceApi;
    }

   @PayloadRoot(localPart = "RequestWS2",namespace = NAMESPACE_URI)
   @ResponsePayload
   public ReponseWS2 getInfoChausser(@RequestPayload StationRequestType request){


       /*Récuperation des valeur de la requeste*/
      // StationRequestType requestClient = request.;

       //Construction de l'address

       String valeurRisqueChausser = serviceApi.getInfoChausser(request.getVille());

       ReponseWS2 reponseClient = new ReponseWS2();
       reponseClient.setRisque(EnumRisque.fromValue(valeurRisqueChausser));

       return reponseClient;
    }


}
