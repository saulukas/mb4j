package org.mb4j.controller.form;

public class FormField4Request {
  public final String name;
  public final String value;
  public final boolean required;
  public final boolean hasError;
  public final String errorMessage;
  public final Integer maxSize;

  public FormField4Request(String name, String value, boolean required, boolean hasError, String errorMessage, Integer maxSize) {
    this.name = name;
    this.value = value;
    this.required = required;
    this.hasError = hasError;
    this.errorMessage = errorMessage;
    this.maxSize = maxSize;
  }
}
