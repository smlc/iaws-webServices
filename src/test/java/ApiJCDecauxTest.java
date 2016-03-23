import domain.Station;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mars on 19/03/16.
 */
public class ApiJCDecauxTest {

    @Test
    public void isConnected() throws Exception {
        //Given
        ApiJCDecaux api = new ApiJCDecaux();

        assertTrue(api.isConnected());
    }

    @Test
    public void getStation() throws Exception {
        //Given
        ApiJCDecaux api = new ApiJCDecaux();

        //When
        Station station = api.getStation("Toulouse");

        assertNull(station);
    }


}