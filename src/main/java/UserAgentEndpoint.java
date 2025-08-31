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
    public String getContentType() {
        return "text/plain";        
    }
}