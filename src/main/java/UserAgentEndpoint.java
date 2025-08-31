class UserAgentEndpoint implements Endpoint {
    @Override
    public HttpResponse handle(HttpRequest req) {
        String userAgent = req.getUserAgent();
        if (userAgent == null) {
            return new HttpResponse(HttpStatus.OK, "User-Agent header not found");
        } 
        return new HttpResponse(HttpStatus.OK, userAgent);
    }
}