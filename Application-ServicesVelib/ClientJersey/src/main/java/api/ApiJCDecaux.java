package api;


import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Station;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;


import javax.json.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.*;

/**
 * Created by mars on 16/03/16.
 */

public class ApiJCDecaux {

    Client client;
    WebTarget wt;
    JsonArray objectArray;
    private static String apiKey = "f6c3385d0ce14f88d6616b15c85bd3989aa478a8";
    private static String UriApi = "https://api.jcdecaux.com/vls/v1/stations?apiKey="+apiKey;

    public ApiJCDecaux(){

        wt = ClientBuilder.newClient().target(UriApi);

        String jsonObjectString = wt.
                request(MediaType.APPLICATION_JSON).get(String.class);

        JsonReader jsonReader = Json.createReader(new StringReader(jsonObjectString));
        objectArray = jsonReader.readArray();
        jsonReader.close();

    }

    public List<Station> getStation(String contract){


        String jsonObjectString = wt.queryParam("contract",contract).request(MediaType.APPLICATION_JSON).get(String.class);
        JsonReader jsonReader = Json.createReader(new StringReader(jsonObjectString));
        objectArray = jsonReader.readArray();
        jsonReader.close();
        List<Station> stationList = new ArrayList();
        ObjectMapper mapper = new ObjectMapper();
        try {

            for (int i=0;i<objectArray.size();i++){
                stationList.add(mapper.readValue(objectArray.getJsonObject(i).toString(), Station.class));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return stationList;
    }

    public boolean isConnected(){
        if(wt.request().head().getStatus()==200)
            return true;

        return false;
    }
}
