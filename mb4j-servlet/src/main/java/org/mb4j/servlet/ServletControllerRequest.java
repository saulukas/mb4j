package org.mb4j.servlet;

import org.mb4j.controller.ControllerRequest;
import org.mb4j.controller.form1.Form;
import org.mb4j.controller.url.ControllerUrl;
import org.mb4j.controller.url.ControllerUrl4Request;
import org.mb4j.controller.url.ControllerUrl4RequestResolver;
import org.mb4j.controller.url.Url4Request;

class ServletControllerRequest extends ControllerRequest {
  private final String path2home;
  private final ControllerUrl4RequestResolver controllerUrlResolver;

  public ServletControllerRequest(
      String path2home,
      ControllerUrl url,
      ControllerUrl4RequestResolver controllerUrlResolver,
      Form.NameResolver actionParamNameResolver) {
    super(url, actionParamNameResolver);
    this.path2home = path2home;
    this.controllerUrlResolver = controllerUrlResolver;
  }

  @Override
  public Url4Request resolveUrl(String urlFromHome) {
    return new Url4Request(path2home + urlFromHome);
  }

  @Override
  public ControllerUrl4Request resolve(ControllerUrl url) {
    return controllerUrlResolver.resolve(url);
  }
}
