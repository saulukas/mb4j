package org.mb4j.servlet;

import com.google.common.base.Optional;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mb4j.brick.renderer.BrickRenderer;
import static org.mb4j.brick.template.TemplateUtils.outputEncodingStringOf;
import org.mb4j.controller.ControllerRequest;
import org.mb4j.controller.form.FormResponse;
import org.mb4j.controller.form.FormResponseRedirectToController;
import org.mb4j.controller.form.FormResponseRedirectToUrlString;
import static org.mb4j.controller.form.FormResponseRedirectToUrlString.redirectTo;
import org.mb4j.controller.form.FormResponseRenderCurrentPage;
import org.mb4j.controller.form.FormSubmitHandler;
import org.mb4j.controller.page.Page;
import org.mb4j.controller.page.PageResponse;
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
import static org.mb4j.controller.utils.HttpNamedParams.namedParametersFromRawQueryString;

public class BrickServletFilter extends HttpFilter {
  private final BrickRenderer renderer;
  private final SiteMap siteMap;

  public BrickServletFilter(BrickRenderer renderer, SiteMap siteMap) {
    this.renderer = renderer;
    this.siteMap = siteMap;
  }

  @Override
  protected void filter(HttpServletRequest httpReq, HttpServletResponse httpResp, FilterChain chain)
      throws IOException, ServletException {
    //
    //   find mapped controller if any
    //   -----------------------------
    //
    System.out.println("HTTP method: " + httpReq.getMethod());
    String servletPath = httpReq.getServletPath();
    UrlPath path = UrlPathString.urlPathOf(servletPath);
    MapUrlPath2Controller.Result resolved = siteMap.urlPath2Controller().controllerFor(path);
    if (resolved.resultIsEmpty()) {
      chain.doFilter(httpReq, httpResp);
      return;
    }
    //
    //   handle FormAction if any
    //   ------------------------
    //
    ControllerRequest request = createRequest(servletPath, resolved, httpReq);
    NamedParams postParams = namedParametersFromRawQueryString(httpReq.getReader().readLine());
    Optional<FormResponse> optionalFormResponse = FormSubmitHandler.formResponseFor(
        request, postParams, siteMap);
    if (optionalFormResponse.isPresent()) {
      FormResponse formResponse = optionalFormResponse.get();
      if (formResponse instanceof FormResponseRedirectToController) {
        ControllerUrl controllerUrl = ((FormResponseRedirectToController) formResponse).controllerUrl;
        formResponse = redirectTo(request.resolve(controllerUrl));
      }
      if (formResponse instanceof FormResponseRedirectToUrlString) {
        String urlString = ((FormResponseRedirectToUrlString) formResponse).urlString;
        httpResp.sendRedirect(urlString);
        return;
      }
      if (formResponse instanceof FormResponseRenderCurrentPage) {
        if (!(resolved.controller instanceof Page)) {
          throw new RuntimeException("Received " + FormResponseRenderCurrentPage.class.getSimpleName()
              + " and current controller must be " + Page.class.getSimpleName() + " but found "
              + resolved.controller + ".");
        }
        FormResponseRenderCurrentPage responseWithAttributes = (FormResponseRenderCurrentPage) formResponse;
        request.attributes().putAll(responseWithAttributes.attributes.asMap());
      }
    }
    //
    //   handle Page response
    //   --------------------
    //
    Page page = (Page) resolved.controller;
    PageResponse pageResponse = page.handle(request);
    httpResp.setCharacterEncoding(outputEncodingStringOf(pageResponse.brick.getClass()));
    renderer.render(pageResponse.brick, httpResp.getWriter());
  }

  private ControllerRequest createRequest(String servletPath, Result resolved, HttpServletRequest httpReq) {
    String rawQueryString = httpReq.getQueryString();
    String path2home = UrlPathStringToHome.from(servletPath);
    NamedParams queryParams = namedParametersFromRawQueryString(rawQueryString);
    ControllerUrl controllerUrl = ControllerUrl.of(
        resolved.controller.getClass(),
        UrlParams.of(resolved.paramsPath, queryParams));
    ControllerRequest request = ServletControllerRequest.of(
        controllerUrl,
        path2home,
        new ServletRequestAttributes(httpReq),
        siteMap);
    return request;
  }
}
