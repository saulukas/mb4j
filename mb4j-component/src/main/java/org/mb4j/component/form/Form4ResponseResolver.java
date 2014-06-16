package org.mb4j.component.form;

import org.mb4j.component.form.action.FormAction;
import org.mb4j.component.form.action.FormAction4Response;
import org.mb4j.component.form.data.FormField4Response;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import org.mb4j.brick.MustacheBrick;
import org.mb4j.component.form.data.FormField;
import org.mb4j.component.viewmap.MapFormClass2Name;

public abstract class Form4ResponseResolver {
  protected static final String FORM_PARAM = "mb(f)";
  protected static final String ACTION_PARAM_PREFIX = "mb(a)";
  private final MapFormClass2Name formClass2name;

  protected Form4ResponseResolver(MapFormClass2Name formClass2name) {
    this.formClass2name = formClass2name;
  }

  public Form4Response resolve(Form<?> form) {
    return new Form4Response(
        createHeaderBrick(formClass2name.formNameOf(form.handlerClass)),
        fields4ResponseFrom(form),
        actions4ResponseFrom(form));
  }

  protected abstract MustacheBrick createHeaderBrick(String formName);

  protected String fieldName4Response(String name) {
    return name;
  }

  private Map<String, FormField4Response> fields4ResponseFrom(Form<?> formData) {
    Map<String, FormField> fieldMap = formData.data.asFieldMap();
    Map<String, FormField4Response> fields4Response = new HashMap<>();
    for (Map.Entry<String, FormField> entry : fieldMap.entrySet()) {
      String name = entry.getKey();
      fields4Response.put(name, new FormField4Response(fieldName4Response(name), entry.getValue()));
    }
    return fields4Response;
  }

  private Map<String, FormAction4Response> actions4ResponseFrom(Form<?> formData) {
    Map<String, FormAction4Response> actions4Response = new TreeMap<>();
    for (FormAction action : formData.actions) {
      actions4Response.put(
          action.name,
          new FormAction4Response(fieldName4Response(ACTION_PARAM_PREFIX + action.name), action));
    }
    return actions4Response;
  }
}
