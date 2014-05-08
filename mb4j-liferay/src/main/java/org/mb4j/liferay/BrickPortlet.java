package org.mb4j.liferay;

import com.google.common.base.Optional;
import static com.google.common.collect.Iterators.forEnumeration;
import static com.google.common.collect.Lists.newArrayList;
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
import org.mb4j.controller.ControllerRequest;
import org.mb4j.controller.form.FormResponse;
import org.mb4j.controller.form.FormResponseRedirectToController;
import org.mb4j.controller.form.FormResponseRedirectToUrlString;
import static org.mb4j.controller.form.FormResponseRedirectToUrlString.redirectTo;
import org.mb4j.controller.form.FormResponseRenderCurrentPage;
import static org.mb4j.controller.form.FormSubmitHandler.formResponseFor;
import org.mb4j.controller.page.Page;
import org.mb4j.controller.page.PageResponse;
import org.mb4j.controller.sitemap.MapUrlPath2Controller;
import org.mb4j.controller.sitemap.SiteMap;
import org.mb4j.controller.url.ControllerUrl;
import org.mb4j.controller.url.NamedParams;
import org.mb4j.controller.url.UrlParams;
import org.mb4j.controller.url.UrlPath;
import static org.mb4j.controller.url.UrlPathString.pathStringOf;
import org.mb4j.controller.utils.Attributes;
import static org.mb4j.controller.utils.HttpNamedParams.namedParametersFromRawQueryString;
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
    System.out.println("Render attributes: " + newArrayList(forEnumeration(renderRequest.getAttributeNames())));
    MapUrlPath2Controller.Result resolved = resolvePage(renderRequest);
    Page page = (Page) resolved.controller;
    ControllerRequest request = createRequest(resolved, renderRequest, renderResponse);
    PageResponse response = page.handle(request);
    renderer.render(response.brick, renderResponse.getWriter());
  }

  @Override
  public void processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException, IOException {
    MapUrlPath2Controller.Result resolved = resolvePage(actionRequest);
    ControllerRequest request = createRequest(resolved, actionRequest, actionResponse);
    NamedParams postParams = PortletUrlUtils.namedParamsFrom(actionRequest);
    Optional<FormResponse> optionalResponse = formResponseFor(request, postParams, siteMap);
    if (!optionalResponse.isPresent()) {
      throw new RuntimeException("Unknown portlet action called:"
          + "\n   " + postParams
          + "\n   " + request);
    }
    FormResponse response = optionalResponse.get();
    if (response instanceof FormResponseRedirectToController) {
      ControllerUrl controllerUrl = ((FormResponseRedirectToController) response).controllerUrl;
      response = redirectTo(request.resolve(controllerUrl));
    }
    if (response instanceof FormResponseRedirectToUrlString) {
      String urlString = ((FormResponseRedirectToUrlString) response).urlString;
      actionResponse.sendRedirect(urlString);
      return;
    }
    if (response instanceof FormResponseRenderCurrentPage) {
      FormResponseRenderCurrentPage responseWithAttributes = (FormResponseRenderCurrentPage) response;
      request.attributes().putAll(responseWithAttributes.attributes.asMap());
    }
    System.out.println("Action attributes: " + newArrayList(forEnumeration(actionRequest.getAttributeNames())));
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

  private ControllerRequest createRequest(
      MapUrlPath2Controller.Result resolved,
      PortletRequest request,
      PortletResponse response) {
    URI currentURI = PortletUrlUtils.currentURI(request);
    NamedParams namedParams = namedParametersFromRawQueryString(currentURI.getRawQuery());
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
