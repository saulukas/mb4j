package org.mb4j.controller.form;

import java.util.Set;

public class FormData<T extends FormFields> {
  public final Class<? extends Form> formClass;
  public final T fields;
  public final Set<String> actionNames;

  public FormData(Class<? extends Form> formClass, T fields, Set<String> actionNames) {
    this.formClass = formClass;
    this.fields = fields;
    this.actionNames = actionNames;
  }
}
