package api;


import domain.Coordonne;
import domain.Station;

import javax.json.*;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import java.awt.*;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;
import java.util.List;

/**
 * Created by lova on 23/03/16.
 */
public class ApiArcGIS {

    Client client;
    WebTarget wt;
    JsonObject object;
    int wkid;
    private static String UriApi = "http://sampleserver6.arcgisonline.com/arcgis/rest/services/Utilities/Geometry/GeometryServer/lengths?f=json";

    public ApiArcGIS () {
        wkid = 27563;
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
        if(wt.request().head().getStatus()==200)
            return true;

        return false;
    }

    public double getDistance (Point pointFrom, Point pointTo) {

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

        formData.putSingle("sr", this.wkid+"");
        formData.putSingle("geometry1", jSongeometry1.toString());
        formData.putSingle("geometry2", jSongeometry2.toString());
        formData.putSingle("geodesic", "true");

        System.out.println(wt.request(MediaType.APPLICATION_JSON).post(Entity.form(formData), JsonObject.class));
        return 0.;
    }

    public Map<Station, Double> getLengths (Coordonne adressClient, List<Station> stations) {

        MultivaluedMap<String, String> formData = new MultivaluedHashMap<String, String>();
        List<Station> candidateStations = new ArrayList<>();
        Map<Station, Double> threeStations = new HashMap<>();

        String jsonRequest = "[";
        for (int i=0; i<stations.size(); i++) {
            if (stations.get(i).getAvailable_bikes() != 0) {
                jsonRequest = jsonRequest+"{\"paths\":" +
                        "[[["+adressClient.getLat()+", "+adressClient.getLon()+"], " +
                        "["+stations.get(i).getPosition().get("lat")+", "+stations.get(i).getPosition().get("lng") +
                        "]]]}";
                if (i<stations.size()-1) {
                    jsonRequest = jsonRequest+",";
                }
                candidateStations.add(stations.get(i));
            }
        }
        jsonRequest = jsonRequest+"]";

        JsonReader reader = Json.createReader(new StringReader(jsonRequest));
        JsonArray polylines = reader.readArray();
        reader.close();

        formData.putSingle("sr", this.wkid+"");
        formData.putSingle("polylines", polylines.toString());
        formData.putSingle("calculationType", "preserveShape");

        JsonArray response = wt.request(MediaType.APPLICATION_JSON).post(Entity.form(formData), JsonObject.class)
                .getJsonArray("lengths");

        threeStations.putAll(researchThreeLengthsMin(response, candidateStations));

        return threeStations;
    }

    private Map<Station, Double> researchThreeLengthsMin (JsonArray response, List<Station> candidateStations) {

        // Liste contenant toutes les stations
            ArrayList<Double> stationList = new ArrayList<Double>();
        // Liste des trois stations avec leur distance
            Map<Station, Double> threeStations = new HashMap<>();
        // Tampons contenant l'indice et la distance minimale
            Double distanceMin;
            int indexMin = 0;

        // Récupération de toutes les stations
        for (int i=0; i<response.size(); i++) {
            stationList.add(Double.parseDouble(response.get(i).toString()));
        }

        for (int j=0; j<3; j++) {
            distanceMin = stationList.get(0);
            for (int i=1; i<stationList.size(); i++) {
                if (stationList.get(i) < distanceMin) {
                    distanceMin = stationList.get(i);
                    indexMin = i;
                }
            }
            threeStations.put(candidateStations.get(indexMin), distanceMin);
            stationList.remove(indexMin);
            candidateStations.remove(indexMin);
        }

        return threeStations;
    }
}
