package org.mb4j.servlet;

import org.mb4j.controller.ControllerRequest;
import org.mb4j.controller.form.FormData;
import org.mb4j.controller.form.FormData4Request;
import org.mb4j.controller.mapping.ControllerClass2UrlPathResolver;
import org.mb4j.controller.mapping.FormClass2NameResolver;
import org.mb4j.controller.url.ControllerUrl;
import org.mb4j.controller.url.ControllerUrl4Request;
import org.mb4j.controller.url.ControllerUrl4RequestResolver;
import org.mb4j.controller.url.NamedParams;
import org.mb4j.controller.url.Url4Request;

public class ServletControllerRequest extends ControllerRequest {
  private final String path2home;
  private final ControllerUrl4RequestResolver controllerUrlResolver;
  private final FormClass2NameResolver formResolver;

  public ServletControllerRequest(
      String path2home,
      ControllerUrl url,
      NamedParams postParams,
      ControllerClass2UrlPathResolver controllerResolver,
      FormClass2NameResolver formResolver) {
    super(url, postParams);
    this.path2home = path2home;
    this.controllerUrlResolver = new ServletControllerUrl4RequestResolver(path2home, controllerResolver);
    this.formResolver = formResolver;
  }

  @Override
  public Url4Request resolveUrl(String urlFromHome) {
    return new Url4Request(path2home + urlFromHome);
  }

  @Override
  public ControllerUrl4Request resolve(ControllerUrl url) {
    return controllerUrlResolver.resolve(url);
  }

  @Override
  public FormData4Request resolve(FormData formData) {
    return ServletFormData4RequestResolver.resolve(formResolver, formData);
  }
}
