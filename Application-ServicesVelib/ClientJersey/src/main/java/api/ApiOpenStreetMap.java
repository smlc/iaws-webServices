package api;

import domain.Coordonne;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.io.IOException;
import java.io.StringReader;


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
            System.out.println(lat +" "+lon);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }


        return new Coordonne(Double.parseDouble(lat),Double.parseDouble(lon));
    }
}
