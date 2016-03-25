import static org.junit.Assert.*;
import org.junit.Test;

import javax.ws.rs.core.MediaType;

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
    public float getDistance () {

        //Given
        ApiArcGIS api = new ApiArcGIS();

        //When

        return 0.f;
    }
}