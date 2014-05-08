package org.mb4j.controller.form;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import org.mb4j.brick.Brick;
import org.mb4j.controller.form.field.FormField;
import org.mb4j.controller.sitemap.MapFormClass2Name;

public abstract class FormData4ResponseResolver {
  protected static final String FORM_PARAM = "mb(f)";
  protected static final String ACTION_PARAM_PREFIX = "mb(a)";
  private final MapFormClass2Name formClass2name;

  protected FormData4ResponseResolver(MapFormClass2Name formClass2name) {
    this.formClass2name = formClass2name;
  }

  protected abstract Brick createHeaderBrick(String formName);

  protected String fieldName4Response(String name) {
    return name;
  }

  public FormData4Response resolve(FormData<?> formData) {
    return new FormData4Response(
        createHeaderBrick(formClass2name.formNameOf(formData.formClass)),
        fields4ResponseFrom(formData),
        actions4ResponseFrom(formData));
  }

  private Map<String, FormField4Response> fields4ResponseFrom(FormData<?> formData) {
    Map<String, FormField> fieldMap = formData.fields.asFieldMap();
    Map<String, FormField4Response> fields4Response = new HashMap<>();
    for (Map.Entry<String, FormField> entry : fieldMap.entrySet()) {
      String name = entry.getKey();
      fields4Response.put(name, new FormField4Response(fieldName4Response(name), entry.getValue()));
    }
    return fields4Response;
  }

  private Map<String, FormAction4Response> actions4ResponseFrom(FormData<?> formData) {
    Map<String, FormAction4Response> actions4Response = new TreeMap<>();
    for (FormAction action : formData.actions) {
      actions4Response.put(
          action.name,
          new FormAction4Response(fieldName4Response(ACTION_PARAM_PREFIX + action.name), action));
    }
    return actions4Response;
  }
}
