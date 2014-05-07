package org.mb4j.controller.form;

import java.lang.reflect.Field;
import java.util.HashMap;
import org.mb4j.controller.utils.ReflectionUtils;

public class FormAction4Response extends HashMap<String, Object> {
  private static final long serialVersionUID = 1L;

  public FormAction4Response(String name, FormAction action) {
    put("name", name);
    put("enabled", action.enabled);
    put("visible", action.visible);
    initAddPropertiesFromSubclass(action);
  }

  private void initAddPropertiesFromSubclass(FormAction action) {
    Class subclass = action.getClass();
    while (!FormAction.class.equals(subclass)) {
      for (Field field : subclass.getDeclaredFields()) {
        put(field.getName(), ReflectionUtils.valueOf(field, action));
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
        + " Access attribute 'name' instead. Name='" + name() + "'");
  }
}
