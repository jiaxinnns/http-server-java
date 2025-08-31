import java.nio.file.Files;
import java.nio.file.Paths;

public class FilesEndpoint implements Endpoint {
    @Override
    public HttpResponse handle(HttpRequest req) {
        String filePath = System.getProperty("user.dir") + req.getEndpointContents();
        System.out.println("Requested file path: " + filePath);
        try {
            String fileContent = Files.readString(Paths.get(filePath));
            return new HttpResponse(HttpStatus.OK, this.getContentType(), fileContent);
        } catch (Exception e) {
            return new HttpResponse(HttpStatus.NOT_FOUND, this.getContentType(),null);
        }
    }

    @Override
    public String getContentType() {
        return "application/octet-stream";
    }
}
