package webservice.serviceuserstory1;
import org.springframework.core.io.ClassPathResource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import services.MediateurService;
import webservice.serviceuserstory1.mypackage.ObjectFactory;
import webservice.serviceuserstory1.mypackage.StationReponseType;
import webservice.serviceuserstory1.mypackage.StationRequestType;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.ws.server.endpoint.annotation.*;

import services.Mediateur;

import java.util.List;
import org.w3c.dom.Element;
import webservice.serviceuserstory1.mypackage.StationType;

import javax.xml.bind.JAXBElement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by mars on 03/04/16.
 */

@Endpoint
public class WsUserStoryOneAEndpoint {

    private static final String NAMESPACE_URI = "http://iaws/ws/contractfirst/station";

    private MediateurService serviecApi;

    @Autowired
    public WsUserStoryOneAEndpoint(MediateurService serviecApi) {

        this.serviecApi = serviecApi;
    }

   @PayloadRoot(localPart = "StationRequest",namespace = NAMESPACE_URI)
   @ResponsePayload
   public JAXBElement<StationReponseType> getStationNonVides(@RequestPayload JAXBElement<StationRequestType> request){


            StationRequestType requestClient = request.getValue();



            StationType stationType = new ObjectFactory().createStationType();
       stationType.setAdresse("31 rue Valade");
       stationType.setAvailableBikes("2");
       stationType.setAvailableBikeStands("5");
       stationType.setName("Station11");
       reponse.setStation(stationType);


            return new ObjectFactory().createStationReponse(reponse);
    }


}
