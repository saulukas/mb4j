package org.mb4j.controller.sitemap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.mb4j.controller.Controller;
import org.mb4j.controller.form.Form;
import org.mb4j.controller.utils.SimpleClassName;

public class FormMappings implements MapFormClass2Name, MapFormName2Form {
  private final Map<Class<? extends Form>, String> class2name = new HashMap<>();
  private final Map<String, Form> name2form = new TreeMap<>();

  public FormMappings(Set<Controller> controllers) {
    Set<Form> forms = new HashSet<>();
    for (Controller controller : controllers) {
      forms.addAll(controller.getFormsRecursively());
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
        name = name + postfix;
        name2form.put(name, form);
        class2name.put(form.getClass(), name);
      }
    }
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
