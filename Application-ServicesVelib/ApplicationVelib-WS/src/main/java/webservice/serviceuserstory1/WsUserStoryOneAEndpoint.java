package webservice.serviceuserstory1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import services.Mediateur;

/**
 * Created by mars on 03/04/16.
 */

@Endpoint
public class WsUserStoryOneAEndpoint {

    private static final String NAMESPACE_URI = "http://iaws/ws/contractfirst/station";

    private Mediateur mediateuApi;

    @Autowired
    public WsUserStoryOneAEndpoint(Mediateur mediateurApi) {
        this.mediateuApi = mediateurApi;
    }


}
