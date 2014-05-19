package org.mb4j.sample.servlet.util;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.inject.Singleton;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Singleton
public class DummyServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("text/html;charset=utf-8");
    ArrayList<String> paramNames = Lists.newArrayList(Iterators.forEnumeration(req.getParameterNames()));
    String paramValues = ""
        + "\n    paramValues: " + paramNames.size()
        + "\n";
    for (String paramName : paramNames) {
      paramValues += "\n        " + paramName + "=[" + req.getParameter(paramName) + "]";
    }
    resp.getWriter().println(""
        + "<h1>Hello World :)</h1>"
        + "\n<form method=\"POST\" action=\"\">"
        + "\n    <input type=\"text\" name=\"thing1\" value=\"value1\">"
        + "\n    <input type=\"submit\" name=\"actionSave\" value=\"Save\">"
        + "\n    <input type=\"submit\" name=\"actionCancel\" value=\"Cancel\">"
        + "\n</form>"
        + "\n<p>Path info: (<em>" + req.getPathInfo() + "</em>)</p>"
        + "\n<p>Query String: (<em>" + req.getQueryString() + "</em>)</p>"
        + "\n<pre>" + paramValues
        + "\n</pre>"
    );
  }
}
