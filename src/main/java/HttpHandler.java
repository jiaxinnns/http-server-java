import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class HttpHandler {
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private final Router router = new Router();

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
        try {
            HttpRequest request = new HttpRequest(this.inputStream);

            HttpResponse response = this.router.route(request);
            byte[] responseBytes = response.toBytes();

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
