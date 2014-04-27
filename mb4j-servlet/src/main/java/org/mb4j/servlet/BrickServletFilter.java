package org.mb4j.servlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mb4j.brick.renderer.BrickRenderer;
import static org.mb4j.brick.template.TemplateUtils.outputEncodingStringOf;
import org.mb4j.controller.ControllerRequest;
import org.mb4j.controller.ControllerResponse;
import org.mb4j.controller.form.FormActionResponse;
import org.mb4j.controller.http.HttpFilter;
import static org.mb4j.controller.http.HttpNamedParams.namedParametersFromRawQueryString;
import org.mb4j.controller.http.UrlPathStringToHome;
import org.mb4j.controller.mapping.ControllerMappings;
import org.mb4j.controller.mapping.UrlPath2ControllerResolver;
import org.mb4j.controller.page.PageResponse;
import org.mb4j.controller.url.ControllerUrl;
import org.mb4j.controller.url.ControllerUrl4Request;
import org.mb4j.controller.url.NamedParams;
import org.mb4j.controller.url.UrlParams;
import org.mb4j.controller.url.UrlPath;
import org.mb4j.controller.url.UrlPathString;

public class BrickServletFilter extends HttpFilter {
  private final BrickRenderer renderer;
  private final ControllerMappings views;

  public BrickServletFilter(BrickRenderer renderer, ControllerMappings viewMap) {
    this.renderer = renderer;
    this.views = viewMap;
  }

  @Override
  protected void filter(HttpServletRequest httpReq, HttpServletResponse httpResp, FilterChain chain)
      throws IOException, ServletException {
    String servletPath = httpReq.getServletPath();
    UrlPath path = UrlPathString.urlPathOf(servletPath);
    UrlPath2ControllerResolver.Result resolvedView = views.urlPath2ControllerResolver().resolve(path);
    if (!resolvedView.hasController()) {
      chain.doFilter(httpReq, httpResp);
      return;
    }
    String path2home = UrlPathStringToHome.from(servletPath);
    NamedParams queryParams = namedParametersFromRawQueryString(httpReq.getQueryString());
    NamedParams postParams = namedParametersFromRawQueryString(httpReq.getReader().readLine());
    ControllerUrl url = ControllerUrl.of(
        resolvedView.controller.getClass(),
        UrlParams.of(resolvedView.paramsPath, queryParams));
    ControllerRequest viewReq = new ServletControllerRequest(
        path2home,
        url,
        postParams,
        views.controllerClass2UrlPathResolver(),
        views.formClass2NameResolver());
    if (postParams.names().contains(ServletFormHeaderBrick.FORM_MARKER_PARAM)) {
      String actionMappedName = null;
      for (String paramName : postParams.names()) {
        if (paramName.startsWith(ServletFormData4RequestResolver.ACTION_NAME_PREFIX)) {
          actionMappedName = paramName.substring(ServletFormData4RequestResolver.ACTION_NAME_PREFIX.length());
          break;
        }
      }
      System.out.println("Form action '" + actionMappedName + "' :" + postParams);
    }
    ControllerResponse viewResp = resolvedView.controller.handle(viewReq);
    handle(viewReq, viewResp, httpResp);
  }

  public static NamedParams namedParamsFrom(HttpServletRequest req) {
    Map<String, String> name2value = new HashMap<>();
    Enumeration<String> names = req.getParameterNames();
    while (names.hasMoreElements()) {
      String name = names.nextElement();
      String value = req.getParameter(name);
      name2value.put(name, value);
    }
    return new NamedParams(name2value);
  }

  private void handle(ControllerRequest viewReq, ControllerResponse viewResp, HttpServletResponse httpResp)
      throws IOException {
    if (viewResp instanceof PageResponse) {
      PageResponse pageResponse = (PageResponse) viewResp;
      httpResp.setCharacterEncoding(outputEncodingStringOf(pageResponse.brick.getClass()));
      renderer.render(pageResponse.brick, httpResp.getWriter());
      return;
    }
    if (viewResp instanceof FormActionResponse) {
      FormActionResponse formActionResponse = (FormActionResponse) viewResp;
      ControllerUrl4Request location = viewReq.resolve(formActionResponse.redirectToControllerUrl);
      httpResp.sendRedirect(location.toString());
      return;
    }
    throw new RuntimeException("Unsupported " + ControllerResponse.class.getSimpleName()
        + ": " + viewResp);
  }
}
