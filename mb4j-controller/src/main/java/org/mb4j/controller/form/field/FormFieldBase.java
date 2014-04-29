package org.mb4j.controller.form.field;

import java.util.HashMap;
import java.util.Map;

public abstract class FormFieldBase {
  public Map<String, FormField> asFieldMap() {
    Map<String, FormField> formFields = new HashMap<>();
    collectFields("", formFields);
    return formFields;
  }

  abstract void collectFields(String nameInParent, Map<String, FormField> fieldMap);

  public void moveValuesFrom(Map<String, String> name2value) {
  }

  public abstract String toString(String margin);

  @Override
  public String toString() {
    return toString("");
  }
}
