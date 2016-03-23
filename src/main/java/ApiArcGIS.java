import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.StringReader;

/**
 * Created by lova on 23/03/16.
 */
public class ApiArcGIS {

    Client client;
    WebTarget wt;
    JsonArray object;
    private static String UriApi = "http://sampleserver6.arcgisonline.com/arcgis/rest/services/Utilities/Geometry/GeometryServer/distance";

    public ApiArcGIS () {
        wt = ClientBuilder.newClient().target(UriApi);

        String jsonObjectString = wt.
                request(MediaType.APPLICATION_JSON).get(String.class);

        JsonReader jsonReader = Json.createReader(new StringReader(jsonObjectString));
        object = jsonReader.readArray();
        jsonReader.close();
    }

    public boolean isConnected () {
        return true;
    }

    public float getDistance () {
        System.out.println(wt.queryParam("sr",)
                .request(MediaType.APPLICATION_JSON).get(String.class));

        return 0.f;
    }
}
