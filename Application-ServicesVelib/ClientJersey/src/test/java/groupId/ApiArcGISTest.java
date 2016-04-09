package groupId;


import api.ApiArcGIS;

import domain.Coordonne;
import domain.Station;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by lova on 23/03/16.
 */
public class ApiArcGISTest {

    @Test
    public void isConnected() throws Exception {
        //Given
        ApiArcGIS api = new ApiArcGIS();

        assertTrue(api.isConnected());

    }

    @Test
    public void getDistance () throws Exception {

        //Given
        ApiArcGIS api = new ApiArcGIS();
        Point pointFrom = new Point();
        pointFrom.setLocation(-115, 36);
        Point pointTo = new Point();
        pointTo.setLocation(-115, 36);

        //When
        double distance = api.getDistance(pointFrom, pointTo);

        //Then
        Assert.assertEquals(0, distance, 0);
    }

    @Test
    public void getLengths () throws Exception {

        //Given
       /* ApiArcGIS api = new ApiArcGIS();
        Coordonne adressClient = new Coordonne(43.6051567, 1.4373457);
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
        position2.put("lat", "43.59723540303583");
        position2.put("lng", "1.45907112459247");
        s2.setName("s2");
        s2.setPosition(position2);
        s2.setAvailable_bikes(0);
        position3.put("lat", "43.571433064051334");
        position3.put("lng", "1.46278065695466");
        s3.setName("s3");
        s3.setPosition(position3);
        s3.setAvailable_bikes(9);
        position4.put("lat", "43.597074846780274");
        position4.put("lng", "1.452605307400692");
        s4.setName("s4");
        s4.setPosition(position4);
        s4.setAvailable_bikes(5);
        position5.put("lat", "43.60221990815785");
        position5.put("lng", "1.45195665819857");
        s5.setName("s5");
        s5.setPosition(position5);
        s5.setAvailable_bikes(4);
        position6.put("lat", "43.59729443895585");
        position6.put("lng", "1.403872948950036");
        s6.setName("s6");
        s6.setPosition(position6);
        s6.setAvailable_bikes(4);

        stations.add(s1);
        stations.add(s2);
        stations.add(s3);
        stations.add(s4);
        stations.add(s5);
        stations.add(s6);

        threeStations.add(s1, 0.005301113355925021);
        threeStations.put(s5, 0.014995485792134916);
        threeStations.put(s4, 0.017303176346981187);

        //When
        threeStationsResultat.addAll(api.getLengths (adressClient, stations, true));

        //Then
        assertEquals(threeStations, threeStationsResultat);*/
    }
}