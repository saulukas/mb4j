package org.mb4j.servlet;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mb4j.BrickRenderer;
import org.mb4j.http.HttpFilter;
import org.mb4j.http.HttpNamedParams;
import static org.mb4j.http.HttpPathToHome.pathStringToHomeFrom;
import static org.mb4j.template.TemplateUtils.outputEncodingStringOf;
import org.mb4j.view.ViewMap;
import org.mb4j.view.ViewParams;
import org.mb4j.view.ViewRequest;
import org.mb4j.view.ViewResponse;
import org.mb4j.view.mount.ViewFromPathResolver;
import org.mb4j.view.path.ViewPath;
import static org.mb4j.view.path.ViewPathString.viewPath;
import org.mb4j.view.url.ViewUrl;

public class BrickServletFilter extends HttpFilter {
  private final BrickRenderer renderer;
  private final ViewMap views;

  public BrickServletFilter(BrickRenderer renderer, ViewMap viewMap) {
    this.renderer = renderer;
    this.views = viewMap;
  }

  @Override
  protected void filter(HttpServletRequest httpReq, HttpServletResponse httpResp, FilterChain chain)
      throws IOException, ServletException {
    String servletPath = httpReq.getServletPath();
    ViewPath path = viewPath(httpReq.getServletPath());
    String path2home = pathStringToHomeFrom(servletPath);
    ViewFromPathResolver.Result resolvedView = views.viewFromPathResolver().resolve(path);
    if (!resolvedView.hasView()) {
      chain.doFilter(httpReq, httpResp);
      return;
    }
    ViewUrl url = ViewUrl.of(resolvedView.view.getClass(), ViewParams.of(
        resolvedView.paramsPath,
        HttpNamedParams.namedParamsFrom(httpReq)));
    ViewRequest viewReq = createViewRequest(path2home, url);
    ViewResponse viewResp = resolvedView.view.handle(viewReq);
    handle(viewReq, viewResp, httpResp);
  }

  private ViewRequest createViewRequest(String path2home, ViewUrl url) {
    return new ViewRequest(
        url,
        new ServletStaticResourceUrlResolver(path2home),
        new ServletViewUrlStringResolver(path2home, views.pathFromViewClassResolver()),
        ServletFormFieldNameResolver.INSTANCE);
  }

  private void handle(ViewRequest viewReq, ViewResponse viewResp, HttpServletResponse httpResp)
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
