package org.mb4j.liferay;

import org.mb4j.controller.ControllerRequest;
import org.mb4j.controller.form.FormData;
import org.mb4j.controller.form.FormData4Request;
import org.mb4j.controller.url.ControllerUrl;
import org.mb4j.controller.url.ControllerUrl4Request;
import org.mb4j.controller.url.ControllerUrl4RequestResolver;
import org.mb4j.controller.url.NamedParams;
import org.mb4j.controller.url.Url4Request;

class PortletControllerRequest extends ControllerRequest {
  private final String path2home;
  private final ControllerUrl4RequestResolver controllerUrlResolver;

  public PortletControllerRequest(
      String path2home,
      ControllerUrl url,
      ControllerUrl4RequestResolver controllerUrlResolver) {
    super(url, NamedParams.empty());
    this.path2home = path2home;
    this.controllerUrlResolver = controllerUrlResolver;
  }

  @Override
  public Url4Request resolveUrl(String urlFromHome) {
    if (!urlFromHome.startsWith("/")) {
      urlFromHome = "/" + urlFromHome;
    }
    return new Url4Request(path2home + urlFromHome);
  }

  @Override
  public ControllerUrl4Request resolve(ControllerUrl url) {
    return controllerUrlResolver.resolve(url);
  }

  @Override
  public FormData4Request resolve(FormData formData) {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
