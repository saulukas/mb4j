package org.mb4j.liferay;

import java.io.IOException;
import java.net.URI;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.MimeResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import org.mb4j.brick.renderer.BrickRenderer;
import org.mb4j.controller.ControllerRequest;
import static org.mb4j.controller.http.HttpNamedParams.namedParametersFromRawQueryString;
import org.mb4j.controller.mapping.ControllerMappings;
import org.mb4j.controller.mapping.UrlPath2ControllerResolver;
import org.mb4j.controller.page.Page;
import org.mb4j.controller.page.PageResponse;
import org.mb4j.controller.url.ControllerUrl;
import org.mb4j.controller.url.NamedParams;
import org.mb4j.controller.url.Url4RequestResolver;
import org.mb4j.controller.url.UrlParams;
import org.mb4j.controller.url.UrlPath;
import static org.mb4j.controller.url.UrlPathString.pathStringOf;
import org.mb4j.controller.utils.SimpleClassName;

public class BrickPortlet extends GenericPortlet {
  private final BrickRenderer renderer;
  private final ControllerMappings mappings;

  protected BrickPortlet(BrickRenderer renderer, ControllerMappings mappings) {
    this.renderer = renderer;
    this.mappings = mappings;
  }

  @Override
  protected void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
    UrlPath2ControllerResolver.Result resolved = resolvePage(request);
    Page page = (Page) resolved.controller;
    PageResponse pageResponse = page.handle(createRequest(resolved, request, response));
    renderer.render(pageResponse.brick, response.getWriter());
  }

  private UrlPath2ControllerResolver.Result resolvePage(PortletRequest request) throws PortletException {
    UrlPath path = PortletUrlPathUtils.urlPathFrom(request);
    System.out.println("urlPath {" + pathStringOf(path) + "}");
    UrlPath2ControllerResolver.Result resolved = mappings.urlPath2ControllerResolver().resolve(path);
    if (resolved.resultIsEmpty()) {
      throw new PortletException("No portlet Page found for path [" + pathStringOf(path) + "]");
    }
    if (!(resolved.controller instanceof Page)) {
      throw new PortletException("Expected " + SimpleClassName.of(Page.class) + " but found "
          + resolved.controller + " at for URL path [" + pathStringOf(path) + "].");
    }
    return resolved;
  }

  private ControllerRequest createRequest(
      UrlPath2ControllerResolver.Result resolved,
      PortletRequest request,
      MimeResponse response) {
    URI currentURI = LiferayUtils.currentURI(request);
    NamedParams namedParams = namedParametersFromRawQueryString(currentURI.getRawQuery());
    String path2home = PortletPathToHome.from(request, currentURI.getRawPath());
    ControllerUrl url = ControllerUrl.of(
        resolved.controller.getClass(),
        UrlParams.of(resolved.paramsPath, namedParams)
    );
    String liferayAuthTokenOrNull = LiferayUtils.authTokenOrNullFrom(response);
    return new ControllerRequest(
        url,
        new Url4RequestResolver(path2home),
        new PortletControllerUrl4RequestResolver(response, mappings.controllerClass2UrlPathResolver()),
        new PortletFormData4RequestResolver(
            response.getNamespace(),
            liferayAuthTokenOrNull,
            mappings.formClass2NameResolver()
        )
    );
  }

  @Override
  public void processAction(ActionRequest request, ActionResponse response) throws PortletException, IOException {
//    UrlPath2ControllerResolver.Result resolved = resolvePage(request);
//    ControllerRequest request = createRequest(resolved, request, response);
//    NamedParams postParams = namedParametersFromRawQueryString(httpReq.getReader().readLine());
//    Optional<FormResponse> formResponse = FormSubmitHandler.formResponseFor(request, postParams, mappings);
//    if (formResponse.isPresent()) {
//      FormResponse presentResponse = formResponse.get();
//      if (presentResponse instanceof FormResponseRedirect) {
//        String urlString = ((FormResponseRedirect) presentResponse).urlString;
//        httpResp.sendRedirect(urlString);
//        return;
//      }
//      if (presentResponse instanceof FormResponseRenderCurrentPage) {
//        if (!(resolved.controller instanceof Page)) {
//          throw new RuntimeException("Received " + FormResponseRenderCurrentPage.class.getSimpleName()
//              + " and current controller must be " + Page.class.getSimpleName() + " but found "
//              + resolved.controller + ".");
//        }
//        FormResponseRenderCurrentPage responseWithAttributes = (FormResponseRenderCurrentPage) presentResponse;
//        request.putAttributes(responseWithAttributes.attributes);
//      }
//    }
  }
}
