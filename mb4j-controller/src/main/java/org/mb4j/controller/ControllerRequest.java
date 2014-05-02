package org.mb4j.controller;

import com.google.common.base.Optional;
import java.util.HashMap;
import java.util.Map;
import org.mb4j.controller.form.FormData;
import org.mb4j.controller.form.FormData4Request;
import org.mb4j.controller.form.FormData4RequestResolver;
import static org.mb4j.controller.url.BufferedUrlPathReader.bufferedReaderOf;
import org.mb4j.controller.url.ControllerUrl;
import org.mb4j.controller.url.ControllerUrl4Request;
import org.mb4j.controller.url.ControllerUrl4RequestResolver;
import org.mb4j.controller.url.Url4Request;
import org.mb4j.controller.url.Url4RequestResolver;
import org.mb4j.controller.url.UrlPathReader;
import org.mb4j.controller.utils.AttributeKey;

public class ControllerRequest {
  private final ControllerUrl url;
  private final UrlPathReader urlPathReader;
  private final Map<AttributeKey, Object> attributes = new HashMap<>();
  private final Url4RequestResolver urlResolver;
  private final ControllerUrl4RequestResolver controllerUrlResolver;
  private final FormData4RequestResolver formDataResolver;

  public ControllerRequest(
      ControllerUrl url,
      Url4RequestResolver urlResolver,
      ControllerUrl4RequestResolver controllerUrlResolver,
      FormData4RequestResolver formDataResolver) {
    this.url = url;
    this.urlPathReader = bufferedReaderOf(url.params.path);
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

  public void putAttributes(Map<AttributeKey, Object> attributes) {
    this.attributes.putAll(attributes);
  }

  public <T> void putAttribute(AttributeKey<? super T> key, T value) {
    this.attributes.put(key, value);
  }

  public <T> Optional<T> attributeFor(AttributeKey<T> key) {
    return Optional.fromNullable((T) attributes.get(key));
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + "[" + urlPathReader + "]";
  }
}
