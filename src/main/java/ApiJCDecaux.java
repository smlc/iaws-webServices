import java.awt.*;
import java.util.List;
import java.util.Map;

import javax.json.JsonArray;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 * Created by mars on 16/03/16.
 */

public class ApiJCDecaux {

    Client client;
    WebTarget wt;
    JsonArray etu;

    public ApiJCDecaux(){
        client = ClientBuilder.newClient();
        wt = client.target("http://notes.tsaap.eu/Scolarite/api/v1");
        etu = wt.path("etudiants").request(MediaType.APPLICATION_JSON).get(JsonArray.class);
    }

    public Map<String,Integer> getStationNonVide(Point point){
        return null;
    }

    public Map<String,Integer> getStationNonComplet(){
        return null;
    }

}
