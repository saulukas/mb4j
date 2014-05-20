package org.mb4j.servlet;

import org.mb4j.servlet.adapters.ServletRequestAttributes;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mb4j.brick.renderer.BrickRenderer;
import org.mb4j.component.Component;
import org.mb4j.component.view.ViewRequest;
import org.mb4j.component.view.ViewResponse;
import org.mb4j.component.form.FormResponse;
import org.mb4j.component.form.FormResponseRedirectToUrlString;
import org.mb4j.component.form.FormResponseRenderCurrentPage;
import static org.mb4j.component.form.FormSubmitHandler.formResponseFor;
import org.mb4j.component.resource.Resources4ResponseResolver;
import org.mb4j.component.resource.Resources4ResponseResolver.ParamValue;
import org.mb4j.component.viewmap.MapUrlPath2Controller;
import org.mb4j.component.viewmap.MapUrlPath2Controller.Result;
import org.mb4j.component.viewmap.SiteMap;
import org.mb4j.component.view.ViewUrl;
import org.mb4j.component.url.NamedParams;
import org.mb4j.component.url.UrlParams;
import org.mb4j.component.url.UrlPath;
import org.mb4j.component.url.UrlPathString;
import org.mb4j.component.url.UrlPathStringToHome;
import org.mb4j.component.utils.HttpFilter;
import static org.mb4j.component.utils.HttpNamedParams.namedParamsFromRawQuery;

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
    //   handle component resource if any
    //   --------------------------------
    //
    NamedParams queryParams = namedParamsFromRawQuery(httpRequest.getQueryString());
    String resourceParam = queryParams.valueOrNullOf(Resources4ResponseResolver.RESOURCE_PARAM_NAME);
    if (resourceParam != null) {
      Resources4ResponseResolver.ParamValue value = ParamValue.from(resourceParam);
      Component componentWithResources
          = siteMap.componentWithResourcesName2Component().componentFor(value.componentName);
      ViewRequest request = createRequest(servletPath, queryParams, resolved, httpRequest);
      componentWithResources.serveResource(
          value.resourceName,
          request,
          new ServletControllerResponse(renderer, httpResponse)
      );
      return;
    }
    //
    //   handle FormAction if any
    //   ------------------------
    //
    if (Objects.equal(httpRequest.getMethod(), "POST")) {
      ViewRequest request = createRequest(servletPath, queryParams, resolved, httpRequest);
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
    ViewRequest request = createRequest(servletPath, queryParams, resolved, httpRequest);
    ViewResponse response = new ServletControllerResponse(renderer, httpResponse);
    resolved.controller.handle(request, response);
  }

  private ViewRequest createRequest(
      String servletPath,
      NamedParams queryParams,
      Result resolved,
      HttpServletRequest httpRequest
  ) {
    String path2home = UrlPathStringToHome.from(servletPath);
    ViewUrl controllerUrl = ViewUrl.of(
        resolved.controller.getClass(),
        UrlParams.of(resolved.paramsPath, queryParams));
    ViewRequest request = ServletControllerRequest.of(
        controllerUrl,
        path2home,
        new ServletRequestAttributes(httpRequest),
        siteMap);
    return request;
  }
}
