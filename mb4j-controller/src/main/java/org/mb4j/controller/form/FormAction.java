package org.mb4j.controller.form;

import java.lang.reflect.ParameterizedType;
import org.mb4j.controller.View;
import org.mb4j.controller.ViewRequest;
import org.mb4j.controller.ViewResponse;

public abstract class FormAction<F extends Form> implements View {
  private final Class<F> formClass;

  public FormAction() {
    ParameterizedType genericAction = (ParameterizedType) getClass().getGenericSuperclass();
    this.formClass = (Class<F>) genericAction.getActualTypeArguments()[0];
  }

  protected abstract ViewResponse doHandle(ViewRequest request, F form);

  @Override
  public ViewResponse handle(ViewRequest request) {
    F form = createFormFrom(request);
    return doHandle(request, form);
  }

  private F createFormFrom(ViewRequest request) {
    F form = createEmptyForm();
    form.resolveFieldNames(request.actionParamNameResolver);
    for (String name : request.url.params.named.names()) {
      FormField param = form.fieldBy(name);
      if (param != null) {
        param.setValue(request.url.params.named.valueOf(name));
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
