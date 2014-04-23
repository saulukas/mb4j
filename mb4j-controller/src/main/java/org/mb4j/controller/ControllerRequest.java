package org.mb4j.controller;

import org.mb4j.controller.form.FormData;
import org.mb4j.controller.form.FormData4Request;
import org.mb4j.controller.form1.Form;
import static org.mb4j.controller.url.BufferedUrlPathReader.bufferedReaderOf;
import org.mb4j.controller.url.ControllerUrl;
import org.mb4j.controller.url.ControllerUrl4Request;
import org.mb4j.controller.url.Url4Request;
import org.mb4j.controller.url.UrlPathReader;

public abstract class ControllerRequest {
  private final ControllerUrl url;
  private final UrlPathReader urlPathReader;
  public final Form.NameResolver actionParamNameResolver;

  public ControllerRequest(
      ControllerUrl url,
      Form.NameResolver actionParamNameResolver) {
    this.url = url;
    this.urlPathReader = bufferedReaderOf(url.params.path);
    this.actionParamNameResolver = actionParamNameResolver;
  }

  public ControllerUrl url() {
    return url;
  }

  public boolean hasMorePathSegments() {
    return urlPathReader.hasMoreSegments();
  }

  public String readPathSegment() {
    return urlPathReader.readSegment();
  }

  public abstract ControllerUrl4Request resolve(ControllerUrl url);

  public abstract Url4Request resolveUrl(String urlFromHome);

  public abstract FormData4Request resolve(FormData formData);

  @Override
  public String toString() {
    return getClass().getSimpleName() + "[" + urlPathReader + "]";
  }
}
