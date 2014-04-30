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
import org.mb4j.controller.form.Form;
import org.mb4j.controller.form.FormResponse;
import org.mb4j.controller.form.field.FormFieldRecord;
import org.mb4j.controller.form.field.FormFieldValueTree;
import org.mb4j.controller.http.HttpFilter;
import static org.mb4j.controller.http.HttpNamedParams.namedParametersFromRawQueryString;
import org.mb4j.controller.http.UrlPathStringToHome;
import org.mb4j.controller.mapping.ControllerMappings;
import org.mb4j.controller.mapping.UrlPath2ControllerResolver;
import org.mb4j.controller.mapping.UrlPath2ControllerResolver.Result;
import org.mb4j.controller.page.PageResponse;
import org.mb4j.controller.url.ControllerUrl;
import org.mb4j.controller.url.NamedParams;
import org.mb4j.controller.url.UrlParams;
import org.mb4j.controller.url.UrlPath;
import org.mb4j.controller.url.UrlPathString;

public class BrickServletFilter extends HttpFilter {
  private final BrickRenderer renderer;
  private final ControllerMappings mappings;

  public BrickServletFilter(BrickRenderer renderer, ControllerMappings mappings) {
    this.renderer = renderer;
    this.mappings = mappings;
  }

  @Override
  protected void filter(HttpServletRequest httpReq, HttpServletResponse httpResp, FilterChain chain)
      throws IOException, ServletException {
    //
    //   find mapped controller if any
    //   -----------------------------
    //
    String servletPath = httpReq.getServletPath();
    UrlPath path = UrlPathString.urlPathOf(servletPath);
    UrlPath2ControllerResolver.Result resolved = mappings.urlPath2ControllerResolver().resolve(path);
    if (resolved.resultIsEmpty()) {
      chain.doFilter(httpReq, httpResp);
      return;
    }
    //
    //   handle FormAction if any
    //   ------------------------
    //
    ControllerRequest request = createRequest(servletPath, resolved, httpReq.getQueryString());
    NamedParams postParams = namedParametersFromRawQueryString(httpReq.getReader().readLine());
    String formName = postParams.valueOrNullOf(ServletFormHeaderBrick.FORM_NAME_PARAM);
    if (formName != null) {
      Form form = mappings.formName2FormResolver().resolveFormName(formName);
      String actionName = null;
      for (String paramName : postParams.names()) {
        if (paramName.startsWith(ServletFormData4RequestResolver.ACTION_NAME_PREFIX)) {
          actionName = paramName.substring(ServletFormData4RequestResolver.ACTION_NAME_PREFIX.length());
          break;
        }
      }
      FormFieldRecord fields = form.createEmptyFields();
      fields.setValuesFrom(FormFieldValueTree.buildTreeFrom(postParams.asMap()));
      FormResponse formResponse = form.handle(request, actionName, fields);
      if (formResponse instanceof FormResponse.Redirect) {
        String urlString = ((FormResponse.Redirect) formResponse).urlString;
        httpResp.sendRedirect(urlString);
        return;
      }
      System.out.println("Form action '" + actionName + "' :" + postParams);
    }
    //
    //   handle mapped controller
    //   ------------------------
    //
    ControllerResponse response = resolved.controller.handle(request);
    if (response instanceof PageResponse) {
      PageResponse pageResponse = (PageResponse) response;
      httpResp.setCharacterEncoding(outputEncodingStringOf(pageResponse.brick.getClass()));
      renderer.render(pageResponse.brick, httpResp.getWriter());
      return;
    }
    throw new RuntimeException("Unsupported " + ControllerResponse.class.getSimpleName() + ": " + response);
  }

  private ControllerRequest createRequest(String servletPath, Result resolved, String rawQueryString) {
    String path2home = UrlPathStringToHome.from(servletPath);
    NamedParams queryParams = namedParametersFromRawQueryString(rawQueryString);
    ControllerUrl controllerUrl = ControllerUrl.of(
        resolved.controller.getClass(),
        UrlParams.of(resolved.paramsPath, queryParams));
    ControllerRequest request = ServletControllerRequest.of(controllerUrl, path2home, mappings);
    return request;
  }
}
