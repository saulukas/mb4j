package org.mb4j.controller.form;

import org.mb4j.controller.form.field.FormField;

public class FormField4Request {
  public final String name;
  public final String value;
  public final boolean required;
  public final boolean hasError;
  public final String errorMessage;
  public final Integer maxSize;

  public FormField4Request(String name, FormField field) {
    this(name, field.value, field.required, field.hasError(), field.errorMessage, field.maxSize);
  }

  public FormField4Request(String name, String value, boolean required, boolean hasError, String errorMessage, Integer maxSize) {
    this.name = name;
    this.value = value;
    this.required = required;
    this.hasError = hasError;
    this.errorMessage = errorMessage;
    this.maxSize = maxSize;
  }

  @Override
  public String toString() {
    throw new UnsupportedOperationException(getClass().getSimpleName() + ".toString() is not supported."
        + " Access attribute 'name', 'value' or others instead. Name4Request='" + name + "'");
  }
}
