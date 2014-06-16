package org.mb4j.component.form.response;

import org.mb4j.component.view.ViewRequest;
import org.mb4j.component.utils.AttributeKey;
import org.mb4j.component.utils.Attributes;

public class FormResponseRenderCurrentPage implements FormResponse {
  private final Attributes attributes;

  private FormResponseRenderCurrentPage(Attributes attributes) {
    this.attributes = attributes;
  }

  public static FormResponseRenderCurrentPage renderCurrentPage(ViewRequest request) {
    return new FormResponseRenderCurrentPage(request.attributes());
  }

  public <T> FormResponseRenderCurrentPage with(AttributeKey<? super T> key, T value) {
    attributes.setValueOf(key, value);
    return this;
  }
}
