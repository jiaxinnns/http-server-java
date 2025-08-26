public class EchoEndpoint implements Endpoint {
    @Override
    public HttpResponse handle(HttpRequest request) {
        String path = request.getPath();
        String[] parts = path.split("/", 3);
        String body = parts.length > 2 ? parts[2] : null;
        return new HttpResponse(HttpStatus.OK, body);
    }
    
}
