package org.mb4j.controller.form;

import org.mb4j.controller.url.ControllerUrl;

public class FormResponseRedirectToController implements FormResponse {
  public final ControllerUrl controllerUrl;

  private FormResponseRedirectToController(ControllerUrl controllerUrl) {
    this.controllerUrl = controllerUrl;
  }

  public static FormResponseRedirectToController redirectTo(ControllerUrl controllerUrl) {
    return new FormResponseRedirectToController(controllerUrl);
  }
}
