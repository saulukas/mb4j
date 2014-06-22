package org.mb4j.example.servlet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class StartServletSampleJetty {
  public static void main(String[] args) throws Exception {
    final int PORT = 8180;
    final String CONTEXT_PATH = "/brickSample";
    long startNanos = System.nanoTime();
    // Server
    Server server = new Server(PORT);
    WebAppContext webAppContext = new WebAppContext();
    webAppContext.setServer(server);
    webAppContext.setContextPath(CONTEXT_PATH);
    webAppContext.setWar("src/main/webapp");
    webAppContext.getSessionHandler().getSessionManager().setSessionIdPathParameterName("none");
    server.setHandler(webAppContext);
    // starting...
    System.out.println("---- starting jetty ...");
    server.start();
    long deltaMillis = (System.nanoTime() - startNanos) / 1000 / 1000;
    System.out.println(""
        + "\n---- starting jetty ... ok (" + deltaMillis + " ms)"
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
