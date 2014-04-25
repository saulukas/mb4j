package org.mb4j.controller.form1;

import org.mb4j.controller.ControllerRequest;

public abstract class FormFiller1<P, F extends Form1> {
  protected abstract F createFormFrom(P params);

  public final F filledForm(ControllerRequest request, P params) {
    F form = createFormFrom(params);
    form.resolveFieldNames(request.actionParamNameResolver);
    return form;
  }
}
