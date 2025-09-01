package endpoints;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import http.HttpContentType;
import http.HttpRequest;
import http.HttpResponse;
import http.HttpStatus;
import utils.Config;

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
        try {
            Path baseDir = Paths.get(Config.baseDirectory);
            String filePath = baseDir.resolve(req.getEndpointContents()).toString();
            String fileContent = Files.readString(Paths.get(filePath));
            return new HttpResponse(HttpStatus.OK, this.getContentType(), fileContent);
        } catch (Exception e) {
            return new HttpResponse(HttpStatus.NOT_FOUND, this.getContentType(),null);
        }
    }

    private HttpResponse handlePostRequest(HttpRequest req) {
        try {
            Path fileDir = Paths.get(Config.baseDirectory, req.getEndpointContents());
            Files.createDirectories(fileDir.getParent());
            Files.writeString(fileDir, req.getBody());
            return new HttpResponse(HttpStatus.CREATED, this.getContentType(), null);
        } catch (Exception e) {
            return new HttpResponse(HttpStatus.INTERNAL_SERVER_ERROR, this.getContentType(),null);
        }
    }
}
