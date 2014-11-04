package org.mb4j.servlet;

import java.util.EnumSet;
import javax.servlet.DispatcherType;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.mb4j.brick.renderer.BrickRenderer;
import org.mb4j.brick.renderer.RendererUtils;
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

    void doStart() throws Exception {
        long startNanos = System.nanoTime();
        Server server = createServer();
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

    Server createServer() {
        Server server = new Server(port);
        BrickServletFilter filter = new BrickServletFilter(renderer, routes);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath(contextPath);
        context.addFilter(new FilterHolder(filter), "/*", EnumSet.of(
                DispatcherType.INCLUDE,
                DispatcherType.REQUEST));
        server.setHandler(context);
        disableSendServerVersion(server);
        return server;
    }

    private void disableSendServerVersion(Server server) {
        for (Connector y : server.getConnectors()) {
            for (org.eclipse.jetty.server.ConnectionFactory x : y.getConnectionFactories()) {
                if (x instanceof HttpConnectionFactory) {
                    ((HttpConnectionFactory) x).getHttpConfiguration().setSendServerVersion(false);
                }
            }
        }
    }
}
