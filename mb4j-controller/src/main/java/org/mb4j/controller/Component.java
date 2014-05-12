package org.mb4j.controller;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.mb4j.controller.form.Form;
import org.mb4j.controller.resource.Resource;
import org.mb4j.controller.utils.ReflectionUtils;
import org.mb4j.controller.utils.SimpleClassName;

public class Component {
  public Set<Form> getFormsRecursively() {
    Set<Form> result = new HashSet<>();
    ReflectionUtils.collectRecursivelyFieldsOf(this, Component.class, Form.class, result);
    return result;
  }

  public Collection<Resource> getResources() {
  }

  public String componentTreeToString(String margin) {
    String result = SimpleClassName.of(getClass());
    Map<String, Component> children = getChildren();
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
      Component child = children.get(childName);
      result += "\n" + margin + "|";
      result += "\n" + margin + "+-- " + childName + " = "
          + child.componentTreeToString(margin + (namesIterator.hasNext() ? "|   " : "    "));
    }
    return result;
  }

  private Map<String, Form> getForms() {
    return ReflectionUtils.getFieldsOf(this, Component.class, Form.class);
  }

  private Map<String, Component> getChildren() {
    return ReflectionUtils.getFieldsOf(this, Component.class, Component.class);
  }
}
