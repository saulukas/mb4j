package org.mb4j.liferay;

import javax.portlet.MimeResponse;
import javax.portlet.PortletURL;
import static org.mb4j.http.HttpNamedParams.queryStringFrom;
import org.mb4j.controller.mount.PathFromViewClassResolver;
import org.mb4j.controller.path.ViewPath;
import org.mb4j.controller.url.ViewUrl;
import org.mb4j.controller.url.ViewUrlStringResolver;

public class PortletViewUrlStringResolver implements ViewUrlStringResolver {
  private final MimeResponse portletResponse;
  private final PathFromViewClassResolver pathResolver;

  public PortletViewUrlStringResolver(
      MimeResponse portletResponse,
      PathFromViewClassResolver pathResolver) {
    this.portletResponse = portletResponse;
    this.pathResolver = pathResolver;
  }

  @Override
  public String urlStringOf(ViewUrl url) {
    ViewPath viewPath = pathResolver.viewPathFor(url.viewClass);
    ViewPath fullPath = viewPath.add(url.params.path);
    String mvcPath = PortletViewPathUtils.mvcPathParamValueFrom(fullPath);
    PortletURL renderURL = portletResponse.createRenderURL();
    renderURL.setParameter(PortletViewPathUtils.MVC_PATH_PARAM_NAME, mvcPath);
    if (url.params.named.isEmpty()) {
      return renderURL.toString();
    }
    return renderURL.toString() + "?" + queryStringFrom(url.params.named);
  }
}