import java.nio.charset.StandardCharsets;

public class HttpResponse {
    private final HttpStatus status;

    public HttpResponse(HttpStatus status) {
        this.status = status;
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
        response.append("\r\n"); // End of headers

        return response.toString().getBytes(StandardCharsets.UTF_8);
    }
}
