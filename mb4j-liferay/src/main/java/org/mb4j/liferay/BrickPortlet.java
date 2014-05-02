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
import org.mb4j.controller.ControllerResponse;
import static org.mb4j.controller.http.HttpNamedParams.namedParametersFromRawQueryString;
import org.mb4j.controller.mapping.ControllerMappings;
import org.mb4j.controller.mapping.UrlPath2ControllerResolver;
import org.mb4j.controller.page.PageResponse;
import org.mb4j.controller.url.ControllerUrl;
import org.mb4j.controller.url.NamedParams;
import org.mb4j.controller.url.UrlParams;
import org.mb4j.controller.url.UrlPath;
import static org.mb4j.controller.url.UrlPathString.pathStringOf;
import static org.mb4j.liferay.PortletPathToHome.pathStringToHomeFrom;
import static org.mb4j.liferay.PortletViewPathUtils.currentURI;
import static org.mb4j.liferay.PortletViewPathUtils.viewPathFrom;

public class BrickPortlet extends GenericPortlet {
  private final BrickRenderer renderer;
  private final ControllerMappings mappings;

  public BrickPortlet(BrickRenderer renderer, ControllerMappings mappings) {
    this.renderer = renderer;
    this.mappings = mappings;
  }

  @Override
  protected void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
    URI currentURI = currentURI(request);
    UrlPath path = viewPathFrom(request);
    String path2home = pathStringToHomeFrom(request, currentURI.getRawPath());
    System.out.println("viewPath {" + pathStringOf(path) + "}");
    System.out.println("currentURI {" + currentURI + "}{" + path2home + "}");
    UrlPath2ControllerResolver.Result resolverResult = mappings.urlPath2ControllerResolver().resolve(path);
    if (resolverResult.resultIsEmpty()) {
      throw new PortletException("No view found for path [" + pathStringOf(path) + "]");
    }
    NamedParams namedParams = namedParametersFromRawQueryString(currentURI.getRawQuery());
    ControllerUrl url = ControllerUrl.of(
        resolverResult.controller.getClass(),
        UrlParams.of(resolverResult.paramsPath, namedParams));
    ControllerRequest viewReq = new PortletControllerRequest(
        url,
        path2home,
        new PortletControllerUrl4RequestResolver(response, mappings.controllerClass2UrlPathResolver()));
    ControllerResponse viewResp = resolverResult.controller.handle(viewReq);
    handle(viewReq, viewResp, response);
  }

  private void handle(ControllerRequest viewReq, ControllerResponse viewResp, RenderResponse response)
      throws IOException {
    if (viewResp instanceof PageResponse) {
      PageResponse pageResponse = (PageResponse) viewResp;
      renderer.render(pageResponse.brick, response.getWriter());
      return;
    }
    throw new RuntimeException("Unsupported " + ControllerResponse.class.getSimpleName()
        + " type: " + viewResp);
  }

  @Override
  public void processAction(ActionRequest request, ActionResponse response) throws PortletException, IOException {
    super.processAction(request, response);
  }
}
