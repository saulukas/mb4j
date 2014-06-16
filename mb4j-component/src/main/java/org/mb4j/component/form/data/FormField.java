package org.mb4j.component.form.data;

import org.mb4j.component.form.data.binding.FormFieldValueNode;
import static com.google.common.base.Strings.isNullOrEmpty;
import java.util.Map;

public class FormField extends AbstractFormData {
  public String value = "";
  public boolean required;
  public boolean enabled = true;
  public boolean visible = true;
  public Integer maxSize;
  public String error = "";

  protected FormField() {
    this(false);
  }

  protected FormField(boolean required) {
    this.required = required;
  }

  public static FormField requiredField() {
    return new FormField(true);
  }

  public static FormField optionalField() {
    return new FormField(false);
  }

  public FormField withMaxSize(int maxSize) {
    this.maxSize = maxSize;
    return this;
  }

  public FormField withValue(String value) {
    this.value = value;
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
