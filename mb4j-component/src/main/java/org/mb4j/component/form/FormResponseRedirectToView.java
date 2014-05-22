package org.mb4j.component.form;

import org.mb4j.component.view.ViewUrl;

public class FormResponseRedirectToView implements FormResponse {
  public final ViewUrl viewUrl;

  private FormResponseRedirectToView(ViewUrl viewUrl) {
    this.viewUrl = viewUrl;
  }

  public static FormResponseRedirectToView redirectTo(ViewUrl viewUrl) {
    return new FormResponseRedirectToView(viewUrl);
  }
}
