package endpoints;
import http.HttpRequest;
import http.HttpResponse;
import http.HttpStatus;

public class RootEndpoint implements Endpoint {
    @Override
    public HttpResponse handle(HttpRequest request) {
        return new HttpResponse(HttpStatus.OK, this.getContentType(), null);
    }

    @Override
    public String getContentType() {
        return "text/plain";
    }
}