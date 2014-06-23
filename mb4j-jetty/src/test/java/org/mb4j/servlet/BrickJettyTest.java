package org.mb4j.servlet;

import static org.mb4j.component.url.UrlPathString.urlPathOf;
import org.mb4j.component.view.View;
import org.mb4j.component.view.ViewRequest;
import org.mb4j.component.view.ViewResponse;
import static org.mb4j.component.viewmap.ViewMapBuilder.routeDefaultHomeTo;
import static org.mb4j.servlet.BrickJetty.brickJetty;

public class BrickJettyTest {
  public static void main(String[] args) {
    brickJetty(routeDefaultHomeTo(new View() {
      @Override
      public void handle(ViewRequest request, ViewResponse response) {
        response.getWriter();
      }
    }).route(urlPathOf("go/*"), new Service() {
      @Override
      protected void serve(ViewRequest request, ViewResponse response) throws Exception {
        response.getWriter().write("chielou-go: " + request.viewUrl());
      }
    })
    ).port(6789).contextPath("/mb4j").start();
  }
}
