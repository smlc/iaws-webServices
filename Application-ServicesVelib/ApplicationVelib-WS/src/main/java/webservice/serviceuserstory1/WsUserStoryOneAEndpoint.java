package webservice.serviceuserstory1;
import domain.Station;
import services.MediateurService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.*;

import java.util.Iterator;
import java.util.List;
import webservice.serviceuserstory1.beanRequete.*;

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

   @PayloadRoot(localPart = "StationVideRequest",namespace = NAMESPACE_URI)
   @ResponsePayload
   public JAXBElement<StationReponseType> getStation(@RequestPayload JAXBElement<StationRequestType> request){



       /*System.out.println(request.getValue().getNomRue());
            StationReponseType reponse = new StationReponseType();

            StationType stationType = new ObjectFactory().createStationType();
       stationType.setAdresse("31 rue Valade");
       stationType.setAvailableBikes("2");
       stationType.setAvailableBikeStands("5");
       stationType.setName("Station11");
       reponse.setStation(stationType);*/

       /*Récuperation des valeur de la requeste*/
       StationRequestType requestClient = request.getValue();

       //Création de la reponse qui sera envoyé au client
       StationReponseType reponseClient = new StationReponseType();
       StationsType listStationReponse = new StationsType();

       //Récuperation des station non vides via les api.
       List<Station> listStation;
       if(requestClient.isRequeteStationNonVide()){

           listStation = serviceApi.getStationsNonVides(requestClient.getVille());
       }else{
           listStation = serviceApi.getStationsNonCompletes(requestClient.getVille());
       }



       //Construction de la réponse
       ObjectFactory factory = new ObjectFactory();
       Iterator<Station>  it = listStation.iterator();

       while(it.hasNext()){
           listStationReponse.getStation().add(factory.createStationType(it.next()));
       }
       reponseClient.setStations(listStationReponse);

       return factory.createStationReponse(reponseClient);
    }


}
