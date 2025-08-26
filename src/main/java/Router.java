import java.util.LinkedHashMap;
import java.util.Map;

public class Router {
    private final Map<String, Endpoint> routes = new LinkedHashMap<>() {{
        put("/echo", new EchoEndpoint());
        put("/", new RootEndpoint());
    }};


    public HttpResponse route(HttpRequest req) {
        System.out.println("Routing request to " + req.getEndpointName());
        for (var entry : routes.entrySet()) {
            if (req.getEndpointName().equals(entry.getKey())) {
                System.out.println("Trying route " + entry.getKey());
                return entry.getValue().handle(req);
            }
        }
        return new HttpResponse(HttpStatus.NOT_FOUND, null);
    }
}
