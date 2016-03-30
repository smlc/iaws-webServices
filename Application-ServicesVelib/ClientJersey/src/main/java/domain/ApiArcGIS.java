package domain;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

/**
 * Created by lova on 23/03/16.
 */
public class ApiArcGIS {

    Client client;
    WebTarget wt;
    JsonObject object;
    private static String UriApi = "http://sampleserver6.arcgisonline.com/arcgis/rest/services/Utilities/Geometry/GeometryServer/distance?f=json";

    public ApiArcGIS () {
        wt = ClientBuilder.newClient().register(new ClientResponseFilter() {

            public void filter(ClientRequestContext clientRequestContext, ClientResponseContext responseContext) throws IOException {
                java.util.List<String> contentType = new ArrayList(1);
                contentType.add(MediaType.APPLICATION_JSON);
                responseContext.getHeaders().put("Content-Type", contentType);
            }
        }).target(UriApi);
        String jsonObjectString = wt.
                request(MediaType.APPLICATION_JSON).get(String.class);

        JsonReader jsonReader = Json.createReader(new StringReader(jsonObjectString));
        object = jsonReader.readObject();
        jsonReader.close();
    }

    public boolean isConnected () {
        return true;
    }

    public double getDistance (int wkid, Point pointFrom, Point pointTo) {

        MultivaluedMap<String, String> formData = new MultivaluedHashMap<String, String>();

        JsonObject jSongeometry1 = Json.createObjectBuilder()
                .add("geometryType", "esriGeometryPoint")
                .add("geometry", Json.createObjectBuilder()
                        .add("x", pointFrom.getX())
                        .add("y", pointFrom.getY()))
                .build();

        JsonObject jSongeometry2 = Json.createObjectBuilder()
                .add("geometryType", "esriGeometryPoint")
                .add("geometry", Json.createObjectBuilder()
                        .add("x", pointFrom.getX())
                        .add("y", pointFrom.getY()))
                .build();

        formData.putSingle("sr", wkid+"");
        formData.putSingle("geometry1", jSongeometry1.toString());
        formData.putSingle("geometry2", jSongeometry2.toString());
        formData.putSingle("geodesic", "true");

        System.out.println(wt.request(MediaType.APPLICATION_JSON).post(Entity.form(formData), JsonObject.class));
        return 0.;
    }
}
