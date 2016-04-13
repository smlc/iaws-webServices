package webservice.serviceuserstory1;
import domain.Station;
import org.w3c.dom.Element;
import services.MediateurService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.*;
import webservice.serviceuserstory1.beanRequete.ObjectFactory;
import webservice.serviceuserstory1.beanRequete.StationReponseType;
import webservice.serviceuserstory1.beanRequete.StationRequestType;
import webservice.serviceuserstory1.beanRequete.StationsType;


import java.util.Iterator;
import java.util.List;


import javax.xml.bind.JAXBElement;


/**
 * Created by mars on 03/04/16.
 */

@Endpoint
public class WsUserStoryOneAEndpoint {

    private static final String NAMESPACE_URI = "http://iaws/ws/contractfirst/station";

    private MediateurService serviceApi;

    @Autowired
    public WsUserStoryOneAEndpoint(MediateurService serviceApi) {

        this.serviceApi = serviceApi;
    }

   @PayloadRoot(localPart = "StationRequest",namespace = NAMESPACE_URI)
   @ResponsePayload
   public Element getStation (@RequestPayload JAXBElement<StationRequestType> request) {

       // Récuperation des valeurs de la requête
       StationRequestType requestClient = request.getValue();

       //Création de la reponse qui sera envoyée au client
       StationReponseType reponseClient = new StationReponseType();
       StationsType listStationReponse = new StationsType();

       //Récuperation des stations non vides via les api.
       List<Station> listStation;
       String chaineRequete = String.format("%s, %s, %s, %s",
               requestClient.getNumeroRue(),requestClient.getNomRue(), requestClient.getVille(),
               requestClient.getCodePostal());

       listStation = serviceApi.getStationsNonVides(requestClient.getVille(),chaineRequete);

       //Construction de la réponse
       ObjectFactory factory = new ObjectFactory();
       Iterator<Station>  it = listStation.iterator();

       while (it.hasNext()) {
           listStationReponse.getStation().add(factory.createStationType(it.next()));
       }

       reponseClient.setStations(listStationReponse);

       return factory.createElementStation(reponseClient);
    }


    @PayloadRoot(localPart = "StationRequestNonComplete",namespace = NAMESPACE_URI)
    @ResponsePayload
    public Element getStationNonComplete(@RequestPayload JAXBElement<StationRequestType> request){

       // Récuperation des valeurs de la requête
        StationRequestType requestClient = request.getValue();

        //Création de la reponse qui sera envoyée au client
        StationReponseType reponseClient = new StationReponseType();
        StationsType listStationReponse = new StationsType();

        //Récuperation des stations non vides via les api.
        List<Station> listStation;

        String chaineRequete = String.format("%s, %s, %s, %s",
                requestClient.getNumeroRue(),requestClient.getNomRue(), requestClient.getVille(),
                requestClient.getCodePostal());

        listStation = serviceApi.getStationsNonCompletes(requestClient.getVille(),chaineRequete);

        //Construction de la réponse
        ObjectFactory factory = new ObjectFactory();
        Iterator<Station>  it = listStation.iterator();

        while(it.hasNext()){
            listStationReponse.getStation().add(factory.createStationType(it.next()));
        }

        reponseClient.setStations(listStationReponse);

        return factory.createElementStation(reponseClient);
    }

}
