import http.HttpServer;
import utils.Config;

public class Main {
  private static final String DIRECTORY_FLAG = "--directory";
  public static void main(String[] args) {
    Config.baseDirectory = System.getProperty("user.dir"); 
    if (args.length > 1 && args[0].equals(DIRECTORY_FLAG)) {
        Config.baseDirectory += args[1];
    }

    HttpServer server = new HttpServer(4221);
    server.start();
  }
}
