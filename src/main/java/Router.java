import java.util.LinkedHashMap;
import java.util.Map;

public class Router {
    private final Map<String, Endpoint> routes = new LinkedHashMap<>() {{
        put("/user-agent", new UserAgentEndpoint());
        put("/echo", new EchoEndpoint());
        put("/", new RootEndpoint());
    }};


    public HttpResponse route(HttpRequest req) {
        for (var entry : routes.entrySet()) {
            if (req.getEndpointName().equals(entry.getKey())) {
                return entry.getValue().handle(req);
            }
        }

        System.out.println("No route found for " + req.getEndpointName());
        return new HttpResponse(HttpStatus.NOT_FOUND, null);
    }
}
