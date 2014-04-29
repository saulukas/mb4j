package org.mb4j.controller.form.field;

import java.util.Map;

public final class FormField extends FormFieldBase {
  public String value;
  public boolean required;
  public String errorMessage;
  public Integer maxSize;

  private FormField(boolean required, String value) {
    this.required = required;
    this.value = value;
  }

  public static FormField createRequiredField() {
    return createRequiredField(null);
  }

  public static FormField createRequiredField(String value) {
    return new FormField(true, value);
  }

  public static FormField createOptionalField() {
    return createOptionalField(null);
  }

  public static FormField createOptionalField(String value) {
    return new FormField(false, value);
  }

  public FormField withMaxSize(int maxSize) {
    this.maxSize = maxSize;
    return this;
  }

  public FormField withErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
    return this;
  }

  public boolean hasError() {
    return errorMessage != null;
  }

  @Override
  void collectFields(String nameInParent, Map<String, FormField> fieldMap) {
    fieldMap.put(nameInParent, this);
  }

  @Override
  public String toString(String margin) {
    return ""
        + (required ? "(!) " : "")
        + (maxSize != null ? "max" + maxSize + " " : "")
        + (value == null ? "null" : "[" + value + "]")
        + (hasError() ? " Error: " + errorMessage : "");
  }
}
