package org.mb4j.controller.form;

import java.util.HashMap;
import java.util.Map;
import org.mb4j.controller.utils.AttributeKey;

public class FormResponseRenderCurrentPage implements FormResponse {
  public final Map<AttributeKey, Object> attributes = new HashMap<>();

  private FormResponseRenderCurrentPage() {
  }

  public static FormResponseRenderCurrentPage renderCurrentPage() {
    return new FormResponseRenderCurrentPage();
  }

  public <T> FormResponseRenderCurrentPage with(AttributeKey<? super T> key, T value) {
    attributes.put(key, value);
    return this;
  }
}
