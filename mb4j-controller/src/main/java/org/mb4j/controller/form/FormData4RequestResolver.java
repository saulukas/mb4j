package org.mb4j.controller.form;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import org.mb4j.brick.Brick;
import org.mb4j.controller.form.field.FormField;
import org.mb4j.controller.mapping.FormClass2NameResolver;

public abstract class FormData4RequestResolver {
  protected static final String FORM_PARAM = "mb(f)";
  protected static final String ACTION_PARAM_PREFIX = "mb(a)";
  private final FormClass2NameResolver formClass2name;

  protected FormData4RequestResolver(FormClass2NameResolver formClass2name) {
    this.formClass2name = formClass2name;
  }

  protected abstract Brick createHeaderBrick(String formName);

  protected String resolveFieldName(String name) {
    return name;
  }

  public FormData4Request resolve(FormData<?> formData) {
    return new FormData4Request(
        createHeaderBrick(formClass2name.formNameOf(formData.formClass)),
        fields4RequestFrom(formData),
        actions4RequestFrom(formData));
  }

  private Map<String, FormField4Request> fields4RequestFrom(FormData<?> formData) {
    Map<String, FormField> fieldMap = formData.fieldGroup.asFieldMap();
    Map<String, FormField4Request> fields4Request = new HashMap<>();
    for (Map.Entry<String, FormField> entry : fieldMap.entrySet()) {
      String name = entry.getKey();
      fields4Request.put(name, new FormField4Request(resolveFieldName(name), entry.getValue()));
    }
    return fields4Request;
  }

  private Map<String, FormAction4Request> actions4RequestFrom(FormData<?> formData) {
    Map<String, FormAction4Request> actions4Request = new TreeMap<>();
    for (String name : formData.actionNames) {
      actions4Request.put(name, new FormAction4Request(resolveFieldName(ACTION_PARAM_PREFIX + name)));
    }
    return actions4Request;
  }
}
