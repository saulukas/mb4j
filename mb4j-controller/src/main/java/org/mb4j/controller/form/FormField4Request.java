package org.mb4j.controller.form;

import java.lang.reflect.Field;
import java.util.HashMap;
import org.mb4j.controller.form.field.FormField;
import org.mb4j.controller.utils.ReflectionUtils;

public class FormField4Request extends HashMap<String, Object> {
  private static final long serialVersionUID = 1L;

  public FormField4Request(String name, FormField field) {
    put("name", name);
    put("value", field.value);
    put("required", field.required);
    put("enabled", field.enabled);
    put("visible", field.visible);
    put("hasError", field.hasErrors());
    put("error", field.error);
    put("maxSize", field.maxSize);
    initAddPropertiesFromSubclass(field);
  }

  private void initAddPropertiesFromSubclass(FormField formField) {
    Class subclass = formField.getClass();
    while (!FormField.class.equals(subclass)) {
      for (Field field : subclass.getDeclaredFields()) {
        put(field.getName(), ReflectionUtils.valueOf(field, formField));
      }
      subclass = subclass.getSuperclass();
    }
  }

  public String name() {
    return (String) get("name");
  }

  @Override
  public String toString() {
    throw new UnsupportedOperationException(getClass().getSimpleName() + ".toString() is not supported."
        + " Access attribute 'name', 'value' or others instead. Name4Request='" + name() + "'");
  }
}
