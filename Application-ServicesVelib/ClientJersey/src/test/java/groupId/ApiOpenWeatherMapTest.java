package groupId;

import api.ApiOpenWeatherMap;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by lova on 29/03/16.
 */
public class ApiOpenWeatherMapTest {

    @Test
    public void isConnected() throws Exception {
        //Given
        ApiOpenWeatherMap api = new ApiOpenWeatherMap();

        assertTrue(api.isConnected());
    }

    @Test
    public void getMeteo () {
        //Given
        ApiOpenWeatherMap api = new ApiOpenWeatherMap();

        //When
        Double pluviometrie = api.getMeteo();

        assertEquals(1.58, pluviometrie, 2);
    }
}