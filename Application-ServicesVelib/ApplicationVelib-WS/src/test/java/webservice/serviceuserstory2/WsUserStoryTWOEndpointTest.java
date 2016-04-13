package webservice.serviceuserstory2;


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
 * Created by mars on 03/04/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/application-context.xml")
public class WsUserStoryTWOEndpointTest {

    @Autowired
    private ApplicationContext applicationContext;

    private MockWebServiceClient mockClient;

    @Before
    public void createClient() {
        mockClient = MockWebServiceClient.createClient(applicationContext);
    }

    @Test
    public void getInfoChausser() throws Exception {
        Source requestPayload = new StreamSource(new ClassPathResource("/webservice.serviceuserstory2/Request.xml").getInputStream());
        Source responsePayload = new StreamSource(new ClassPathResource("/webservice.serviceuserstory2/Reponse.xml").getInputStream());


        Resource schemaXsd = new ClassPathResource("/webservice.serviceuserstory2/ReponseContract.xsd");
        mockClient.sendRequest(withPayload(requestPayload)).
                andExpect(validPayload(schemaXsd));
/*
        mockClient.sendRequest(withPayload(requestPayload)).
                andExpect(payload(responsePayload));*/
    }
}