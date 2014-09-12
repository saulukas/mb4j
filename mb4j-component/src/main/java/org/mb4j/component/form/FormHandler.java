package org.mb4j.component.form;

import com.google.common.base.Objects;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.mb4j.component.form.action.FormAction;
import org.mb4j.component.form.action.FormActionMethod;
import org.mb4j.component.form.data.FormData;
import org.mb4j.component.form.response.FormResponse;
import org.mb4j.component.utils.ReflectionUtils;
import static org.mb4j.component.utils.ReflectionUtils.getAnnotatedMethodNamesOf;
import static org.mb4j.component.utils.ReflectionUtils.getAnnotatedMethodsOf;
import org.mb4j.component.utils.SimpleClassName;
import org.mb4j.component.Request;

public class FormHandler<T extends FormData> {
  public final Class<T> dataClass;

  protected FormHandler() {
    ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
    this.dataClass = (Class<T>) type.getActualTypeArguments()[0];
  }

  protected FormHandler(Class<T> dataClass) {
    this.dataClass = dataClass;
  }

  public FormResponse handle(String actionName, Request request, T data) {
    Method method = getActionMethodByName(actionName);
    try {
      method.setAccessible(true);
      return (FormResponse) method.invoke(this, request, data);
    } catch (IllegalAccessException | IllegalArgumentException | SecurityException | InvocationTargetException ex) {
      throw new RuntimeException("Failed to invoke action method " + method + ": " + ex, ex);
    }
  }

  public Set<String> getActionNames() {
    return getAnnotatedMethodNamesOf(getClass(), FormHandler.class, FormActionMethod.class);
  }

  public Collection<FormAction> getActions() {
    Collection<FormAction> actions = new ArrayList<>();
    for (String actionName : getActionNames()) {
      actions.add(actionForName(actionName));
    }
    return actions;
  }

  public T createEmptyFields() {
    return ReflectionUtils.createInstanceOf(dataClass);
  }

  public Form<T> formWith(T data) {
    return new Form(getClass(), data, getActions());
  }

  protected FormAction actionForName(String name) {
    FormAction action = new FormAction(name);
    action.enabled = isActionEnabled(name);
    action.visible = isActionVisible(name);
    return action;
  }

  protected boolean isActionEnabled(String name) {
    return true;
  }

  protected boolean isActionVisible(String name) {
    return true;
  }

  private Method getActionMethodByName(String name) {
    List<Method> methods = getAnnotatedMethodsOf(getClass(), FormHandler.class, FormActionMethod.class);
    for (Method method : methods) {
      if (Objects.equal(name, method.getName())) {
        return method;
      }
    }
    return null;
  }

  @Override
  public String toString() {
    return SimpleClassName.of(getClass()) + "(" + SimpleClassName.of(dataClass) + ") "
        + getActionNames();
  }
}
