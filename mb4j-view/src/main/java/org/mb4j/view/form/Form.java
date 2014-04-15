package org.mb4j.view.form;

import java.util.Collections;
import java.util.Map;

public class Form {
  private Map<String, FormField> name2param = null;

  public interface NameResolver {
    String resolvedName(String fieldName);
  }

  public FormField fieldBy(String name) {
    return name2param.get(name);
  }

  public boolean hasNames() {
    return name2param != null;
  }

  void resolveFieldNames(NameResolver nameResolver) {
    if (!hasNames()) {
      name2param = Collections.unmodifiableMap(
          ActionParamsReflectionNames.resolveNamesFor(this, nameResolver));
    }
  }

  @Override
  public String toString() {
    String result = getClass().getName() + ": paramCount="
        + (hasNames() ? "" + name2param.size() : "UNRESOLVED_NAMES_YET");
    if (name2param != null) {
      for (String name : name2param.keySet()) {
        result += "\n  " + name2param.get(name);
      }
    }
    return result;
  }
}
