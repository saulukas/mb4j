package org.mb4j.controller.mapping;

import org.mb4j.controller.utils.SimpleClassName;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.mb4j.controller.Controller;
import org.mb4j.controller.form.FormAction2;

public class FormActionMappings {
  private final Map<Class<? extends FormAction2>, String> class2name = new HashMap<>();
  private final Map<String, FormAction2> name2action = new TreeMap<>();

  public FormActionMappings(
      Iterable<Class<? extends Controller>> controllerClasses,
      InstanceProviderByClass instanceProvider) {
    for (FormAction2 action : initFormActionsFrom(controllerClasses, instanceProvider)) {
      String name = "" + class2name.size();
      while (name.length() < 4) {
        name = "0" + name;
      }
      name = "fa" + name;
      class2name.put(action.getClass(), name);
      name2action.put(name, action);
    }
  }

  private Iterable<FormAction2> initFormActionsFrom(
      Iterable<Class<? extends Controller>> controllerClasses,
      InstanceProviderByClass instanceProvider) {
    List<Class<? extends FormAction2>> actionClasses = new ArrayList<>();
    Set<Class<?>> visitedClasses = new HashSet<>();
    for (Class<?> controllerClass : controllerClasses) {
      try {
        collectFormActionsFor(controllerClass, false, visitedClasses, actionClasses);
      } catch (Exception ex) {
        throw new RuntimeException(ex);
      }
    }
    List<FormAction2> actions = new ArrayList<>();
    for (Class<? extends FormAction2> actionClass : actionClasses) {
      actions.add(instanceProvider.instanceOf(actionClass));
    }
    return actions;
  }

  private void collectFormActionsFor(
      Class<?> klass,
      boolean considerThisKlass,
      Set<Class<?>> visitedClasses,
      List<Class<? extends FormAction2>> result) throws Exception {
    if (visitedClasses.contains(klass)) {
      return;
    }
    visitedClasses.add(klass);
    if (considerThisKlass && FormAction2.class.isAssignableFrom(klass)) {
      result.add((Class<? extends FormAction2>) klass);
    }
    for (Field declaredField : klass.getDeclaredFields()) {
      collectFormActionsFor(declaredField.getType(), true, visitedClasses, result);
    }
    if (klass.getSuperclass() != null) {
      collectFormActionsFor(klass.getSuperclass(), false, visitedClasses, result);
    }
  }

  @Override
  public String toString() {
    String result = "Action mappings " + name2action.size() + ":";
    for (Map.Entry<String, FormAction2> entry : name2action.entrySet()) {
      result += "\n    " + entry.getKey() + " -> " + SimpleClassName.of(entry.getValue().getClass());
    }
    return result;
  }
}
