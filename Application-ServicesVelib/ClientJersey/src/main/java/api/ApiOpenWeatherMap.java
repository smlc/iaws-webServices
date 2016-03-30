package api;

import org.w3c.dom.Document;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 * Created by lova on 29/03/16.
 */
public class ApiOpenWeatherMap {

    Client client;
    WebTarget wt;

    private static String apiKey = "fdace7509c0b11ca8b680e39e39d916f";
    private static String UriApi = "http://api.openweathermap.org/data/2.5/weather?appid="+apiKey;

    public ApiOpenWeatherMap () {

        wt = ClientBuilder.newClient().target(UriApi);
        wt.queryParam("mode", "xml");
        Document docXML = wt.request(MediaType.APPLICATION_XML).get(Document.class);
    }

    public void getMeteo () {

        System.out.println(wt.queryParam("mode", "xml").queryParam("q", "toulouse")
                .request(MediaType.APPLICATION_XML).get(String.class));
    }

    public boolean isConnected(){
        return true;
    }
}
