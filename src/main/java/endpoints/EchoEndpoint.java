package endpoints;

import http.HttpRequest;
import http.HttpResponse;
import http.HttpStatus;

public class EchoEndpoint implements Endpoint {
    @Override
    public HttpResponse handle(HttpRequest request) {
        String body = request.getEndpointContents();
        return new HttpResponse(HttpStatus.OK, this.getContentType(),body);
    }
    
    @Override
    public String getContentType() {
        return "text/plain";
    }
}
