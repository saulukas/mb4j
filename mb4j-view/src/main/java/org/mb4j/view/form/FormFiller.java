package org.mb4j.view.form;

import org.mb4j.view.ViewRequest;

public abstract class FormFiller<P, F extends Form> {
  protected abstract F createFormFrom(P params);

  public final F filledForm(ViewRequest request, P params) {
    F form = createFormFrom(params);
    form.resolveFieldNames(request.actionParamNameResolver);
    return form;
  }
}
