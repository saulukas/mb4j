package org.mb4j.component.sitemap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.mb4j.component.Component;
import org.mb4j.component.View;
import org.mb4j.component.form.Form;
import org.mb4j.component.utils.SimpleClassName;

public class FormMappings implements MapFormClass2Name, MapFormName2Form {
  private final Map<Class<? extends Form>, String> class2name = new HashMap<>();
  private final Map<String, Form> name2form = new TreeMap<>();

  public FormMappings(Set<View> controllers) {
    Set<Form> forms = new HashSet<>();
    for (View controller : controllers) {
      if (controller instanceof Component) {
        Component component = (Component) controller;
        component.addFormsRecursively(forms);
      }
    }
    for (Form form : forms) {
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
  public String formNameOf(Class<? extends Form> formClass) {
    String name = class2name.get(formClass);
    if (name == null) {
      throw new RuntimeException("No mapping found for Form class: " + formClass);
    }
    return name;
  }

  @Override
  public Form formFor(String formName) {
    Form form = name2form.get(formName);
    if (form == null) {
      throw new RuntimeException("No mapping found for Form name: " + formName);
    }
    return form;
  }

  @Override
  public String toString() {
    String result = "Form count: " + name2form.size();
    for (Map.Entry<String, Form> entry : name2form.entrySet()) {
      Form form = entry.getValue();
      result += "\n    " + entry.getKey() + " -> " + form.getClass().getName()
          + " " + form.getActionNames();
    }
    return result;
  }
}
