package api;

import org.w3c.dom.Document;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

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
        if(!isConnected()){
            System.out.println("Connexion echoué");
            return;
        }
    }

    public Double getMeteo(String ville) {

        Document docXML = wt.queryParam("mode", "xml").queryParam("q", ville)
                .request(MediaType.APPLICATION_XML).get(Document.class);

        XPath xPath =  XPathFactory.newInstance().newXPath();
        String pluviometrie = null;

        // Récupération du volume d'eau
        try {
            pluviometrie = xPath.compile("/current/precipitation[@mode]/@value").evaluate(docXML);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        // On retourne la valeur du volume d'eau que si elle n'est pas nulle
        if (!pluviometrie.isEmpty())
            return Double.parseDouble(pluviometrie);
        return 0.;
    }

    public boolean isConnected() {

        if(wt.request().head().getStatus()==200)
            return true;

        return false;
    }
}
