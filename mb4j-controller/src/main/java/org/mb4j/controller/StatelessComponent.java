package org.mb4j.controller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import org.mb4j.controller.form1.Form;

public class StatelessComponent {
  public Iterable<StatelessComponent> getChildren() {
    return collectChildren(new ArrayList<StatelessComponent>());
  }

  public Iterable<Form> getForms() {
    return collectForms(new ArrayList<Form>());
  }

  Collection<StatelessComponent> collectChildren(Collection<StatelessComponent> result) {
    collectChildren(this, result);
    return result;
  }

  static void collectChildren(StatelessComponent component, Collection<StatelessComponent> result) {
    Class klass = component.getClass();
    while (klass != null && StatelessComponent.class.isAssignableFrom(klass)) {
      Field[] declaredFields = klass.getDeclaredFields();
      for (Field declaredField : declaredFields) {
        Object fieldValue;
        try {
          declaredField.setAccessible(true);
          fieldValue = declaredField.get(component);
        } catch (Exception ex) {
          throw new RuntimeException("Failed to access field: " + declaredField);
        }
        if (fieldValue instanceof StatelessComponent) {
          result.add((StatelessComponent) fieldValue);
        }
      }
      klass = klass.getSuperclass();
    }
  }

  Collection<Form> collectForms(Collection<Form> result) {
    collectForms(this, result);
    return result;
  }

  static void collectForms(StatelessComponent component, Collection<Form> result) {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
