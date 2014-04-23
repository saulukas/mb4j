package org.mb4j.servlet;

import java.util.HashMap;
import java.util.Map;
import org.mb4j.controller.form.FormAction;
import org.mb4j.controller.form.FormAction4Request;
import org.mb4j.controller.form.FormData;
import org.mb4j.controller.form.FormData4Request;
import org.mb4j.controller.form.FormField;
import org.mb4j.controller.form.FormField4Request;

public class ServletFormData4RequestResolver {
  public static final String ACTION_NAME_PREFIX = "mb(a)";

  public static FormData4Request resolve(FormData formData) {
    return new FormData4Request(
        ServletFormHeaderBrick.INSTANCE,
        fields4RequestFrom(formData),
        actions4RequestFrom(formData));
  }

  private static Map<String, FormAction4Request> actions4RequestFrom(FormData formData) {
    Map<String, FormAction> actions = formData.getActions();
    Map<String, FormAction4Request> actions4Request = new HashMap<>();
    for (Map.Entry<String, FormAction> entry : actions.entrySet()) {
      String name = entry.getKey();
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
