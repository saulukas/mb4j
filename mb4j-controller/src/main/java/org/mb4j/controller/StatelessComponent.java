package org.mb4j.controller;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.mb4j.controller.form.Form;
import org.mb4j.controller.utils.ReflectionUtils;
import org.mb4j.controller.utils.SimpleClassName;

public class StatelessComponent {
  public Map<String, StatelessComponent> getChildren() {
    return ReflectionUtils.getFieldsOf(this, StatelessComponent.class, StatelessComponent.class);
  }

  public Map<String, Form> getForms() {
    return ReflectionUtils.getFieldsOf(this, StatelessComponent.class, Form.class);
  }

  public Set<Form> getFormsRecursively() {
    Set<Form> result = new HashSet<>();
    ReflectionUtils.collectRecursivelyFieldsOf(this, StatelessComponent.class, Form.class, result);
    return result;
  }

  public String componentTreeToString(String margin) {
    String result = SimpleClassName.of(getClass());
    Map<String, StatelessComponent> children = getChildren();
    for (Form form : getForms().values()) {
      String formMargin = margin + (children.isEmpty() ? "    " : "|   ");
      result += "\n" + formMargin + "form: " + SimpleClassName.of(form.getClass())
          + " " + form.getActionNames();
    }
    if (children.isEmpty()) {
      return result;
    }
    Iterator<String> namesIterator = children.keySet().iterator();
    while (namesIterator.hasNext()) {
      String childName = namesIterator.next();
      StatelessComponent child = children.get(childName);
      result += "\n" + margin + "|";
      result += "\n" + margin + "+-- " + childName + " = "
          + child.componentTreeToString(margin + (namesIterator.hasNext() ? "|   " : "    "));
    }
    return result;
  }
}
