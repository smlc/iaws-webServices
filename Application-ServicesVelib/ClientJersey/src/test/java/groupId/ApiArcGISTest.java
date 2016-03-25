package groupId;

import static org.junit.Assert.*;

import domain.ApiArcGIS;
import org.junit.Assert;
import org.junit.Test;

import javax.json.*;
import java.awt.*;
import java.io.StringReader;

/**
 * Created by lova on 23/03/16.
 */
public class ApiArcGISTest {

    @Test
    public void isConnected() throws Exception {
        //Given
        ApiArcGIS api = new ApiArcGIS();

        Assert.assertTrue(api.isConnected());
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
        double distance = api.getDistance(4326, pointFrom, pointTo);

        Assert.assertEquals(0, distance, 0);
    }
}