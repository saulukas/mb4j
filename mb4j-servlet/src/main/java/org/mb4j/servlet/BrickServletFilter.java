package org.mb4j.servlet;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mb4j.brick.renderer.BrickRenderer;
import org.mb4j.controller.http.HttpFilter;
import org.mb4j.controller.http.HttpNamedParams;
import static org.mb4j.controller.http.HttpPathToHome.pathStringToHomeFrom;
import static org.mb4j.brick.template.TemplateUtils.outputEncodingStringOf;
import org.mb4j.controller.mapping.ControllerMappings;
import org.mb4j.controller.url.UrlParams;
import org.mb4j.controller.ControllerRequest;
import org.mb4j.controller.ViewResponse;
import org.mb4j.controller.mapping.UrlPath2ControllerResolver;
import org.mb4j.controller.url.UrlPath;
import static org.mb4j.controller.url.UrlPathString.urlPath;
import org.mb4j.controller.url.ControllerUrl;

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
    UrlPath path = urlPath(httpReq.getServletPath());
    String path2home = pathStringToHomeFrom(servletPath);
    UrlPath2ControllerResolver.Result resolvedView = views.urlPath2ControllerResolver().resolve(path);
    if (!resolvedView.hasController()) {
      chain.doFilter(httpReq, httpResp);
      return;
    }
    ControllerUrl url = ControllerUrl.of(resolvedView.controller.getClass(), UrlParams.of(
        resolvedView.paramsPath,
        HttpNamedParams.namedParamsFrom(httpReq)));
    ControllerRequest viewReq = createViewRequest(path2home, url);
    ViewResponse viewResp = resolvedView.controller.handle(viewReq);
    handle(viewReq, viewResp, httpResp);
  }

  private ControllerRequest createViewRequest(String path2home, ControllerUrl url) {
    return new ControllerRequest(
        url,
        new ServletStaticResourceUrlResolver(path2home),
        new ServletViewUrlStringResolver(path2home, views.controllerClass2UrlPathResolver()),
        ServletFormFieldNameResolver.INSTANCE);
  }

  private void handle(ControllerRequest viewReq, ViewResponse viewResp, HttpServletResponse httpResp)
      throws IOException {
    switch (viewResp.type) {
    case BRICK:
      httpResp.setCharacterEncoding(outputEncodingStringOf(viewResp.brick.getClass()));
      renderer.render(viewResp.brick, httpResp.getWriter());
      break;
    case REDIRECT_TO_VIEW:
      String location = viewReq.stringOf(viewResp.viewUrl);
      httpResp.sendRedirect(location);
      break;
    default:
      throw new RuntimeException("Unsupported " + ViewResponse.class.getSimpleName()
          + " type: " + viewResp.type);
    }
  }
}
