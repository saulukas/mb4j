package org.mb4j.component.resource;

import java.lang.reflect.Field;
import java.util.HashMap;
import org.mb4j.component.utils.ReflectionUtils;

public class Resource4Response extends HashMap<String, Object> {
  private static final long serialVersionUID = 1L;

  public Resource4Response(String url, Resource resource) {
    put("url", url);
    put("name", resource.name);
    put("enabled", resource.enabled);
    put("visible", resource.visible);
    initAddPropertiesFromSubclass(resource);
  }

  private void initAddPropertiesFromSubclass(Resource resource) {
    Class subclass = resource.getClass();
    while (!Resource.class.equals(subclass)) {
      for (Field field : subclass.getDeclaredFields()) {
        put(field.getName(), ReflectionUtils.valueOf(field, resource));
      }
      subclass = subclass.getSuperclass();
    }
  }

  public String name() {
    return (String) get("name");
  }

  public String url() {
    return (String) get("url");
  }

  @Override
  public String toString() {
    throw new UnsupportedOperationException(
        getClass().getSimpleName() + ".toString() is not supported."
        + " Access attribute 'url' instead. url='" + url() + "'");
  }
}
