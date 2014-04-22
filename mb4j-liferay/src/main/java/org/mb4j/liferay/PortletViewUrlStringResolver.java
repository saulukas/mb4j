package org.mb4j.liferay;

import javax.portlet.MimeResponse;
import javax.portlet.PortletURL;
import static org.mb4j.controller.http.HttpNamedParams.queryStringFrom;
import org.mb4j.controller.mapping.ControllerClass2UrlPathResolver;
import org.mb4j.controller.url.UrlPath;
import org.mb4j.controller.url.ControllerUrl;
import org.mb4j.controller.url.ControllerUrl2StringResolver;

public class PortletViewUrlStringResolver implements ControllerUrl2StringResolver {
  private final MimeResponse portletResponse;
  private final ControllerClass2UrlPathResolver pathResolver;

  public PortletViewUrlStringResolver(
      MimeResponse portletResponse,
      ControllerClass2UrlPathResolver pathResolver) {
    this.portletResponse = portletResponse;
    this.pathResolver = pathResolver;
  }

  @Override
  public String urlStringOf(ControllerUrl url) {
    UrlPath viewPath = pathResolver.urlPathFor(url.controllerClass);
    UrlPath fullPath = viewPath.add(url.params.path);
    String mvcPath = PortletViewPathUtils.mvcPathParamValueFrom(fullPath);
    PortletURL renderURL = portletResponse.createRenderURL();
    renderURL.setParameter(PortletViewPathUtils.MVC_PATH_PARAM_NAME, mvcPath);
    if (url.params.named.isEmpty()) {
      return renderURL.toString();
    }
    return renderURL.toString() + "?" + queryStringFrom(url.params.named);
  }
}
