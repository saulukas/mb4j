package org.mb4j.liferay;

import com.google.common.base.Optional;
import java.io.IOException;
import java.net.URI;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletConfig;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.ResourceServingPortlet;
import org.mb4j.brick.renderer.BrickRenderer;
import org.mb4j.controller.Component;
import org.mb4j.controller.Request;
import org.mb4j.controller.form.FormResponse;
import org.mb4j.controller.form.FormResponseRedirectToUrlString;
import org.mb4j.controller.form.FormResponseRenderCurrentPage;
import static org.mb4j.controller.form.FormSubmitHandler.formResponseFor;
import org.mb4j.controller.resource.Resources4ResponseResolver;
import org.mb4j.controller.resource.Resources4ResponseResolver.ParamValue;
import org.mb4j.controller.sitemap.MapUrlPath2Controller;
import org.mb4j.controller.sitemap.SiteMap;
import org.mb4j.controller.url.ControllerUrl;
import org.mb4j.controller.url.NamedParams;
import org.mb4j.controller.url.UrlParams;
import org.mb4j.controller.url.UrlPath;
import static org.mb4j.controller.url.UrlPathString.pathStringOf;
import org.mb4j.controller.utils.Attributes;
import static org.mb4j.controller.utils.HttpNamedParams.namedParamsFromRawQuery;
import static org.mb4j.liferay.PortletPathToHome.pathToAssets;
import static org.mb4j.liferay.PortletUrlUtils.authTokenOrNullFrom;

public class BrickPortlet implements Portlet, ResourceServingPortlet {
  private final String friendlyUrlMapping;
  private final BrickRenderer renderer;
  private final SiteMap viewMap;

  protected BrickPortlet(String friendlyUrlMapping, BrickRenderer renderer, SiteMap viewMap) {
    this.friendlyUrlMapping = friendlyUrlMapping;
    this.renderer = renderer;
    this.viewMap = viewMap;
  }

  @Override
  public void init(PortletConfig config) throws PortletException {
  }

  @Override
  public void destroy() {
  }

  @Override
  public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException, IOException {
    MapUrlPath2Controller.Result resolved = resolveView(renderRequest);
    Request request = createRequest(resolved, renderRequest, renderResponse);
    resolved.controller.handle(request, new PortletControllerResponse(renderer, renderResponse));
  }

  @Override
  public void processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException, IOException {
    MapUrlPath2Controller.Result resolved = resolveView(actionRequest);
    Request request = createRequest(resolved, actionRequest, actionResponse);
    NamedParams postParams = PortletUrlUtils.namedParamsFrom(actionRequest);
    Optional<FormResponse> formRC = formResponseFor(request, postParams, viewMap.formName2Form());
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
  public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws PortletException, IOException {
    String resourceParam = resourceRequest.getParameter(Resources4ResponseResolver.RESOURCE_PARAM_NAME);
    if (resourceParam == null) {
      return;
    }
    Resources4ResponseResolver.ParamValue value = ParamValue.from(resourceParam);
    Component componentWithResources
        = viewMap.componentWithResourcesName2Component().componentFor(value.componentName);
    MapUrlPath2Controller.Result resolved = resolveView(resourceRequest);
    Request request = createRequest(resolved, resourceRequest, resourceResponse);
    componentWithResources.serveResource(
        value.resourceName,
        request,
        new PortletControllerResponse(renderer, resourceResponse)
    );
  }

  private MapUrlPath2Controller.Result resolveView(PortletRequest request) throws PortletException {
    UrlPath path = PortletUrlUtils.urlPathFor(request, friendlyUrlMapping);
    System.out.println("urlPath {" + pathStringOf(path) + "}");
    MapUrlPath2Controller.Result resolved = viewMap.urlPath2Controller().controllerFor(path);
    if (resolved.resultIsEmpty()) {
      throw new PortletException("No portlet view found for path [" + pathStringOf(path) + "]");
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
    String path2assets = pathToAssets(request, currentURI.getRawPath());
    Attributes attributes = new PortletRequestAttributes(request);
    String authTokenOrNull = authTokenOrNullFrom(response);
    String namespace = response.getNamespace();
    return PortletControllerRequest.of(
        url,
        path2home,
        path2assets,
        attributes,
        namespace,
        authTokenOrNull,
        new PortletResources4ResponseResolver(response, viewMap.componentWithResourcesClass2Name()),
        viewMap
    );
  }
}
