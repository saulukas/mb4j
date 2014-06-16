package org.mb4j.example.servlet.util;

import org.mb4j.component.form.data.FormField;

public class FormFieldWithLabel extends FormField {
  public String label;

  public static FormFieldWithLabel requiredFieldWithLabel(String label) {
    FormFieldWithLabel field = new FormFieldWithLabel();
    field.required = true;
    field.label = label;
    return field;
  }

  public static FormFieldWithLabel optionalFieldWithLabel(String label) {
    FormFieldWithLabel field = new FormFieldWithLabel();
    field.required = false;
    field.label = label;
    return field;
  }
}
