package org.mb4j.controller.mapping;

import java.util.HashSet;
import java.util.Set;
import org.mb4j.controller.StatelessComponent;
import org.mb4j.controller.form.Form;

public class FormMappings {
  public static Set<Form> findFormsIn(StatelessComponent component) {
    Set<StatelessComponent> visited = new HashSet<>();
    Set<Form> result = new HashSet<>();
    findForms(component, visited, result);
    return result;
  }

  private static void findForms(
      StatelessComponent component,
      Set<StatelessComponent> visited,
      Set<Form> result) {
    if (visited.contains(component)) {
      return;
    }
    visited.add(component);
  }
}
