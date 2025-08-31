package endpoints;
import java.util.LinkedHashMap;
import java.util.Map;

import http.HttpContentType;
import http.HttpRequest;
import http.HttpResponse;
import http.HttpStatus;

public class Router {
    private final Map<String, Endpoint> routes = new LinkedHashMap<>() {{
        put("/files", new FilesEndpoint());
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
        return new HttpResponse(HttpStatus.NOT_FOUND, HttpContentType.TEXT_PLAIN, null);
    }
}
