package org.mb4j.controller.form;

import java.util.Set;

public class FormData<T extends FormFieldGroup> {
  public final Class<? extends Form> formClass;
  public final T fieldGroup;
  public final Set<String> actionNames;

  public FormData(Class<? extends Form> formClass, T fieldGroup, Set<String> actionNames) {
    this.formClass = formClass;
    this.fieldGroup = fieldGroup;
    this.actionNames = actionNames;
  }
}
