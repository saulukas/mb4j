package org.mb4j.controller;

import org.mb4j.controller.form.Form;
import static org.mb4j.controller.url.BufferedUrlPathReader.bufferedReaderOf;
import org.mb4j.controller.url.ControllerUrl;
import org.mb4j.controller.url.ControllerUrl4Request;
import org.mb4j.controller.url.ControllerUrl4RequestResolver;
import org.mb4j.controller.url.Url4Request;
import org.mb4j.controller.url.Url4RequestResolver;
import org.mb4j.controller.url.UrlPathReader;

public class ControllerRequest {
  private final ControllerUrl url;
  private final UrlPathReader urlPathReader;
  private final Url4RequestResolver staticResourceUrlResolver;
  private final ControllerUrl4RequestResolver controllerUrlResolver;
  public final Form.NameResolver actionParamNameResolver;

  public ControllerRequest(
      ControllerUrl url,
      Url4RequestResolver staticUrlResolver,
      ControllerUrl4RequestResolver controllerUrlResolver,
      Form.NameResolver actionParamNameResolver) {
    this.url = url;
    this.urlPathReader = bufferedReaderOf(url.params.path);
    this.staticResourceUrlResolver = staticUrlResolver;
    this.controllerUrlResolver = controllerUrlResolver;
    this.actionParamNameResolver = actionParamNameResolver;
  }

  public ControllerUrl url() {
    return url;
  }

  public ControllerUrl4Request resolve(ControllerUrl url) {
    return controllerUrlResolver.resolve(url);
  }

  public Url4Request resolveUrl(String urlFromHome) {
    return staticResourceUrlResolver.resolveUrl(urlFromHome);
  }

  public boolean hasMorePathSegments() {
    return urlPathReader.hasMoreSegments();
  }

  public String readPathSegment() {
    return urlPathReader.readSegment();
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + "[" + urlPathReader + "]";
  }
}
