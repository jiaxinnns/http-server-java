package endpoints;
import http.HttpContentType;
import http.HttpRequest;
import http.HttpResponse;
import http.HttpStatus;

class UserAgentEndpoint implements Endpoint {
    @Override
    public HttpResponse handle(HttpRequest req) {
        String userAgent = req.getUserAgent();
        if (userAgent == null) {
            return new HttpResponse(HttpStatus.NOT_FOUND, this.getContentType(), null);
        } 
        return new HttpResponse(HttpStatus.OK, this.getContentType(),userAgent);
    }

    @Override
    public HttpContentType getContentType() {
        return HttpContentType.TEXT_PLAIN;        
    }
}