package http;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {
    private ServerSocket serverSocket;
    private final ExecutorService executor = Executors.newFixedThreadPool(10);

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
            while (true) {
                Socket clientSocket = serverSocket.accept();
                HttpClientHandler handler = new HttpClientHandler(clientSocket);
                executor.submit(handler::handle);
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }
}
