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
    public JAXBElement<ResponseWs3Type> getTempsTrajet(@RequestPayload JAXBElement<ResponseWs3Type> request){


        return null;
    }
}
