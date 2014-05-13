package org.mb4j.controller;

import org.mb4j.controller.form.FormData;
import org.mb4j.controller.form.FormData4Response;
import org.mb4j.controller.form.FormData4ResponseResolver;
import org.mb4j.controller.resource.Resources4Response;
import org.mb4j.controller.resource.Resources4ResponseResolver;
import static org.mb4j.controller.url.BufferedUrlPathReader.bufferedReaderOf;
import org.mb4j.controller.url.ControllerUrl;
import org.mb4j.controller.url.ControllerUrl4Response;
import org.mb4j.controller.url.ControllerUrl4ResponseResolver;
import org.mb4j.controller.url.Url4Response;
import org.mb4j.controller.url.Url4ResponseResolver;
import org.mb4j.controller.url.UrlPathReader;
import org.mb4j.controller.utils.Attributes;

public class Request {
  private final ControllerUrl url;
  private final UrlPathReader urlPathReader;
  private final Attributes attributes;
  private final Url4ResponseResolver urlResolver;
  private final ControllerUrl4ResponseResolver controllerUrlResolver;
  private final FormData4ResponseResolver formDataResolver;
  private final Resources4ResponseResolver resourcesResolver;

  public Request(
      ControllerUrl url,
      Attributes attributes,
      Url4ResponseResolver urlResolver,
      ControllerUrl4ResponseResolver controllerUrlResolver,
      FormData4ResponseResolver formDataResolver,
      Resources4ResponseResolver resourcesResolver) {
    this.url = url;
    this.urlPathReader = bufferedReaderOf(url.params.path);
    this.attributes = attributes;
    this.urlResolver = urlResolver;
    this.controllerUrlResolver = controllerUrlResolver;
    this.formDataResolver = formDataResolver;
    this.resourcesResolver = resourcesResolver;
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

  public ControllerUrl4Response resolve(ControllerUrl url) {
    return controllerUrlResolver.resolve(url);
  }

  public Url4Response resolveUrl(String staticAssetUrl) {
    return urlResolver.resolveUrl(staticAssetUrl);
  }

  public FormData4Response resolve(FormData<?> formData) {
    return formDataResolver.resolve(formData);
  }

  public Resources4Response resolveResourcesOf(Component component) {
    return resourcesResolver.resolveResourcesFor(component);
  }

  public Attributes attributes() {
    return attributes;
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + "[" + urlPathReader + "]";
  }
}
