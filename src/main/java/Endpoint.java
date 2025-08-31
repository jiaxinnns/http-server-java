public interface Endpoint {
    HttpResponse handle(HttpRequest request);
    String getContentType();
}
