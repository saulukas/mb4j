package org.mb4j.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.mb4j.controller.form.Form;
import org.mb4j.controller.resource.Resource;
import org.mb4j.controller.resource.ResourceMethod;
import org.mb4j.controller.utils.ReflectionUtils;
import static org.mb4j.controller.utils.ReflectionUtils.getAnnotatedMethodNamesOf;
import org.mb4j.controller.utils.SimpleClassName;

public class Component {
  public Set<Form> getFormsRecursively() {
    Set<Form> result = new HashSet<>();
    ReflectionUtils.collectRecursivelyFieldsOf(this, Component.class, Form.class, result);
    return result;
  }

  public Set<String> getResourceNames() {
    return getAnnotatedMethodNamesOf(getClass(), Component.class, ResourceMethod.class);
  }

  public Collection<Resource> getResources() {
    Collection<Resource> resources = new ArrayList<>();
    for (String name : getResourceNames()) {
      resources.add(resourceForName(name));
    }
    return resources;
  }

  protected Resource resourceForName(String name) {
    Resource resource = new Resource(name);
    resource.enabled = isResourceEnabled(name);
    resource.visible = isResourceVisible(name);
    return resource;
  }

  protected boolean isResourceEnabled(String name) {
    return true;
  }

  protected boolean isResourceVisible(String name) {
    return true;
  }

  public String componentTreeToString(String margin) {
    String result = SimpleClassName.of(getClass());
    Map<String, Component> children = getChildren();
    for (Form form : getForms().values()) {
      String formMargin = margin + (children.isEmpty() ? "    " : "|   ");
      result += "\n" + formMargin + "form: " + SimpleClassName.of(form.getClass())
          + " " + form.getActionNames();
    }
    Set<String> resourceNames = getResourceNames();
    if (!resourceNames.isEmpty()) {
      String resourceMargin = margin + (children.isEmpty() ? "    " : "|   ");
      result += "\n" + resourceMargin + "resources: " + resourceNames;
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
