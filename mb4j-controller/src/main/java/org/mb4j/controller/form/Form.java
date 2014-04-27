package org.mb4j.controller.form;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.mb4j.controller.ControllerRequest;
import org.mb4j.controller.utils.ReflectionUtils;

public class Form<T extends FormData> {
  public final Class<T> dataClass;

  public Form() {
    this.dataClass = initFormDataClass();
  }

  private Class<T> initFormDataClass() {
    ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
    return (Class<T>) type.getActualTypeArguments()[0];
  }

  public FormResponse handle(ControllerRequest request, String actionName, T data) {
    System.out.println("action=" + actionName);
    return null;
  }

  public Set<String> getActionNames() {
    return getActionNames(getClass());
  }

  static Set<String> getActionNames(Class klass) {
    List<Method> methods = ReflectionUtils.getAnnotatedMethodsOf(klass, Form.class, FormAction.class);
    Set<String> actions = new TreeSet<>();
    for (Method method : methods) {
      actions.add(method.getName());
    }
    return actions;
  }

  public T createEmptyData() {
    try {
      return dataClass.newInstance();
    } catch (Exception ex) {
      throw new RuntimeException("Failed to create empty data for " + dataClass + ": " + ex, ex);
    }
  }
}
