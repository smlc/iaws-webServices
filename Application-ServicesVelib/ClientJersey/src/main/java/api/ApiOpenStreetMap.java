package api;

import domain.Coordonne;
import org.w3c.dom.Document;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;


/**
 * Created by mars on 29/03/16.
 */
public class ApiOpenStreetMap {


    Client client;
    WebTarget wt;
    private static String UriApi = "http://nominatim.openstreetmap.org/search?format=xml";

    public ApiOpenStreetMap(){
        wt = ClientBuilder.newClient().target(UriApi);
        if(!isConnected()){
            System.out.println("Connexion echoué");
            return;
        }
    }
    public boolean isConnected(){
        if(wt.request().head().getStatus()==200)
            return true;

        return false;
    }
    public Coordonne getLatLong(String address) {



        Document docXML = wt.queryParam("q",address)
                .request(MediaType.APPLICATION_XML).get(Document.class);


        if(!isConnected()){
            System.out.println("Connexion echoué");
            return null;
        }


        XPath xPath =  XPathFactory.newInstance().newXPath();

        String lat = null;
        String lon = null;
        try {
            lat = xPath.compile("/searchresults/place/@lat").evaluate(docXML);
            lon = xPath.compile("/searchresults/place/@lon").evaluate(docXML);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }


        return new Coordonne(Double.parseDouble(lat),Double.parseDouble(lon));
    }
}
