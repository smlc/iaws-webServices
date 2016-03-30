package groupId;

import api.ApiOpenWeatherMap;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by lova on 29/03/16.
 */
public class ApiOpenWeatherMapTest {

    @Test
    public void isConnected() throws Exception {
        //Given
        ApiOpenWeatherMap api = new ApiOpenWeatherMap();

        Assert.assertTrue(api.isConnected());
    }

    @Test
    public void getMeteo () {
        //Given
        ApiOpenWeatherMap api = new ApiOpenWeatherMap();

        //When
        api.getMeteo();
    }
}