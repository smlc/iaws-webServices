package api;

import domain.Coordonne;
import domain.Station;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by lova on 13/04/16.
 */
public class ApiArcGISTest {

    @Test
    public void testIsConnected() throws Exception {

        //Given
        ApiArcGIS apiArcGIS = new ApiArcGIS();

        //Then
        assertTrue(apiArcGIS.isConnected());
    }

    @Test
    public void testGetDistance() throws Exception {

        //Given
        ApiArcGIS apiArcGIS = new ApiArcGIS();

        //When
        // Coordonnées de l'adresse de départ : 31, rue Valade, Toulouse, 31000
        Coordonne pointFrom = new Coordonne(43.6052081, 1.4348775);

        // Coordonnées de l'adresse d'arrivée : 4 Rue Albert Lautmann, Toulouse, 31000
        Coordonne pointTo = new Coordonne(43.607068, 1.439364);

        double distance = apiArcGIS.getDistance(pointFrom, pointTo);

        //Then
        assertEquals(distance, 0.41701632927358845, 30);
    }

    @Test
    public void testGetLengths() throws Exception {

        //Given
        ApiArcGIS apiArcGIS = new ApiArcGIS();

        // Coordonnées du client : 31, rue Valade, Toulouse, 31000
        Coordonne addressClient = new Coordonne(43.6052081, 1.4348775);

        // Liste des stations
        List<Station> stations = new ArrayList<>();
        Station s1 = new Station();
        Station s2 = new Station();
        Station s3 = new Station();
        Station s4 = new Station();
        Station s5 = new Station();
        Station s6 = new Station();
        Map<Object, Object> position = new HashMap();
        Map<Object, Object> position2 = new HashMap();
        Map<Object, Object> position3 = new HashMap();
        Map<Object, Object> position4 = new HashMap();
        Map<Object, Object> position5 = new HashMap();
        Map<Object, Object> position6 = new HashMap();
        List<Object> threeStations = new ArrayList<>();
        List<Object> threeStationsResultat = new ArrayList<>();

        position.put("lat", "43.608951960496405");
        position.put("lng", "1.441003598726198");
        s1.setName("s1");
        s1.setPosition(position);
        s1.setAvailable_bikes(6);
        s1.setDistance(apiArcGIS.getDistance(addressClient, new Coordonne(43.608951960496405, 1.441003598726198)));
        position2.put("lat", "43.59723540303583");
        position2.put("lng", "1.45907112459247");
        s2.setName("s2");
        s2.setPosition(position2);
        s2.setAvailable_bikes(0);
        s2.setDistance(apiArcGIS.getDistance(addressClient, new Coordonne(43.59723540303583, 1.45907112459247)));
        position3.put("lat", "43.571433064051334");
        position3.put("lng", "1.46278065695466");
        s3.setName("s3");
        s3.setPosition(position3);
        s3.setAvailable_bikes(9);
        s3.setDistance(apiArcGIS.getDistance(addressClient, new Coordonne(43.571433064051334, 1.46278065695466)));
        position4.put("lat", "43.597074846780274");
        position4.put("lng", "1.452605307400692");
        s4.setName("s4");
        s4.setPosition(position4);
        s4.setAvailable_bikes(5);
        s4.setDistance(apiArcGIS.getDistance(addressClient, new Coordonne(43.597074846780274, 1.452605307400692)));
        position5.put("lat", "43.60221990815785");
        position5.put("lng", "1.45195665819857");
        s5.setName("s5");
        s5.setPosition(position5);
        s5.setAvailable_bikes(4);
        s5.setDistance(apiArcGIS.getDistance(addressClient, new Coordonne(43.60221990815785, 1.45195665819857)));
        position6.put("lat", "43.59729443895585");
        position6.put("lng", "1.403872948950036");
        s6.setName("s6");
        s6.setPosition(position6);
        s6.setAvailable_bikes(4);
        s6.setDistance(apiArcGIS.getDistance(addressClient, new Coordonne(43.59729443895585, 1.403872948950036)));

        stations.add(s1);
        stations.add(s2);
        stations.add(s3);
        stations.add(s4);
        stations.add(s5);
        stations.add(s6);

        threeStations.add(s1);
        threeStations.add(s5);
        threeStations.add(s4);

        //When
        threeStationsResultat.addAll(apiArcGIS.getLengths (addressClient, stations, true));

        //Then
        assertEquals(threeStations, threeStationsResultat);
    }
}