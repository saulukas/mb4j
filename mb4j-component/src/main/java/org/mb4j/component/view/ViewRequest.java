package org.mb4j.component.view;

import org.mb4j.component.Component;
import org.mb4j.component.asset.AssetUrl4Response;
import org.mb4j.component.asset.AssetUrl4ResponseResolver;
import org.mb4j.component.form.Form;
import org.mb4j.component.form.Form4Response;
import org.mb4j.component.form.Form4ResponseResolver;
import org.mb4j.component.resource.Resources4Response;
import org.mb4j.component.resource.Resources4ResponseResolver;
import org.mb4j.component.url.BufferedUrlPathReader;
import org.mb4j.component.url.UrlPathReader;
import org.mb4j.component.utils.Attributes;

public class ViewRequest {
  private final ViewUrl viewUrl;
  private final UrlPathReader urlPathReader;
  private final Attributes attributes;
  private final AssetUrl4ResponseResolver assetUrlResolver;
  private final ViewUrl4ResponseResolver viewUrlResolver;
  private final Form4ResponseResolver formDataResolver;
  private final Resources4ResponseResolver resourcesResolver;

  public ViewRequest(
      ViewUrl viewUrl,
      Attributes attributes,
      AssetUrl4ResponseResolver assetUrlResolver,
      ViewUrl4ResponseResolver viewUrlResolver,
      Form4ResponseResolver formDataResolver,
      Resources4ResponseResolver resourcesResolver) {
    this.viewUrl = viewUrl;
    this.urlPathReader = BufferedUrlPathReader.of(viewUrl.params.path);
    this.attributes = attributes;
    this.assetUrlResolver = assetUrlResolver;
    this.viewUrlResolver = viewUrlResolver;
    this.formDataResolver = formDataResolver;
    this.resourcesResolver = resourcesResolver;
  }

  public ViewUrl viewUrl() {
    return viewUrl;
  }

  public boolean hasMorePathSegments() {
    return urlPathReader.hasMoreSegments();
  }

  public String readPathSegment() {
    return urlPathReader.readSegment();
  }

  public ViewUrl4Response resolve(ViewUrl url) {
    return viewUrlResolver.resolve(url);
  }

  public AssetUrl4Response resolveUrl(String assetUrl) {
    return assetUrlResolver.resolveUrl(assetUrl);
  }

  public Form4Response resolve(Form<?> formData) {
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
