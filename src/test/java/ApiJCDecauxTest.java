import domain.Station;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mars on 19/03/16.
 */
public class ApiJCDecauxTest {

    @Test
    public void isConected() throws Exception {
        //Given
        ApiJCDecaux api = new ApiJCDecaux();


        assertTrue(api.isConected());
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