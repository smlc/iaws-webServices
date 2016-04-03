package webservice.serviceuserstory1;
import webservice.serviceuserstory1.mypackage.StationReponseType;
import webservice.serviceuserstory1.mypackage.StationRequestType;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.ws.server.endpoint.annotation.*;

import services.Mediateur;

/**
 * Created by mars on 03/04/16.
 */

@Endpoint
public class WsUserStoryOneAEndpoint {

    private static final String NAMESPACE_URI = "http://iaws/ws/contractfirst/station";

    private Mediateur mediateuApi;

    @Autowired
    public WsUserStoryOneAEndpoint(Mediateur mediateurApi) {
        this.mediateuApi = mediateurApi;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "StationReponse")
    @ResponsePayload
    public StationReponseType getStationNonVides(@RequestPayload StationRequestType request){

            StationReponseType reponse = new StationReponseType();
            reponse.getStation().setAdresse("31 rue valade");
            reponse.getStation().setAvailableBikes("2");
            reponse.getStation().setAvailableBikeStands("5");
            reponse.getStation().setName("Station1");

            return reponse;
    }

}
