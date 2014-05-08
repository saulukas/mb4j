package org.mb4j.servlet;

import com.google.common.base.Objects;
import com.google.common.base.Optional;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mb4j.brick.renderer.BrickRenderer;
import org.mb4j.controller.Request;
import org.mb4j.controller.form.FormResponse;
import org.mb4j.controller.form.FormResponseRedirectToUrlString;
import org.mb4j.controller.form.FormResponseRenderCurrentPage;
import static org.mb4j.controller.form.FormSubmitHandler.formResponseFor;
import org.mb4j.controller.sitemap.MapUrlPath2Controller;
import org.mb4j.controller.sitemap.MapUrlPath2Controller.Result;
import org.mb4j.controller.sitemap.SiteMap;
import org.mb4j.controller.url.ControllerUrl;
import org.mb4j.controller.url.NamedParams;
import org.mb4j.controller.url.UrlParams;
import org.mb4j.controller.url.UrlPath;
import org.mb4j.controller.url.UrlPathString;
import org.mb4j.controller.url.UrlPathStringToHome;
import org.mb4j.controller.utils.HttpFilter;
import static org.mb4j.controller.utils.HttpNamedParams.namedParamsFromRawQuery;

public class BrickServletFilter extends HttpFilter {
  private final BrickRenderer renderer;
  private final SiteMap siteMap;

  public BrickServletFilter(BrickRenderer renderer, SiteMap siteMap) {
    this.renderer = renderer;
    this.siteMap = siteMap;
  }

  @Override
  protected void filter(
      HttpServletRequest httpRequest,
      HttpServletResponse httpResponse,
      FilterChain chain
  ) throws IOException, ServletException {
    //
    //   find mapped controller if any
    //   -----------------------------
    //
    String servletPath = httpRequest.getServletPath();
    UrlPath path = UrlPathString.urlPathOf(servletPath);
    MapUrlPath2Controller.Result resolved = siteMap.urlPath2Controller().controllerFor(path);
    if (resolved.resultIsEmpty()) {
      chain.doFilter(httpRequest, httpResponse);
      return;
    }
    //
    //   handle FormAction if any
    //   ------------------------
    //
    if (Objects.equal(httpRequest.getMethod(), "POST")) {
      Request request = createRequest(servletPath, resolved, httpRequest);
      NamedParams postParams = namedParamsFromRawQuery(httpRequest.getReader().readLine());
      Optional<FormResponse> formRC = formResponseFor(request, postParams, siteMap.formName2Form());
      if (formRC.isPresent()) {
        FormResponse formResponse = formRC.get();
        if (formResponse instanceof FormResponseRedirectToUrlString) {
          httpResponse.sendRedirect(((FormResponseRedirectToUrlString) formResponse).urlString);
          return;
        }
        if (!(formResponse instanceof FormResponseRenderCurrentPage)) {
          throw new RuntimeException("Unsupported Form response: " + formResponse);
        }
      }
    }
    //
    //   handle Controller response
    //   --------------------------
    //
    Request request = createRequest(servletPath, resolved, httpRequest);
    resolved.controller.handle(request, new ServletControllerResponse(renderer, httpResponse));
  }

  private Request createRequest(String servletPath, Result resolved, HttpServletRequest httpRequest) {
    String rawQueryString = httpRequest.getQueryString();
    String path2home = UrlPathStringToHome.from(servletPath);
    NamedParams queryParams = namedParamsFromRawQuery(rawQueryString);
    ControllerUrl controllerUrl = ControllerUrl.of(
        resolved.controller.getClass(),
        UrlParams.of(resolved.paramsPath, queryParams));
    Request request = ServletControllerRequest.of(
        controllerUrl,
        path2home,
        new ServletRequestAttributes(httpRequest),
        siteMap);
    return request;
  }
}
