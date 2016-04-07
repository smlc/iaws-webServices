package webservice.serviceuserstory2;

import domain.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import services.MediateurService;
import webservice.serviceuserstory1.beanRequete.ObjectFactory;
import webservice.serviceuserstory1.beanRequete.StationReponseType;
import webservice.serviceuserstory1.beanRequete.StationRequestType;
import webservice.serviceuserstory1.beanRequete.StationsType;

import javax.xml.bind.JAXBElement;
import java.util.Iterator;
import java.util.List;


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

   @PayloadRoot(localPart = "StationVideRequest",namespace = NAMESPACE_URI)
   @ResponsePayload
   public JAXBElement<StationReponseType> getInfoChausser(@RequestPayload JAXBElement<StationRequestType> request){


       return factory.createStationReponse(reponseClient);
    }


}
