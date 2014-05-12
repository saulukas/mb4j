package org.mb4j.controller.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class ReflectionUtils {
  public static <T> Map<String, T> getFieldsOf(Object object, Class<?> baseClass, Class<T> type) {
    TreeMap<String, T> result = new TreeMap<>();
    Class klass = object.getClass();
    while (klass != null && baseClass.isAssignableFrom(klass)) {
      Field[] declaredFields = klass.getDeclaredFields();
      for (Field declaredField : declaredFields) {
        Object fieldValue;
        try {
          declaredField.setAccessible(true);
          fieldValue = declaredField.get(object);
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

  public static <T> void collectRecursivelyFieldsOf(Object object, Class<?> baseClass, Class<T> type, Collection<T> result) {
    Class klass = object.getClass();
    while (klass != null && baseClass.isAssignableFrom(klass)) {
      Field[] declaredFields = klass.getDeclaredFields();
      for (Field declaredField : declaredFields) {
        Object fieldValue;
        try {
          declaredField.setAccessible(true);
          fieldValue = declaredField.get(object);
        } catch (Exception ex) {
          throw new RuntimeException("Failed to access field: " + declaredField);
        }
        if (type.isInstance(fieldValue)) {
          result.add((T) fieldValue);
        } else if (baseClass.isInstance(fieldValue)) {
          collectRecursivelyFieldsOf(fieldValue, baseClass, type, result);
        }
      }
      klass = klass.getSuperclass();
    }
  }

  public static List<Method> getAnnotatedMethodsOf(Class klass, Class<?> baseClass, Class<? extends Annotation> anotation) {
    List<Method> result = new ArrayList<>();
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

  public static Set<String> getAnnotatedMethodNamesOf(
      Class klass,
      Class<?> baseClass,
      Class<? extends Annotation> anotation
  ) {
    List<Method> methods = ReflectionUtils.getAnnotatedMethodsOf(klass, baseClass, anotation);
    Set<String> orderedMethodNames = new TreeSet<>();
    for (Method method : methods) {
      orderedMethodNames.add(method.getName());
    }
    return orderedMethodNames;
  }

  public static Object valueOf(Field field, Object instance) {
    try {
      field.setAccessible(true);
      return field.get(instance);
    } catch (Exception ex) {
      throw new RuntimeException("Failed to access field: " + field);
    }
  }

  public static <T> T createInstanceOf(Class<T> klass) {
    try {
      Constructor<T> constructor = klass.getDeclaredConstructor();
      constructor.setAccessible(true);
      return constructor.newInstance();
    } catch (Exception ex) {
      throw new RuntimeException("Failed to create instance of " + klass + ": " + ex, ex);
    }
  }
}
