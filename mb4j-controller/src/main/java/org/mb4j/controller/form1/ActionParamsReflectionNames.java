package org.mb4j.controller.form1;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import org.mb4j.controller.form1.Form1.NameResolver;

class ActionParamsReflectionNames {
  static Map<String, FormField1> resolveNamesFor(Form1 actionParams, NameResolver nameResolver) {
    Map<String, FormField1> paramMap = new HashMap<>();
    Field[] fields = actionParams.getClass().getDeclaredFields();
    for (Field field : fields) {
      FormField1 param = resolveName(nameResolver, actionParams, field);
      if (param != null) {
        paramMap.put(param.name(), param);
      }
    }
    return paramMap;
  }

  private static FormField1 resolveName(NameResolver nameResolver, Object actionParams, Field field) {
    Object maybeParam = valueOf(actionParams, field);
    if (maybeParam instanceof FormField1) {
      FormField1 param = (FormField1) maybeParam;
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
