package org.mb4j.liferay;

import javax.portlet.MimeResponse;
import javax.portlet.PortletURL;
import static org.mb4j.controller.http.HttpNamedParams.queryStringFrom;
import org.mb4j.controller.mapping.ControllerClass2UrlPathResolver;
import org.mb4j.controller.url.ControllerUrl;
import org.mb4j.controller.url.ControllerUrl4Request;
import org.mb4j.controller.url.ControllerUrl4RequestResolver;
import org.mb4j.controller.url.UrlPath;

public class PortletViewUrlStringResolver implements ControllerUrl4RequestResolver {
  private final MimeResponse portletResponse;
  private final ControllerClass2UrlPathResolver pathResolver;

  public PortletViewUrlStringResolver(
      MimeResponse portletResponse,
      ControllerClass2UrlPathResolver pathResolver) {
    this.portletResponse = portletResponse;
    this.pathResolver = pathResolver;
  }

  @Override
  public ControllerUrl4Request resolve(ControllerUrl url) {
    UrlPath viewPath = pathResolver.urlPathFor(url.controllerClass);
    UrlPath fullPath = viewPath.add(url.params.path);
    String mvcPath = PortletViewPathUtils.mvcPathParamValueFrom(fullPath);
    PortletURL renderURL = portletResponse.createRenderURL();
    renderURL.setParameter(PortletViewPathUtils.MVC_PATH_PARAM_NAME, mvcPath);
    String urlString = renderURL.toString();
    if (!url.params.named.isEmpty()) {
      urlString += "?" + queryStringFrom(url.params.named);
    }
    return new ControllerUrl4Request(urlString);
  }
}
