import http.HttpServer;

public class Main {
  private static final String DIRECTORY_FLAG = "--directory";
  public static void main(String[] args) {
    if (args.length > 1 && args[0].equals(DIRECTORY_FLAG)) {
      System.setProperty("user.dir", args[1]);
    }

    HttpServer server = new HttpServer(4221);
    server.start();
  }
}
