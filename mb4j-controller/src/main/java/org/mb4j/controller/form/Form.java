package org.mb4j.controller.form;

import org.mb4j.controller.form.field.FormFieldRecord;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.mb4j.controller.ControllerRequest;
import org.mb4j.controller.utils.ReflectionUtils;

public class Form<T extends FormFieldRecord> {
  public final Class<T> fieldsClass;

  public Form() {
    this.fieldsClass = initFormDataClass();
  }

  private Class<T> initFormDataClass() {
    ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
    return (Class<T>) type.getActualTypeArguments()[0];
  }

  public FormResponse handle(ControllerRequest request, String actionName, T fields) {
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

  public T createEmptyFields() {
    try {
      return fieldsClass.newInstance();
    } catch (Exception ex) {
      throw new RuntimeException("Failed to create empty " + fieldsClass + ": " + ex, ex);
    }
  }

  public FormData<T> dataWith(T fields) {
    return new FormData(getClass(), fields, getActionNames());
  }
}
