package webservice.serviceuserstory3;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.test.server.MockWebServiceClient;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import static org.springframework.ws.test.server.RequestCreators.withPayload;
import static org.springframework.ws.test.server.ResponseMatchers.payload;
import static org.springframework.ws.test.server.ResponseMatchers.validPayload;

/**
 * Created by lova on 09/04/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("application-context.xml")
public class WsUserStoryThreeEndpointTest {

    @Autowired
    private ApplicationContext applicationContext;

    private MockWebServiceClient mockClient;

    @Before
    public void createClient() {
        mockClient = MockWebServiceClient.createClient(applicationContext);
    }

    @Test
    public void testGetTempsTrajet() throws Exception {
        Source requestPayload = new StreamSource(new ClassPathResource("/webservice/serviceuserstory3/Request.xml").getInputStream());
        Source responsePayload = new StreamSource(new ClassPathResource("/webservice/serviceuserstory3/Response.xml").getInputStream());

        Resource schemaXsd = new ClassPathResource("/webservice/serviceuserstory3/ResponseContract.xsd");
        mockClient.sendRequest(withPayload(requestPayload)).
                andExpect(validPayload(schemaXsd));
    }
}