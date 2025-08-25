import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    private ServerSocket serverSocket;

    public HttpServer(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
            serverSocket.setReuseAddress(true);
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }

    public void start() {
        try {
            Socket clientSocket = serverSocket.accept();
            HttpHandler handler = new HttpHandler(clientSocket);
            handler.handle();
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }
}
