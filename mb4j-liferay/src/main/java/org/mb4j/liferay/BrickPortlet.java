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
import org.mb4j.component.Component;
import org.mb4j.component.form.FormResponse;
import org.mb4j.component.form.FormResponseRedirectToUrlString;
import org.mb4j.component.form.FormResponseRenderCurrentPage;
import static org.mb4j.component.form.FormSubmitHandler.formResponseFor;
import org.mb4j.component.resource.Resources4ResponseResolver;
import org.mb4j.component.resource.Resources4ResponseResolver.ParamValue;
import org.mb4j.component.url.NamedParams;
import org.mb4j.component.url.UrlParams;
import org.mb4j.component.url.UrlPath;
import static org.mb4j.component.url.UrlPathString.pathStringOf;
import org.mb4j.component.utils.Attributes;
import static org.mb4j.component.utils.HttpNamedParams.namedParamsFromRawQuery;
import org.mb4j.component.view.ViewRequest;
import org.mb4j.component.view.ViewResponse;
import org.mb4j.component.view.ViewUrl;
import org.mb4j.component.viewmap.MapUrlPath2Controller;
import org.mb4j.component.viewmap.ViewMap;
import static org.mb4j.liferay.PortletPathToHome.pathToAssets;
import static org.mb4j.liferay.PortletUrlUtils.authTokenOrNullFrom;

public class BrickPortlet implements Portlet, ResourceServingPortlet {
  private final String friendlyUrlMapping;
  private final BrickRenderer renderer;
  private final ViewMap viewMap;

  protected BrickPortlet(String friendlyUrlMapping, BrickRenderer renderer, ViewMap viewMap) {
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
    ViewRequest request = createRequest(resolved, renderRequest, renderResponse);
    ViewResponse response = new PortletControllerResponse(renderer, renderResponse);
    resolved.controller.handle(request, response);
  }

  @Override
  public void processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException, IOException {
    MapUrlPath2Controller.Result resolved = resolveView(actionRequest);
    ViewRequest request = createRequest(resolved, actionRequest, actionResponse);
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
    ViewRequest request = createRequest(resolved, resourceRequest, resourceResponse);
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

  private ViewRequest createRequest(
      MapUrlPath2Controller.Result resolved,
      PortletRequest request,
      PortletResponse response) {
    URI currentURI = PortletUrlUtils.currentURI(request);
    NamedParams namedParams = namedParamsFromRawQuery(currentURI.getRawQuery());
    ViewUrl viewUrl = ViewUrl.of(
        resolved.controller.getClass(),
        UrlParams.of(resolved.paramsPath, namedParams)
    );
    String path2home = PortletUrlUtils.path2homeFor(response);
    String path2assets = pathToAssets(request, currentURI.getRawPath());
    Attributes attributes = new PortletRequestAttributes(request);
    String authTokenOrNull = authTokenOrNullFrom(response);
    String namespace = response.getNamespace();
    return PortletControllerRequest.of(
        viewUrl,
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
