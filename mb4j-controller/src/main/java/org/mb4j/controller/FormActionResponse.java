package org.mb4j.controller;

import org.mb4j.controller.url.ControllerUrl;

public class FormActionResponse implements ControllerResponse {
  public final ControllerUrl redirectToControllerUrl;

  private FormActionResponse(ControllerUrl redirectToControllerUrl) {
    this.redirectToControllerUrl = redirectToControllerUrl;
  }

  public static FormActionResponse redirectTo(ControllerUrl controllerUrl) {
    return new FormActionResponse(controllerUrl);
  }
}
