package org.mb4j.controller.form;

import java.util.HashMap;
import java.util.Map;
import org.mb4j.controller.url.NamedParams;
import org.mb4j.controller.utils.ReflectionUtils;

public abstract class FormFieldBase {
  public Map<String, FormField> asFieldMap() {
    Map<String, FormField> formFields = new HashMap<>();
    collectFields("", formFields);
    return formFields;
  }

  public Map<String, FormFieldBase> getChildren() {
    return ReflectionUtils.getFieldsOf(this, FormFieldBase.class, FormFieldBase.class);
  }

  abstract void collectFields(String nameInParent, Map<String, FormField> fieldMap);

  public void setValuesFrom(NamedParams params) {
  }

  public abstract String toString(String margin);

  @Override
  public String toString() {
    return toString("");
  }
}
