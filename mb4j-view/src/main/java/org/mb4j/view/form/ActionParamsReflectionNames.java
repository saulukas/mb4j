package org.mb4j.view.form;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import org.mb4j.view.form.Form.NameResolver;

class ActionParamsReflectionNames {
  static Map<String, FormField> resolveNamesFor(Form actionParams, NameResolver nameResolver) {
    Map<String, FormField> paramMap = new HashMap<String, FormField>();
    Field[] fields = actionParams.getClass().getDeclaredFields();
    for (Field field : fields) {
      FormField param = resolveName(nameResolver, actionParams, field);
      if (param != null) {
        paramMap.put(param.name(), param);
      }
    }
    return paramMap;
  }

  private static FormField resolveName(NameResolver nameResolver, Object actionParams, Field field) {
    Object maybeParam = valueOf(actionParams, field);
    if (maybeParam instanceof FormField) {
      FormField param = (FormField) maybeParam;
      param.resolveNameTo(nameResolver.resolvedName(field.getName()));
      return param;
    }
    return null;
  }

  private static Object valueOf(Object actionParams, Field field) throws RuntimeException {
    try {
      return field.get(actionParams);
    } catch (IllegalAccessException ignore) {
      field.setAccessible(true);
      try {
        return field.get(actionParams);
      } catch (IllegalAccessException ex1) {
        throw new RuntimeException("Failed to accessed view action field: " + ex1);
      }
    }
  }
}
