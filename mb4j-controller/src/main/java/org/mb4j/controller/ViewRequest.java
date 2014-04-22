package org.mb4j.controller;

import org.mb4j.controller.form.Form;
import static org.mb4j.controller.url.BufferedUrlPathReader.bufferedReaderOf;
import org.mb4j.controller.url.ControllerUrl;
import org.mb4j.controller.url.ControllerUrl2StringResolver;
import org.mb4j.controller.url.StaticResourceUrlResolver;
import org.mb4j.controller.url.UrlPathReader;

public class ViewRequest {
  private final ControllerUrl url;
  public final UrlPathReader pathParamsReader;
  private final StaticResourceUrlResolver staticResourceUrlResolver;
  private final ControllerUrl2StringResolver viewUrlResolver;
  public final Form.NameResolver actionParamNameResolver;

  public ViewRequest(
      ControllerUrl url,
      StaticResourceUrlResolver staticUrlResolver,
      ControllerUrl2StringResolver viewUrlResolver,
      Form.NameResolver actionParamNameResolver) {
    this.url = url;
    this.pathParamsReader = bufferedReaderOf(url.params.path);
    this.staticResourceUrlResolver = staticUrlResolver;
    this.viewUrlResolver = viewUrlResolver;
    this.actionParamNameResolver = actionParamNameResolver;
  }

  public ControllerUrl url() {
    return url;
  }

  public String stringOf(ControllerUrl url) {
    return viewUrlResolver.urlStringOf(url);
  }

  public String staticUrl(String urlFromHome) {
    return staticResourceUrlResolver.urlForStaticResource(urlFromHome);
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + "[" + pathParamsReader + "]";
  }
}
