package api;



import javax.json.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import java.awt.*;
import java.io.StringReader;

/**
 * Created by lova on 23/03/16.
 */
public class ApiArcGIS {

    Client client;
    WebTarget wt;
    JsonObject object;
    private static String UriApi = "http://sampleserver6.arcgisonline.com/arcgis/rest/services/Utilities/Geometry/GeometryServer/distance?f=json";

    public ApiArcGIS () {
        wt = ClientBuilder.newClient().target(UriApi);

        String jsonObjectString = wt.
                request(MediaType.APPLICATION_JSON).get(String.class);
        System.out.println(jsonObjectString);
        JsonReader jsonReader = Json.createReader(new StringReader(jsonObjectString));
        object = jsonReader.readObject();
        jsonReader.close();
    }

    public boolean isConnected () {
        return true;
    }

    public double getDistance (int wkid, Point pointFrom, Point pointTo) {

        String geometry1 = "%7B" +
                "\"geometryType\" : \"esriGeometryPoint\"," +
                "\"geometry\" : %7B" +
                "\"x\" : " +pointFrom.getX()+ "," +
                "\"y\" : " +pointFrom.getY() +
                "%7D" +
                "%7D";

        String geometry2 = "%7B" +
                "\"geometryType\" : \"esriGeometryPoint\"," +
                "\"geometry\" : %7B" +
                "\"x\" : " +pointTo.getX()+ "," +
                "\"y\" : " +pointTo.getY() +
                "%7D" +
                "%7D";

        System.out.println(wt.queryParam("sr",wkid).queryParam("geometry1", geometry1)
                .queryParam("geometry2", geometry2).queryParam("geodesic", true)
                .request(MediaType.APPLICATION_JSON).get(String.class));
        object.getJsonObject("distance");

        return 0.;
    }
}
