package org.mb4j.controller.form;

import org.mb4j.controller.Request;
import org.mb4j.controller.utils.AttributeKey;
import org.mb4j.controller.utils.Attributes;

public class FormResponseRenderCurrentPage implements FormResponse {
  private final Attributes attributes;

  private FormResponseRenderCurrentPage(Attributes attributes) {
    this.attributes = attributes;
  }

  public static FormResponseRenderCurrentPage renderCurrentPage(Request request) {
    return new FormResponseRenderCurrentPage(request.attributes());
  }

  public <T> FormResponseRenderCurrentPage with(AttributeKey<? super T> key, T value) {
    attributes.setValueOf(key, value);
    return this;
  }
}
