import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HttpRequest {
    private final String method;
    private final String path;  
    private final String httpVersion;

    public HttpRequest(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        // First line: "GET /something HTTP/1.1"
        String requestLine = reader.readLine();
        if (requestLine == null || requestLine.isEmpty()) {
            throw new IOException("Empty request");
        }

        String[] parts = requestLine.split(" ");
        this.method = parts[0];  // e.g. "GET"
        this.path = parts[1];    // e.g. "/hello"
        this.httpVersion = parts[2]; // e.g. "HTTP/1.1"

        // TODO: consume headers
        String line;
        while ((line = reader.readLine()) != null && !line.isEmpty()) {
            // You could store headers in a map here later
        }
    }

    public String getPath() {
        return path;
    } 
    
    public String getEndpointName() {
        String[] parts = path.split("/", 3);
        String rawName = parts.length > 1 ? parts[1] : "";
        return "/" + rawName;
    }

    public String getMethod() {
        return method;
    }

    public String getHttpVersion() {
        return httpVersion; 
    }
}
