package endpoints;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import http.HttpContentType;
import http.HttpRequest;
import http.HttpResponse;
import http.HttpStatus;

public class FilesEndpoint implements Endpoint {
    @Override
    public HttpResponse handle(HttpRequest req) {
        if (req.getMethod().equals("GET")) {
            return handleGetRequest(req);
        }
        return handlePostRequest(req);
    }

    @Override
    public HttpContentType getContentType() {
        return HttpContentType.OCTET_STREAM;
    }

    private HttpResponse handleGetRequest(HttpRequest req) {
        String filePath = System.getProperty("user.dir") + req.getEndpointContents();
        try {
            String fileContent = Files.readString(Paths.get(filePath));
            return new HttpResponse(HttpStatus.OK, this.getContentType(), fileContent);
        } catch (Exception e) {
            return new HttpResponse(HttpStatus.NOT_FOUND, this.getContentType(),null);
        }
    }

    private HttpResponse handlePostRequest(HttpRequest req) {
        String filePath = System.getProperty("user.dir") + req.getEndpointContents();
        try {
            Files.writeString(Paths.get(filePath), req.getBody());
            return new HttpResponse(HttpStatus.CREATED, this.getContentType(), null);
        } catch (Exception e) {
            return new HttpResponse(HttpStatus.NOT_FOUND, this.getContentType(),null);
        }
    }
}
