package endpoints;

import http.HttpRequest;
import http.HttpResponse;

public interface Endpoint {
    HttpResponse handle(HttpRequest request);
    String getContentType();
}
