package org.mb4j.component;

import org.mb4j.component.form.FormData;
import org.mb4j.component.form.FormData4Response;
import org.mb4j.component.form.FormData4ResponseResolver;
import org.mb4j.component.resource.Resources4Response;
import org.mb4j.component.resource.Resources4ResponseResolver;
import org.mb4j.component.url.AssetUrl4Response;
import org.mb4j.component.url.AssetUrl4ResponseResolver;
import org.mb4j.component.url.BufferedUrlPathReader;
import org.mb4j.component.url.ControllerUrl;
import org.mb4j.component.url.ControllerUrl4Response;
import org.mb4j.component.url.ControllerUrl4ResponseResolver;
import org.mb4j.component.url.UrlPathReader;
import org.mb4j.component.utils.Attributes;

public class ViewRequest {
  private final ControllerUrl url;
  private final UrlPathReader urlPathReader;
  private final Attributes attributes;
  private final AssetUrl4ResponseResolver urlResolver;
  private final ControllerUrl4ResponseResolver controllerUrlResolver;
  private final FormData4ResponseResolver formDataResolver;
  private final Resources4ResponseResolver resourcesResolver;

  public ViewRequest(
      ControllerUrl url,
      Attributes attributes,
      AssetUrl4ResponseResolver urlResolver,
      ControllerUrl4ResponseResolver controllerUrlResolver,
      FormData4ResponseResolver formDataResolver,
      Resources4ResponseResolver resourcesResolver) {
    this.url = url;
    this.urlPathReader = BufferedUrlPathReader.of(url.params.path);
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

  public AssetUrl4Response resolveUrl(String assetUrl) {
    return urlResolver.resolveUrl(assetUrl);
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
