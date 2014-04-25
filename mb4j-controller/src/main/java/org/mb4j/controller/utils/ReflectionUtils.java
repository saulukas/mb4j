package org.mb4j.controller.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ReflectionUtils {
  public static <T> Map<String, T> getFieldsOf(Object object, Class<?> baseClass, Class<T> type) {
    TreeMap<String, T> result = new TreeMap<>();
    Object superObject = object;
    Class klass = superObject.getClass();
    while (klass != null && baseClass.isAssignableFrom(klass)) {
      Field[] declaredFields = klass.getDeclaredFields();
      for (Field declaredField : declaredFields) {
        Object fieldValue;
        try {
          declaredField.setAccessible(true);
          fieldValue = declaredField.get(superObject);
        } catch (Exception ex) {
          throw new RuntimeException("Failed to access field: " + declaredField);
        }
        if (type.isInstance(fieldValue)) {
          result.put(declaredField.getName(), (T) fieldValue);
        }
      }
      klass = klass.getSuperclass();
    }
    return result;
  }

  public static List<Method> getAnnotatedMethodsOf(Object object, Class<?> baseClass, Class<? extends Annotation> anotation) {
    List<Method> result = new ArrayList<>();
    Class klass = object.getClass();
    while (klass != null && baseClass.isAssignableFrom(klass)) {
      Method[] declaredMethods = klass.getDeclaredMethods();
      for (Method declaredMethod : declaredMethods) {
        if (declaredMethod.getAnnotation(anotation) != null) {
          result.add(declaredMethod);
        }
      }
      klass = klass.getSuperclass();
    }
    return result;
  }
}
