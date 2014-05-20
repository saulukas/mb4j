package org.mb4j.component.view;

import org.mb4j.component.Component;
import org.mb4j.component.asset.AssetUrl4Response;
import org.mb4j.component.asset.AssetUrl4ResponseResolver;
import org.mb4j.component.form.FormData;
import org.mb4j.component.form.FormData4Response;
import org.mb4j.component.form.FormData4ResponseResolver;
import org.mb4j.component.resource.Resources4Response;
import org.mb4j.component.resource.Resources4ResponseResolver;
import org.mb4j.component.url.BufferedUrlPathReader;
import org.mb4j.component.url.UrlPathReader;
import org.mb4j.component.utils.Attributes;

public class ViewRequest {
  private final ViewUrl url;
  private final UrlPathReader urlPathReader;
  private final Attributes attributes;
  private final AssetUrl4ResponseResolver assetUrlResolver;
  private final ViewUrl4ResponseResolver controllerUrlResolver;
  private final FormData4ResponseResolver formDataResolver;
  private final Resources4ResponseResolver resourcesResolver;

  public ViewRequest(
      ViewUrl url,
      Attributes attributes,
      AssetUrl4ResponseResolver assetUrlResolver,
      ViewUrl4ResponseResolver controllerUrlResolver,
      FormData4ResponseResolver formDataResolver,
      Resources4ResponseResolver resourcesResolver) {
    this.url = url;
    this.urlPathReader = BufferedUrlPathReader.of(url.params.path);
    this.attributes = attributes;
    this.assetUrlResolver = assetUrlResolver;
    this.controllerUrlResolver = controllerUrlResolver;
    this.formDataResolver = formDataResolver;
    this.resourcesResolver = resourcesResolver;
  }

  public ViewUrl url() {
    return url;
  }

  public boolean hasMorePathSegments() {
    return urlPathReader.hasMoreSegments();
  }

  public String readPathSegment() {
    return urlPathReader.readSegment();
  }

  public ViewUrl4Response resolve(ViewUrl url) {
    return controllerUrlResolver.resolve(url);
  }

  public AssetUrl4Response resolveUrl(String assetUrl) {
    return assetUrlResolver.resolveUrl(assetUrl);
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
