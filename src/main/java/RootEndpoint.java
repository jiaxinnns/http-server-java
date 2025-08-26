public class RootEndpoint implements Endpoint {
    @Override
    public HttpResponse handle(HttpRequest request) {
        return new HttpResponse(HttpStatus.OK, null);
    }
}