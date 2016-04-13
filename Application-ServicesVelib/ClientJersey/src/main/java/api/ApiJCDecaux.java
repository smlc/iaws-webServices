package api;


import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Station;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import javax.json.*;
import javax.ws.rs.client.*;
import javax.ws.rs.core.*;

/**
 * Created by mars on 16/03/16.
 */

public class ApiJCDecaux {

    Client client;
    WebTarget wt;
    JsonArray jsonArray;
    private static String apiKey = "f6c3385d0ce14f88d6616b15c85bd3989aa478a8";
    private static String UriApi = "https://api.jcdecaux.com/vls/v1/stations?apiKey="+apiKey;

    public ApiJCDecaux() {

        wt = ClientBuilder.newClient().register(new ClientResponseFilter() {

            public void filter(ClientRequestContext clientRequestContext, ClientResponseContext responseContext) throws IOException {
                java.util.List<String> contentType = new ArrayList(1);
                contentType.add(MediaType.APPLICATION_JSON);
                responseContext.getHeaders().put("Content-Type", contentType);
            }
        }).target(UriApi);
    }

    public List<Station> getStation (String contract) {

        jsonArray = wt.queryParam("contract",contract).request(MediaType.APPLICATION_JSON).get(JsonArray.class);

        List<Station> stationList = new ArrayList();
        ObjectMapper mapper = new ObjectMapper();
        try {

            for (int i=0;i<jsonArray.size();i++){
                stationList.add(mapper.readValue(jsonArray.getJsonObject(i).toString(), Station.class));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stationList;
    }

    public boolean isConnected () {

        if(wt.request().head().getStatus()==200){
            return true;
        }
        return false;
    }
}
