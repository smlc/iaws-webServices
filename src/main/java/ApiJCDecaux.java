import com.google.gson.JsonArray;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.Map;

/**
 * Created by mars on 16/03/16.
 */

public class ApiJCDecaux {

    Client client;
    public ApiJCDecaux(){
         client = ClientBuilder.newClient();
        wt = client.target("http://notes.tsaap.eu/Scolarite/api/v1");
        etu = wt.path("etudiants").request(MediaType.APPLICATION_JSON).get(JsonArray.class);
    }

    public Map<String,Integer> getStationNonVide(String adresse){

    }

    public Map<String,Integer> getStationNonComplet(){

    }

}
