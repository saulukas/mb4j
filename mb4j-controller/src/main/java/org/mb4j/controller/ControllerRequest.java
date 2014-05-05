package org.mb4j.controller;

import org.mb4j.controller.form.FormData;
import org.mb4j.controller.form.FormData4Request;
import org.mb4j.controller.form.FormData4RequestResolver;
import org.mb4j.controller.resource.BinaryResource;
import org.mb4j.controller.resource.TextResource;
import static org.mb4j.controller.url.BufferedUrlPathReader.bufferedReaderOf;
import org.mb4j.controller.url.ControllerUrl;
import org.mb4j.controller.url.ControllerUrl4Request;
import org.mb4j.controller.url.ControllerUrl4RequestResolver;
import org.mb4j.controller.url.Url4Request;
import org.mb4j.controller.url.Url4RequestResolver;
import org.mb4j.controller.url.UrlPathReader;
import org.mb4j.controller.utils.Attributes;

public abstract class ControllerRequest {
  private final ControllerUrl url;
  private final UrlPathReader urlPathReader;
  private final Attributes attributes;
  private final Url4RequestResolver urlResolver;
  private final ControllerUrl4RequestResolver controllerUrlResolver;
  private final FormData4RequestResolver formDataResolver;

  public ControllerRequest(
      ControllerUrl url,
      Attributes attributes,
      Url4RequestResolver urlResolver,
      ControllerUrl4RequestResolver controllerUrlResolver,
      FormData4RequestResolver formDataResolver) {
    this.url = url;
    this.urlPathReader = bufferedReaderOf(url.params.path);
    this.attributes = attributes;
    this.urlResolver = urlResolver;
    this.controllerUrlResolver = controllerUrlResolver;
    this.formDataResolver = formDataResolver;
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

  public ControllerUrl4Request resolve(ControllerUrl url) {
    return controllerUrlResolver.resolve(url);
  }

  public Url4Request resolveUrl(String urlFromHome) {
    return urlResolver.resolveUrl(urlFromHome);
  }

  public FormData4Request resolve(FormData<?> formData) {
    return formDataResolver.resolve(formData);
  }

  public Attributes attributes() {
    return attributes;
  }

  public abstract BinaryResource.Output binaryOutput();

  public abstract TextResource.Output textOutput();

  @Override
  public String toString() {
    return getClass().getSimpleName() + "[" + urlPathReader + "]";
  }
}
