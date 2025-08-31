import java.nio.charset.StandardCharsets;

public class HttpResponse {
    private final HttpStatus status;
    private String contentType;
    private String body;

    public HttpResponse(HttpStatus status, String contentType, String body) {
        this.status = status;
        this.contentType = contentType;
        this.body = body;
    }

    public byte[] toBytes() {
        StringBuilder response = new StringBuilder();

        // Status line
        response.append("HTTP/1.1 ")
                .append(status.getCode())
                .append(" ")
                .append(status.getReason())
                .append("\r\n");

        // Headers
        response.append("Content-Type: ");
        response.append(this.contentType).append("\r\n");
        response.append("Content-Length: ")
                .append(String.valueOf(this.body != null ? this.body.getBytes(StandardCharsets.UTF_8).length : 0))
                .append("\r\n");
        response.append("\r\n"); // End of headers

        // Body
        if (this.body != null) response.append(this.body);

        return response.toString().getBytes(StandardCharsets.UTF_8);
    }
}
