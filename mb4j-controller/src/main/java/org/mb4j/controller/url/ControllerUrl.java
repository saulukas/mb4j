package org.mb4j.controller.url;

import org.mb4j.controller.Controller;

public class ControllerUrl {
  public final Class<? extends Controller> controllerClass;
  public final UrlParams params;

  private ControllerUrl(Class<? extends Controller> controllerClass, UrlParams params) {
    this.controllerClass = controllerClass;
    this.params = params;
  }

  public static ControllerUrl of(Class<? extends Controller> controllerClass) {
    return of(controllerClass, UrlParams.empty());
  }

  public static ControllerUrl of(Class<? extends Controller> controllerClass, UrlPath path) {
    return new ControllerUrl(controllerClass, UrlParams.of(path));
  }

  public static ControllerUrl of(Class<? extends Controller> controllerClass, UrlPathBuilder pathBuilder) {
    return ControllerUrl.of(controllerClass, pathBuilder.instance());
  }

  public static ControllerUrl of(Class<? extends Controller> controllerClass, UrlParams params) {
    return new ControllerUrl(controllerClass, params);
  }

  @Override
  public String toString() {
    return controllerClass.getSimpleName() + "(" + params + ")";
  }

  public ControllerUrl withReplacedParam(String name, String value) {
    return new ControllerUrl(controllerClass, params.withReplacedParam(name, value));
  }

  public ControllerUrl withDeletedParam(String name) {
    return new ControllerUrl(controllerClass, params.withDeletedParam(name));
  }
}
