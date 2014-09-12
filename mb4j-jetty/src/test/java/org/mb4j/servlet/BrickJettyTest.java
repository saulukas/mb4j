package org.mb4j.servlet;

import static org.mb4j.component.url.UrlPathString.urlPathOf;
import org.mb4j.component.Controller;
import org.mb4j.component.Request;
import org.mb4j.component.Response;
import static org.mb4j.component.viewmap.ViewMapBuilder.routeDefaultHomeTo;
import static org.mb4j.servlet.BrickJetty.brickJetty;

public class BrickJettyTest {
  public static void main(String[] args) {
    brickJetty(routeDefaultHomeTo(new Controller() {
      @Override
      public void handle(Request request, Response response) {
        response.getWriter();
      }
    }).route(urlPathOf("go/*"), new Service() {
      @Override
      protected void serve(Request request, Response response) throws Exception {
        response.getWriter().write("chielou-go: " + request.viewUrl());
      }
    })
    ).port(6789).contextPath("/mb4j").start();
  }
}
