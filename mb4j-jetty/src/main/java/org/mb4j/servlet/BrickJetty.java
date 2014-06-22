package org.mb4j.servlet;

import java.io.IOException;
import java.util.EnumSet;
import javax.servlet.DispatcherType;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.mb4j.brick.renderer.BrickRenderer;
import org.mb4j.brick.renderer.RendererUtils;
import org.mb4j.component.utils.HttpFilter;
import org.mb4j.component.viewmap.ViewMap;
import org.mb4j.component.viewmap.ViewMapBuilder;

public class BrickJetty {
  final ViewMap routes;
  BrickRenderer renderer = RendererUtils.renderer4Development();
  int port = 8080;
  String contextPath = "/";

  private BrickJetty(ViewMap routes) {
    this.routes = routes;
  }

  public static BrickJetty brickJetty(ViewMap routes) {
    return new BrickJetty(routes);
  }

  public static BrickJetty brickJetty(ViewMapBuilder routesBuilder) {
    return brickJetty(new ViewMap(routesBuilder));
  }

  public BrickJetty port(int port) {
    this.port = port;
    return this;
  }

  public BrickJetty contextPath(String contextPath) {
    this.contextPath = contextPath;
    return this;
  }

  public void start() {
    try {
      doStart();
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }

  public static class MyFilter extends HttpFilter {
    @Override
    protected void filter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
      resp.getWriter().write("ma filte :)");
    }
  }

  void doStart() throws Exception {
    long startNanos = System.nanoTime();
    // Server
    Server server = new Server(port);
    // Brick filter
    BrickServletFilter filter = new BrickServletFilter(renderer, routes);
    // Servlet context
    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
    context.setContextPath(contextPath);
    context.addFilter(new FilterHolder(new MyFilter()), "/*", EnumSet.of(
        DispatcherType.INCLUDE,
        DispatcherType.REQUEST));
    server.setHandler(context);
    // starting...
    System.out.println("---- starting jetty ...");
    server.start();
    long deltaMillis = (System.nanoTime() - startNanos) / 1000 / 1000;
    System.out.println(""
        + "\n---- starting jetty ... ok (" + deltaMillis + " ms)"
        + "\n----"
        + "\n----     http://localhost:" + port + contextPath
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
