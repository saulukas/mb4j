package org.mb4j.servlet;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.mb4j.controller.form.FormAction4Request;
import org.mb4j.controller.form.FormData;
import org.mb4j.controller.form.FormData4Request;
import org.mb4j.controller.form.FormField;
import org.mb4j.controller.form.FormField4Request;
import org.mb4j.controller.mapping.FormClass2NameResolver;

public class ServletFormData4RequestResolver {
  public static final String ACTION_NAME_PREFIX = "mb(a)";

  public static FormData4Request resolve(FormClass2NameResolver formResolver, FormData formData) {
    return new FormData4Request(
        new ServletFormHeaderBrick(formResolver.formNameOf(formData.getFormClass())),
        fields4RequestFrom(formData),
        actions4RequestFrom(formData));
  }

  private static Map<String, FormAction4Request> actions4RequestFrom(FormData formData) {
    Set<String> actionNames = formData.getActionNames();
    Map<String, FormAction4Request> actions4Request = new TreeMap<>();
    for (String name : actionNames) {
      actions4Request.put(name, new FormAction4Request(ACTION_NAME_PREFIX + name));
    }
    return actions4Request;
  }

  private static Map<String, FormField4Request> fields4RequestFrom(FormData formData) {
    Map<String, FormField> fields = formData.getFields();
    Map<String, FormField4Request> fields4Request = new HashMap<>();
    for (Map.Entry<String, FormField> entry : fields.entrySet()) {
      String name = entry.getKey();
      fields4Request.put(name, new FormField4Request(name, entry.getValue()));
    }
    return fields4Request;
  }
}
