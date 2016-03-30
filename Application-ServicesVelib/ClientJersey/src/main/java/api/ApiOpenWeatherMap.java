package api;

import org.w3c.dom.Document;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by lova on 29/03/16.
 */
public class ApiOpenWeatherMap {

    Client client;
    WebTarget wt;

    private static String apiKey = "fdace7509c0b11ca8b680e39e39d916f";
    private static String UriApi = "http://api.openweathermap.org/data/2.5/weather?appid="+apiKey+"&mode=xml";

    public ApiOpenWeatherMap () {

        wt = ClientBuilder.newClient().target(UriApi);

        Document docXML = wt.queryParam("q", "toulouse").request(MediaType.APPLICATION_XML).get(Document.class);

    }

    public void getMeteo () {

        //System.out.println(wt.request(MediaType.APPLICATION_XML).get(String.class));
    }

    public boolean isConnected(){
        return true;
    }
}
