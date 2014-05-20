package org.mb4j.component.form;

import java.util.Collection;
import org.mb4j.component.form.field.FormFieldRecord;

public class FormData<T extends FormFieldRecord> {
  public final Class<? extends Form> formClass;
  public final T fields;
  public final Collection<FormAction> actions;

  public FormData(Class<? extends Form> formClass, T fields, Collection<FormAction> actions) {
    this.formClass = formClass;
    this.fields = fields;
    this.actions = actions;
  }
}
