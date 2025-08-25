import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class HttpHandler {
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;

    public HttpHandler(Socket socket) { 
        this.socket = socket;
        try {
            this.inputStream = socket.getInputStream();
            this.outputStream = socket.getOutputStream();
        } catch (IOException e) {
            System.out.println("IO Exception: " + e.getMessage());
        }
    }

    public void handle() {
        // Get input, output
        // Build HttpResponse
        // Send it
        try {

            String responseString = "HTTP/1.1 200 OK\r\n\r\n";
            byte[] responseBytes = responseString.getBytes(StandardCharsets.UTF_8);

            this.outputStream.write(responseBytes);
            this.outputStream.flush();

            this.inputStream.close();
            this.outputStream.close();
            this.socket.close();
        } catch (IOException e) {
            System.out.println("IO Exception: " + e.getMessage());
        }
    }
}
