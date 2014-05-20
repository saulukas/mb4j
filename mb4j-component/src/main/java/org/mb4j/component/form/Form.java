package org.mb4j.component.form;

import com.google.common.base.Objects;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.mb4j.component.ViewRequest;
import org.mb4j.component.form.field.FormFieldRecord;
import org.mb4j.component.utils.ReflectionUtils;
import static org.mb4j.component.utils.ReflectionUtils.getAnnotatedMethodNamesOf;
import static org.mb4j.component.utils.ReflectionUtils.getAnnotatedMethodsOf;

public class Form<T extends FormFieldRecord> {
  public final Class<T> fieldsClass;

  protected Form() {
    ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
    this.fieldsClass = (Class<T>) type.getActualTypeArguments()[0];
  }

  protected Form(Class<T> fieldsClass) {
    this.fieldsClass = fieldsClass;
  }

  public FormResponse handle(String actionName, ViewRequest request, T fields) {
    Method method = getActionMethodByName(actionName);
    try {
      method.setAccessible(true);
      return (FormResponse) method.invoke(this, request, fields);
    } catch (IllegalAccessException | IllegalArgumentException | SecurityException | InvocationTargetException ex) {
      throw new RuntimeException("Failed to invoke action method " + method + ": " + ex, ex);
    }
  }

  public Set<String> getActionNames() {
    return getAnnotatedMethodNamesOf(getClass(), Form.class, FormActionMethod.class);
  }

  public Collection<FormAction> getActions() {
    Collection<FormAction> actions = new ArrayList<>();
    for (String actionName : getActionNames()) {
      actions.add(actionForName(actionName));
    }
    return actions;
  }

  public T createEmptyFields() {
    return ReflectionUtils.createInstanceOf(fieldsClass);
  }

  public FormData<T> dataWith(T fields) {
    return new FormData(getClass(), fields, getActions());
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
    List<Method> methods = getAnnotatedMethodsOf(getClass(), Form.class, FormActionMethod.class);
    for (Method method : methods) {
      if (Objects.equal(name, method.getName())) {
        return method;
      }
    }
    return null;
  }
}
