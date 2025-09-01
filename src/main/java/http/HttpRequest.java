package http;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
    private final String method;
    private final String path;  
    private final String httpVersion;
    private Map<String, String> headers = new HashMap<>();
    private String body = null;

    public HttpRequest(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        // Consume request line
        String requestLine = reader.readLine();
        if (requestLine == null || requestLine.isEmpty()) {
            throw new IOException("Empty request");
        }

        String[] parts = requestLine.split(" ");
        this.method = parts[0];  // e.g. "GET"
        this.path = parts[1];    // e.g. "/hello"
        this.httpVersion = parts[2]; // e.g. "HTTP/1.1"

        // Consume headers
        String line;
        while ((line = reader.readLine()) != null && !line.isEmpty()) {
            headers.put(line.split(": ")[0], line.split(": ")[1]);
        }

        // Consume body
        String contentLength = getContentLength();
        if (contentLength != null) {
            int length = Integer.parseInt(contentLength);
            char[] bodyChars = new char[length];
            reader.read(bodyChars, 0, length);
            this.body = new String(bodyChars, 0, bodyChars.length);
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

    public String getEndpointContents() {
        // Returns the part after the endpoint name
        String[] parts = path.split("/", 3);
        return parts.length > 2 ? parts[2] : null;
    }

    public String getMethod() {
        return method;
    }

    public String getHttpVersion() {
        return httpVersion; 
    }

    public String getUserAgent() {
        for (var entry : headers.entrySet()) {
            if (entry.getKey().equals("User-Agent")) {
                return entry.getValue();
            }
        }

        return null;
    }

    public String getContentLength() {
        for (var entry : headers.entrySet()) {
            if (entry.getKey().equals("Content-Length")) {
                return entry.getValue();
            }
        }

        return null;
    }

    public String getBody() {
        return body;
    }
}
