package org.mb4j.liferay;

import com.google.common.base.Optional;
import static com.google.common.collect.Iterators.forEnumeration;
import static com.google.common.collect.Lists.newArrayList;
import java.io.IOException;
import java.net.URI;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.MimeResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import org.mb4j.brick.renderer.BrickRenderer;
import org.mb4j.controller.ControllerRequest;
import org.mb4j.controller.form.FormResponse;
import org.mb4j.controller.form.FormResponseRedirect;
import org.mb4j.controller.form.FormResponseRenderCurrentPage;
import static org.mb4j.controller.form.FormSubmitHandler.formResponseFor;
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
import static org.mb4j.liferay.LiferayUtils.authTokenOrNullFrom;

public class BrickPortlet extends GenericPortlet {
  private final BrickRenderer renderer;
  private final ControllerMappings mappings;

  protected BrickPortlet(BrickRenderer renderer, ControllerMappings mappings) {
    this.renderer = renderer;
    this.mappings = mappings;
  }

  @Override
  protected void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException, IOException {
    System.out.println("Render attributes: " + newArrayList(forEnumeration(renderRequest.getAttributeNames())));
    UrlPath2ControllerResolver.Result resolved = resolvePage(renderRequest);
    Page page = (Page) resolved.controller;
    ControllerRequest request = createRequest(resolved, renderRequest, renderResponse);
    PageResponse response = page.handle(request);
    renderer.render(response.brick, renderResponse.getWriter());
  }

  @Override
  public void processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException, IOException {
    UrlPath2ControllerResolver.Result resolved = resolvePage(actionRequest);
    ControllerRequest request = createRequest(resolved, actionRequest, actionResponse);
    NamedParams postParams = PortletUrlUtils.namedParamsFrom(actionRequest);
    Optional<FormResponse> optionalResponse = formResponseFor(request, postParams, mappings);
    if (!optionalResponse.isPresent()) {
      throw new RuntimeException("Unknown portlet action called:"
          + "\n   " + postParams
          + "\n   " + request);
    }
    FormResponse response = optionalResponse.get();
    if (response instanceof FormResponseRedirect) {
      String urlString = ((FormResponseRedirect) response).urlString;
      actionResponse.sendRedirect(urlString);
      return;
    }
    FormResponseRenderCurrentPage responseWithAttributes = (FormResponseRenderCurrentPage) response;
    request.attributes().putAll(responseWithAttributes.attributes.asMap());
    PortletUrlUtils.copyUrlPathFromParameterToAttribute(actionRequest);
    System.out.println("Action attributes: " + newArrayList(forEnumeration(actionRequest.getAttributeNames())));
  }

  private UrlPath2ControllerResolver.Result resolvePage(PortletRequest request) throws PortletException {
    UrlPath path = PortletUrlUtils.urlPathFrom(request);
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
      PortletResponse response) {
    URI currentURI = LiferayUtils.currentURI(request);
    NamedParams namedParams = namedParametersFromRawQueryString(currentURI.getRawQuery());
    String path2home = PortletPathToHome.from(request, currentURI.getRawPath());
    ControllerUrl url = ControllerUrl.of(
        resolved.controller.getClass(),
        UrlParams.of(resolved.paramsPath, namedParams)
    );
    MimeResponse mimeResponse = (response instanceof MimeResponse) ? (MimeResponse) response : null;
    String liferayAuthTokenOrNull = (mimeResponse == null) ? null : authTokenOrNullFrom(mimeResponse);
    return new ControllerRequest(
        url,
        new PortletRequestAttributes(request),
        new Url4RequestResolver(path2home),
        new PortletControllerUrl4RequestResolver(mimeResponse, mappings.controllerClass2UrlPathResolver()),
        new PortletFormData4RequestResolver(
            response.getNamespace(),
            liferayAuthTokenOrNull,
            mappings.formClass2NameResolver()
        )
    );
  }
}
