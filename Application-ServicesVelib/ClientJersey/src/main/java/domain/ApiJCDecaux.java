package domain;


import domain.Station;

import java.io.StringReader;



import javax.json.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 * Created by mars on 16/03/16.
 */

public class ApiJCDecaux {

    Client client;
    WebTarget wt;
    JsonArray object;
    private static String apiKey = "f6c3385d0ce14f88d6616b15c85bd3989aa478a8";
    private static String UriApi = "https://api.jcdecaux.com/vls/v1/stations?apiKey="+apiKey;

    public ApiJCDecaux(){

        wt = ClientBuilder.newClient().target(UriApi);

        String jsonObjectString = wt.
                request(MediaType.APPLICATION_JSON).get(String.class);

        JsonReader jsonReader = Json.createReader(new StringReader(jsonObjectString));
        object = jsonReader.readArray();
        jsonReader.close();

    }

    public Station getStation(String contract){
        System.out.println(wt.queryParam("contract",contract)
                .request(MediaType.APPLICATION_JSON).get(String.class));
        object.getJsonObject(1).getString("status");
        Station station = new Station();
        station.setAdresse(object.getJsonObject(1).getString("address"));
<<<<<<< HEAD
=======
        return null;
>>>>>>> 50cdbd03406f8ba0665fd5e2eb33ae5ffa37ad8b

        return null;
    }

    public boolean isConnected(){
        return true;
    }
}
