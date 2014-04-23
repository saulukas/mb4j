package org.mb4j.controller.form;

public class FormField {
  public String value;
  public boolean required;
  public String errorMessage;
  public Integer maxSize;

  private FormField(boolean required) {
    this.required = required;
  }

  public static FormField createRequiredField() {
    return new FormField(false);
  }

  public static FormField createOptionalField() {
    return new FormField(false);
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
}
