package org.mb4j.component.viewmap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.mb4j.component.Component;
import org.mb4j.component.view.View;
import org.mb4j.component.form.FormHandler;
import org.mb4j.component.utils.SimpleClassName;

public class FormMappings implements MapFormClass2Name, MapFormName2Form {
  private final Map<Class<? extends FormHandler>, String> class2name = new HashMap<>();
  private final Map<String, FormHandler> name2form = new TreeMap<>();

  public FormMappings(Set<View> views) {
    Set<FormHandler> forms = new HashSet<>();
    for (View view : views) {
      if (view instanceof Component) {
        Component component = (Component) view;
        component.addFormsRecursively(forms);
      }
    }
    for (FormHandler form : forms) {
      if (!class2name.containsKey(form.getClass())) {
        String name = SimpleClassName.of(form.getClass());
        String postfix = "";
        int counter = 0;
        while (name2form.containsKey(name + postfix)) {
          counter += 1;
          postfix = "" + counter;
        }
        name += postfix;
        name2form.put(name, form);
        class2name.put(form.getClass(), name);
      }
    }
  }

  public boolean isEmpty() {
    return class2name.isEmpty();
  }

  @Override
  public String formNameOf(Class<? extends FormHandler> formClass) {
    String name = class2name.get(formClass);
    if (name == null) {
      throw new RuntimeException("No mapping found for Form class: " + formClass);
    }
    return name;
  }

  @Override
  public FormHandler formFor(String formName) {
    FormHandler form = name2form.get(formName);
    if (form == null) {
      throw new RuntimeException("No mapping found for Form name: " + formName);
    }
    return form;
  }

  @Override
  public String toString() {
    String result = "Form count: " + name2form.size();
    for (Map.Entry<String, FormHandler> entry : name2form.entrySet()) {
      FormHandler form = entry.getValue();
      result += "\n    " + entry.getKey() + " -> " + form.getClass().getName()
          + " " + form.getActionNames();
    }
    return result;
  }
}
