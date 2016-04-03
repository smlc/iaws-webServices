package groupId;

import api.ApiJCDecaux;
import domain.Station;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by mars on 19/03/16.
 */
public class ApiJCDecauxTest {

 /*   @Test
    public void isConnected() throws Exception {
        //Given
        ApiJCDecaux api = new ApiJCDecaux();

        //When
        assertTrue(api.isConnected());
    }
*/
    @Test
    public void getStation() throws Exception {
        //Given
        ApiJCDecaux api = new ApiJCDecaux();

        //When
        List<Station> stations = api.getStation("Toulouse");
        System.out.println(stations.get(1));
       assertNotSame(0,stations.size());
    }
}