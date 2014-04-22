package org.mb4j.servlet;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mb4j.brick.renderer.BrickRenderer;
import static org.mb4j.brick.template.TemplateUtils.outputEncodingStringOf;
import org.mb4j.controller.ControllerRequest;
import org.mb4j.controller.ControllerResponse;
import org.mb4j.controller.FormActionResponse;
import org.mb4j.controller.PageResponse;
import org.mb4j.controller.http.HttpFilter;
import org.mb4j.controller.http.HttpNamedParams;
import org.mb4j.controller.http.UrlPathStringToHome;
import org.mb4j.controller.mapping.ControllerMappings;
import org.mb4j.controller.mapping.UrlPath2ControllerResolver;
import org.mb4j.controller.url.ControllerUrl;
import org.mb4j.controller.url.ControllerUrl4Request;
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
    ControllerUrl url = ControllerUrl.of(resolvedView.controller.getClass(), UrlParams.of(
        resolvedView.paramsPath,
        HttpNamedParams.namedParamsFrom(httpReq)));
    String path2home = UrlPathStringToHome.from(servletPath);
    ControllerRequest viewReq = createViewRequest(path2home, url);
    ControllerResponse viewResp = resolvedView.controller.handle(viewReq);
    handle(viewReq, viewResp, httpResp);
  }

  private ControllerRequest createViewRequest(String path2home, ControllerUrl url) {
    return new ControllerRequest(
        url,
        new ServletStaticResourceUrlResolver(path2home),
        new ServletViewUrlStringResolver(path2home, views.controllerClass2UrlPathResolver()),
        ServletFormFieldNameResolver.INSTANCE);
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
