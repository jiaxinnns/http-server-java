package endpoints;
import http.HttpContentType;
import http.HttpRequest;
import http.HttpResponse;
import http.HttpStatus;

public class RootEndpoint implements Endpoint {
    @Override
    public HttpResponse handle(HttpRequest request) {
        return new HttpResponse(HttpStatus.OK, this.getContentType(), null);
    }

    @Override
    public HttpContentType getContentType() {
        return HttpContentType.TEXT_PLAIN;
    }
}