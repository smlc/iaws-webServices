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
    private static String UriApi = "http://sampleserver6.arcgisonline.com/arcgis/rest/services/Utilities/Geometry/GeometryServer?f=json";

    public ApiArcGIS () {
        wkid = 4326;
        wt = ClientBuilder.newClient().register(new ClientResponseFilter() {

            public void filter(ClientRequestContext clientRequestContext, ClientResponseContext responseContext) throws IOException {
                java.util.List<String> contentType = new ArrayList(1);
                contentType.add(MediaType.APPLICATION_JSON);
                responseContext.getHeaders().put("Content-Type", contentType);
            }
        }).target(UriApi);

    }

    public boolean isConnected () {
        if(wt.request().head().getStatus()==200)
            return true;

        return false;
    }

    public double getDistance (Coordonne pointFrom, Coordonne pointTo) {

        MultivaluedMap<String, String> formData = new MultivaluedHashMap<String, String>();

        JsonObject jSongeometry1 = Json.createObjectBuilder()
                .add("geometryType", "esriGeometryPoint")
                .add("geometry", Json.createObjectBuilder()
                        .add("x", pointFrom.getLon())
                        .add("y", pointFrom.getLat()))
                .build();

        JsonObject jSongeometry2 = Json.createObjectBuilder()
                .add("geometryType", "esriGeometryPoint")
                .add("geometry", Json.createObjectBuilder()
                        .add("x", pointTo.getLon())
                        .add("y", pointTo.getLat()))
                .build();

        formData.putSingle("sr", this.wkid+"");
        formData.putSingle("geometry1", jSongeometry1.toString());
        formData.putSingle("geometry2", jSongeometry2.toString());
        formData.putSingle("geodesic", "true");
        formData.putSingle("distanceUnit","9036"); //9036 = Km

        JsonObject jSonObjectReponse = wt.path("distance").request(MediaType.APPLICATION_JSON).post(Entity.form(formData), JsonObject.class);
        System.out.println("ArcGis: "+jSonObjectReponse.getJsonNumber("distance"));
        return jSonObjectReponse.getJsonNumber("distance").doubleValue();
    }

    public List<Station> getLengths (Coordonne adressClient, List<Station> stations, boolean emptyOrFull) {

        MultivaluedMap<String, String> formData = new MultivaluedHashMap<String, String>();
        List<Station> candidateStations = new ArrayList<>();
        List<Station> threeStations = new ArrayList<>();

        String jsonRequest = "[";
        if (emptyOrFull) {
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
        } else {
            for (int i=0; i<stations.size(); i++) {
                if (stations.get(i).getAvailable_bike_stands() != 0) {
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
        }

        jsonRequest = jsonRequest+"]";

        JsonReader reader = Json.createReader(new StringReader(jsonRequest));
        JsonArray polylines = reader.readArray();
        reader.close();

        formData.putSingle("sr", this.wkid+"");
        formData.putSingle("polylines", polylines.toString());
        formData.putSingle("calculationType", "preserveShape");

        JsonArray response = wt.path("lengths").request(MediaType.APPLICATION_JSON).post(Entity.form(formData), JsonObject.class)
                .getJsonArray("lengths");

        threeStations.addAll(researchThreeLengthsMin(response, candidateStations));

        return threeStations;
    }

    private List<Station> researchThreeLengthsMin (JsonArray response, List<Station> candidateStations) {

        // Liste contenant toutes les stations
            ArrayList<Double> stationList = new ArrayList<Double>();
        // Liste des trois stations avec leur distance
            List<Station> threeStations = new ArrayList<>();
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
            candidateStations.get(indexMin).setDistance(distanceMin);
            threeStations.add(candidateStations.get(indexMin));
            stationList.remove(indexMin);
            candidateStations.remove(indexMin);
        }

        return threeStations;
    }
}
