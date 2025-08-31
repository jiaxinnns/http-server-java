import http.HttpServer;

public class Main {
  public static void main(String[] args) {
    if (args.length > 1 && args[0].equals("--directory")) {
      System.setProperty("user.dir", args[1]);
    }

    HttpServer server = new HttpServer(4221);
    server.start();
  }
}
