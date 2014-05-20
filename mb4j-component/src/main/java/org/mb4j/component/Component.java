package org.mb4j.component;

import org.mb4j.component.view.ViewRequest;
import org.mb4j.component.view.ViewResponse;
import com.google.common.base.Objects;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.mb4j.component.form.Form;
import org.mb4j.component.resource.Resource;
import org.mb4j.component.resource.ResourceMethod;
import org.mb4j.component.utils.ReflectionUtils;
import static org.mb4j.component.utils.ReflectionUtils.getAnnotatedMethodNamesOf;
import static org.mb4j.component.utils.ReflectionUtils.getAnnotatedMethodsOf;
import org.mb4j.component.utils.SimpleClassName;

public class Component {
  public void addFormsRecursively(Collection<Form> result) {
    ReflectionUtils.collectRecursivelyNonStaticFieldsOf(this, Component.class, Form.class, result);
  }

  public void addSubtree(Collection<Component> result) {
    result.add(this);
    ReflectionUtils.collectRecursivelyNonStaticFieldsOf(this, Component.class, Component.class, result);
  }

  public boolean hasResources() {
    return !getResourceNames().isEmpty();
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

  public void serveResource(String resourceName, ViewRequest request, ViewResponse response) throws IOException {
    Method method = getResourceMethodByName(resourceName);
    try {
      method.setAccessible(true);
      method.invoke(this, request, response);
    } catch (IllegalAccessException | IllegalArgumentException | SecurityException | InvocationTargetException ex) {
      throw new RuntimeException("Failed to invoke resource method " + method + ": " + ex, ex);
    }
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
    return ReflectionUtils.getNonStaticFieldsOf(this, Component.class, Form.class);
  }

  private Map<String, Component> getChildren() {
    return ReflectionUtils.getNonStaticFieldsOf(this, Component.class, Component.class);
  }

  private Method getResourceMethodByName(String name) {
    List<Method> methods = getAnnotatedMethodsOf(getClass(), Component.class, ResourceMethod.class);
    for (Method method : methods) {
      if (Objects.equal(name, method.getName())) {
        return method;
      }
    }
    return null;
  }
}
