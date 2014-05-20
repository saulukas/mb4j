package org.mb4j.component.form;

import org.mb4j.component.view.ViewUrl;

public class FormResponseRedirectToController implements FormResponse {
  public final ViewUrl controllerUrl;

  private FormResponseRedirectToController(ViewUrl controllerUrl) {
    this.controllerUrl = controllerUrl;
  }

  public static FormResponseRedirectToController redirectTo(ViewUrl controllerUrl) {
    return new FormResponseRedirectToController(controllerUrl);
  }
}
