package org.mb4j.liferay;

import javax.portlet.MimeResponse;
import javax.portlet.PortletURL;
import static org.mb4j.controller.http.HttpNamedParams.queryStringFrom;
import org.mb4j.controller.mapping.ControllerClass2UrlPathResolver;
import org.mb4j.controller.url.ControllerUrl;
import org.mb4j.controller.url.ControllerUrl4Request;
import org.mb4j.controller.url.ControllerUrl4RequestResolver;
import org.mb4j.controller.url.UrlPath;

public class PortletControllerUrl4RequestResolver implements ControllerUrl4RequestResolver {
  private final MimeResponse mimeResponseOrNull;
  private final ControllerClass2UrlPathResolver class2urlPath;

  public PortletControllerUrl4RequestResolver(
      MimeResponse mimeResponseOrNull,
      ControllerClass2UrlPathResolver class2urlPath) {
    this.mimeResponseOrNull = mimeResponseOrNull;
    this.class2urlPath = class2urlPath;
  }

  @Override
  public ControllerUrl4Request resolve(ControllerUrl url) {
    if (mimeResponseOrNull == null) {
      throw new RuntimeException("Controller URLs are resolved only in Rendering and Resource phase");
    }
    UrlPath controllerPath = class2urlPath.urlPathFor(url.controllerClass);
    UrlPath fullPath = controllerPath.add(url.params.path);
    String mvcPath = PortletUrlUtils.mvcPathParamValueFrom(fullPath);
    PortletURL renderURL = mimeResponseOrNull.createRenderURL();
    renderURL.setParameter(PortletUrlUtils.MVC_PATH_PARAM_NAME, mvcPath);
    String urlString = renderURL.toString();
    if (!url.params.named.isEmpty()) {
      urlString += "?" + queryStringFrom(url.params.named);
    }
    return new ControllerUrl4Request(urlString);
  }
}
