package org.mb4j.servlet;

import org.mb4j.component.Request;
import org.mb4j.component.Response;
import org.mb4j.component.View;
import static org.mb4j.component.url.UrlPathString.urlPathOf;
import static org.mb4j.component.viewmap.ViewMapBuilder.routeDefaultHomeTo;
import static org.mb4j.servlet.BrickJetty.brickJetty;

public class BrickJettyTest {

    public static void main(String[] args) {
        brickJetty(routeDefaultHomeTo(new View() {
            @Override
            public void render(Request request, Response response) {
                response.write(""
                        + "<p>Hello MB on Jetty :)</p>"
                        + "<a href=\"go?aaa=111\">One more hello...</a>");
            }
        }).route(urlPathOf("go/*"), new Service() {
            @Override
            protected void serve(Request request, Response response) throws Exception {
                response.getWriter().write("chielou-go: " + request.viewLocator());
            }
        })
        ).port(6789).contextPath("/mb4j").start();
    }
}
