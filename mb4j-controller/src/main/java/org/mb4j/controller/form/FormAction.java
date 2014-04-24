package org.mb4j.controller.form;

import java.lang.reflect.ParameterizedType;
import org.mb4j.controller.Controller;
import org.mb4j.controller.ControllerRequest;

public abstract class FormAction<T extends FormData> extends Controller {
  public final Class<T> formDataClass;

  public FormAction() {
    this.formDataClass = initFormDataClass();
  }

  private Class<T> initFormDataClass() {
    ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
    return (Class<T>) type.getActualTypeArguments()[0];
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
