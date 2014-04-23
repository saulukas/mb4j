package org.mb4j.controller.form;

import org.mb4j.controller.Controller;
import org.mb4j.controller.ControllerRequest;

public abstract class FormAction<T extends FormData> implements Controller {
  protected final Class<T> formDataClass;

  public FormAction(Class<T> formDataClass) {
    this.formDataClass = formDataClass;
  }

  @Override
  public FormActionResponse handle(ControllerRequest request) {
    T formData = createEmptyFormData();
    request.fill(formData);
    return handle(request, formData);
  }

  protected T createEmptyFormData() {
    try {
      return formDataClass.newInstance();
    } catch (Exception ex) {
      throw new RuntimeException("Failed to create instance of " + formDataClass);
    }
  }

  protected abstract FormActionResponse handle(ControllerRequest request, T formData);
}
