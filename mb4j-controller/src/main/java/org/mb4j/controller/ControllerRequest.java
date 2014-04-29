package org.mb4j.controller;

import java.util.Map;
import org.mb4j.controller.form.FormData;
import org.mb4j.controller.form.FormData4Request;
import org.mb4j.controller.form.field.FormField;
import org.mb4j.controller.form.field.FormFieldRecord;
import org.mb4j.controller.form1.Form1;
import static org.mb4j.controller.url.BufferedUrlPathReader.bufferedReaderOf;
import org.mb4j.controller.url.ControllerUrl;
import org.mb4j.controller.url.ControllerUrl4Request;
import org.mb4j.controller.url.NamedParams;
import org.mb4j.controller.url.Url4Request;
import org.mb4j.controller.url.UrlPathReader;

public abstract class ControllerRequest {
  private final ControllerUrl url;
  private final NamedParams postParams;
  private final UrlPathReader urlPathReader;
  public final Form1.NameResolver actionParamNameResolver;

  public ControllerRequest(
      ControllerUrl url,
      NamedParams postParams,
      Form1.NameResolver actionParamNameResolver) {
    this.url = url;
    this.postParams = postParams;
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

  public abstract FormData4Request resolve(FormData<?> formData);

  public void fill(FormFieldRecord fields) {
    Map<String, FormField> fieldMap = fields.asFieldMap();
    for (String paramName : postParams.names()) {
      if (fieldMap.containsKey(paramName)) {
        fieldMap.get(paramName).value = postParams.valueOf(paramName);
      }
    }
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + "[" + urlPathReader + "]";
  }
}
