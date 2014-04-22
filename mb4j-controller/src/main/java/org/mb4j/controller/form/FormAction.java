package org.mb4j.controller.form;

import java.lang.reflect.ParameterizedType;
import org.mb4j.controller.Controller;
import org.mb4j.controller.ControllerRequest;
import org.mb4j.controller.ControllerResponse;

public abstract class FormAction<F extends Form> implements Controller {
  private final Class<F> formClass;

  public FormAction() {
    ParameterizedType genericAction = (ParameterizedType) getClass().getGenericSuperclass();
    this.formClass = (Class<F>) genericAction.getActualTypeArguments()[0];
  }

  protected abstract ControllerResponse doHandle(ControllerRequest request, F form);

  @Override
  public ControllerResponse handle(ControllerRequest request) {
    F form = createFormFrom(request);
    return doHandle(request, form);
  }

  private F createFormFrom(ControllerRequest request) {
    F form = createEmptyForm();
    form.resolveFieldNames(request.actionParamNameResolver);
    for (String name : request.url().params.named.names()) {
      FormField param = form.fieldBy(name);
      if (param != null) {
        param.setValue(request.url().params.named.valueOf(name));
      }
    }
    return form;
  }

  F createEmptyForm() {
    try {
      return formClass.newInstance();
    } catch (Exception ex) {
      throw new RuntimeException("Failed to create empty form for action " + this + ": " + ex, ex);
    }
  }
}
