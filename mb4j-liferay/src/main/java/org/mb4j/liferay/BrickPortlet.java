package org.mb4j.liferay;

import java.io.IOException;
import java.net.URI;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
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
    //
    //   find mapped controller if any
    //   -----------------------------
    //
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
    //
    //   handle Page response
    //   --------------------
    //
    Page page = (Page) resolved.controller;
    PageResponse pageResponse = page.handle(createRequest(resolved, request, response));
    renderer.render(pageResponse.brick, response.getWriter());
  }

  private ControllerRequest createRequest(
      UrlPath2ControllerResolver.Result resolved,
      RenderRequest request,
      RenderResponse response) {
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
    super.processAction(request, response);
  }
}
