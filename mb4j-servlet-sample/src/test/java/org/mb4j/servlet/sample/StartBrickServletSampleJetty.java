package org.mb4j.servlet.sample;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class StartBrickServletSampleJetty {
  public static void main(String[] args) throws Exception {
    final int PORT = 8180;
    final String CONTEXT_PATH = "/brickSample";
    // Server
    Server server = new Server(PORT);
    // WebAppContext
    WebAppContext webAppContext = new WebAppContext();
    webAppContext.setServer(server);
    webAppContext.setContextPath(CONTEXT_PATH);
    webAppContext.setWar("src/main/webapp");
    webAppContext.getSessionHandler().getSessionManager().setSessionIdPathParameterName("none");
    server.setHandler(webAppContext);
    // starting...
    System.out.println("---- starting jetty ...");
    server.start();
    System.out.println(""
        + "\n---- starting jetty ... ok"
        + "\n----"
        + "\n----     http://localhost:" + PORT + CONTEXT_PATH
        + "\n----"
        + "\n---- press ENTER to stop jetty");
    System.in.read();
    // stopping ...
    System.out.println("---- stopping jetty ...");
    server.stop();
    server.join();
    System.out.println("---- stopping jetty ... ok");
  }
}