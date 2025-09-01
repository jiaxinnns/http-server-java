package http;

public enum HttpContentType {
    TEXT_PLAIN("text/plain"),
    OCTET_STREAM("application/octet-stream");

    private final String value;

    HttpContentType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
