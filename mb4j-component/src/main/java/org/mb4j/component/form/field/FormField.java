package org.mb4j.component.form.field;

import static com.google.common.base.Strings.isNullOrEmpty;
import java.util.Map;

public class FormField extends FormFieldBase {
  public String value = "";
  public boolean required;
  public boolean enabled = true;
  public boolean visible = true;
  public Integer maxSize;
  public String error = "";

  protected FormField() {
    this(false, "");
  }

  protected FormField(boolean required, String value) {
    this.required = required;
    this.value = value;
  }

  public static FormField createRequiredField() {
    return createRequiredField("");
  }

  public static FormField createOptionalField() {
    return createOptionalField("");
  }

  public static FormField createRequiredField(String value) {
    return new FormField(true, value);
  }

  public static FormField createOptionalField(String value) {
    return new FormField(false, value);
  }

  public FormField withMaxSize(int maxSize) {
    this.maxSize = maxSize;
    return this;
  }

  @Override
  public boolean hasErrors() {
    return !isNullOrEmpty(error);
  }

  public FormField setErrorIf(boolean isError, String error) {
    if (isError) {
      this.error = error;
    }
    return this;
  }

  @Override
  void collectFields(String nameInParent, Map<String, FormField> fieldMap) {
    fieldMap.put(nameInParent, this);
  }

  @Override
  void setValuesFrom(FormFieldValueNode node) {
    value = node.value;
  }

  @Override
  public String toString(String margin) {
    return ""
        + (required ? "(!) " : "")
        + (maxSize != null ? "max" + maxSize + " " : "")
        + (value == null ? "null" : "[" + value + "]")
        + (hasErrors() ? " Error: " + error : "");
  }
}
