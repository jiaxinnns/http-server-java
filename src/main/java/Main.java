import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Main {
  public static void main(String[] args) {
    try {
      ServerSocket serverSocket = new ServerSocket(4221);
    
      // Since the tester restarts your program quite often, setting SO_REUSEADDR
      // ensures that we don't run into 'Address already in use' errors
      serverSocket.setReuseAddress(true);
    
      Socket socket = serverSocket.accept(); // Wait for connection from client.
      System.out.println("accepted new connection");

      InputStream inputStream = socket.getInputStream();

      String responseString = "HTTP/1.1 200 OK\r\n\r\n";
      byte[] responseBytes = responseString.getBytes(StandardCharsets.UTF_8);

      OutputStream outputStream = socket.getOutputStream(); 
      outputStream.write(responseBytes);
      outputStream.flush();

      inputStream.close();
      outputStream.close();
      socket.close();
      serverSocket.close();
    } catch (IOException e) {
      System.out.println("IOException: " + e.getMessage());
    }
  }
}
