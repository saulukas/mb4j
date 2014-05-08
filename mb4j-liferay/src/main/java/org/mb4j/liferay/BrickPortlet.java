package org.mb4j.liferay;

import com.google.common.base.Optional;
import java.io.IOException;
import java.net.URI;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import org.mb4j.brick.renderer.BrickRenderer;
import org.mb4j.controller.Request;
import org.mb4j.controller.form.FormResponse;
import org.mb4j.controller.form.FormResponseRedirectToUrlString;
import org.mb4j.controller.form.FormResponseRenderCurrentPage;
import static org.mb4j.controller.form.FormSubmitHandler.formResponseFor;
import org.mb4j.controller.page.Page;
import org.mb4j.controller.sitemap.MapUrlPath2Controller;
import org.mb4j.controller.sitemap.SiteMap;
import org.mb4j.controller.url.ControllerUrl;
import org.mb4j.controller.url.NamedParams;
import org.mb4j.controller.url.UrlParams;
import org.mb4j.controller.url.UrlPath;
import static org.mb4j.controller.url.UrlPathString.pathStringOf;
import org.mb4j.controller.utils.Attributes;
import static org.mb4j.controller.utils.HttpNamedParams.namedParamsFromRawQuery;
import org.mb4j.controller.utils.SimpleClassName;
import static org.mb4j.liferay.PortletPathToHome.pathToStaticResources;
import static org.mb4j.liferay.PortletUrlUtils.authTokenOrNullFrom;

public class BrickPortlet extends GenericPortlet {
  private final String friendlyUrlMapping;
  private final BrickRenderer renderer;
  private final SiteMap siteMap;

  protected BrickPortlet(String friendlyUrlMapping, BrickRenderer renderer, SiteMap siteMap) {
    this.friendlyUrlMapping = friendlyUrlMapping;
    this.renderer = renderer;
    this.siteMap = siteMap;
  }

  @Override
  protected void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException, IOException {
    MapUrlPath2Controller.Result resolved = resolvePage(renderRequest);
    Request request = createRequest(resolved, renderRequest, renderResponse);
    resolved.controller.handle(request, new PortletControllerResponse(renderer, renderResponse));
  }

  @Override
  public void processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException, IOException {
    MapUrlPath2Controller.Result resolved = resolvePage(actionRequest);
    Request request = createRequest(resolved, actionRequest, actionResponse);
    NamedParams postParams = PortletUrlUtils.namedParamsFrom(actionRequest);
    Optional<FormResponse> formRC = formResponseFor(request, postParams, siteMap.formName2Form());
    if (!formRC.isPresent()) {
      throw new RuntimeException("Unknown portlet action called:"
          + "\n   " + postParams
          + "\n   " + request);
    }
    FormResponse response = formRC.get();
    if (response instanceof FormResponseRedirectToUrlString) {
      String urlString = ((FormResponseRedirectToUrlString) response).urlString;
      actionResponse.sendRedirect(urlString);
      return;
    }
    if (!(response instanceof FormResponseRenderCurrentPage)) {
      throw new RuntimeException("Unsupported Form response: " + response);
    }
  }

  @Override
  public void serveResource(ResourceRequest request, ResourceResponse response) throws PortletException, IOException {
    super.serveResource(request, response);
  }

  private MapUrlPath2Controller.Result resolvePage(PortletRequest request) throws PortletException {
    UrlPath path = PortletUrlUtils.urlPathFor(request, friendlyUrlMapping);
    System.out.println("urlPath {" + pathStringOf(path) + "}");
    MapUrlPath2Controller.Result resolved = siteMap.urlPath2Controller().controllerFor(path);
    if (resolved.resultIsEmpty()) {
      throw new PortletException("No portlet Page found for path [" + pathStringOf(path) + "]");
    }
    if (!(resolved.controller instanceof Page)) {
      throw new PortletException("Expected " + SimpleClassName.of(Page.class) + " but found "
          + resolved.controller + " at for URL path [" + pathStringOf(path) + "].");
    }
    return resolved;
  }

  private Request createRequest(
      MapUrlPath2Controller.Result resolved,
      PortletRequest request,
      PortletResponse response) {
    URI currentURI = PortletUrlUtils.currentURI(request);
    NamedParams namedParams = namedParamsFromRawQuery(currentURI.getRawQuery());
    ControllerUrl url = ControllerUrl.of(
        resolved.controller.getClass(),
        UrlParams.of(resolved.paramsPath, namedParams)
    );
    String path2home = PortletUrlUtils.path2homeFor(response);
    String path2staticResources = pathToStaticResources(request, currentURI.getRawPath());
    Attributes attributes = new PortletRequestAttributes(request);
    String authTokenOrNull = authTokenOrNullFrom(response);
    String namespace = response.getNamespace();
    return PortletControllerRequest.of(
        url,
        path2home,
        path2staticResources,
        attributes,
        namespace,
        authTokenOrNull,
        siteMap
    );
  }
}
