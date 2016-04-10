package api;

import api.ApiOpenStreetMap;
import domain.Coordonne;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mars on 29/03/16.
 */
public class ApiOpenStreetMapTest {

    @Test
    public void getLatLong() throws Exception {

        //Given
        ApiOpenStreetMap apiOpenSM = new ApiOpenStreetMap();

        //When
        Coordonne p = apiOpenSM.getLatLong("31 rue valade, Toulouse, 31000");

        //Then
        assertEquals(new Coordonne(43.6051567, 1.4373457), p);
    }

    @Test
    public void isConnected() throws Exception {
        //Given
        ApiOpenStreetMap apiOpenSM = new ApiOpenStreetMap();

        //When
        assertTrue(apiOpenSM.isConnected());

    }
}