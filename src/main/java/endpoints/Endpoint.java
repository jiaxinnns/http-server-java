package endpoints;

import http.HttpContentType;
import http.HttpRequest;
import http.HttpResponse;

public interface Endpoint {
    HttpResponse handle(HttpRequest request);
    HttpContentType getContentType();
}
