package org.mb4j.controller.form;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.mb4j.controller.utils.ReflectionUtils;

public class Form {
  public Set<String> getActionNames() {
    List<Method> methods = ReflectionUtils.getAnnotatedMethodsOf(this, Form.class, FormAction.class);
    Set<String> actions = new TreeSet<>();
    for (Method method : methods) {
      actions.add(method.getName());
    }
    return actions;
  }
}
