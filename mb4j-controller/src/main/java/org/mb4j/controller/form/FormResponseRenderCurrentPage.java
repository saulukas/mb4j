package org.mb4j.controller.form;

import org.mb4j.controller.utils.AttributeKey;
import org.mb4j.controller.utils.AttributesMap;

public class FormResponseRenderCurrentPage implements FormResponse {
  public final AttributesMap attributes = new AttributesMap();

  private FormResponseRenderCurrentPage() {
  }

  public static FormResponseRenderCurrentPage renderCurrentPage() {
    return new FormResponseRenderCurrentPage();
  }

  public <T> FormResponseRenderCurrentPage with(AttributeKey<? super T> key, T value) {
    attributes.setValueOf(key, value);
    return this;
  }
}
