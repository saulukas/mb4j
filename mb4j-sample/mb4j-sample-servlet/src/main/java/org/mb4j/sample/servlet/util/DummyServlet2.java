package org.mb4j.sample.servlet.util;

import com.google.inject.Singleton;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Singleton
public class DummyServlet2 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("text/html;charset=utf-8");
    resp.getWriter().println(""
        + "<h1>Hello World 2</h1>"
        + "<strong>" + req.getPathInfo() + "</strong>"
        + "<p>" + req.getQueryString() + "</p>"
        + "<img src=\"../../img/glyphicons_254_fishes.png\"/>"
    );
  }
}
