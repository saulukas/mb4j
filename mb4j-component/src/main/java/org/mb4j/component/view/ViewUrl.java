package org.mb4j.component.view;

import org.mb4j.component.url.UrlParams;
import org.mb4j.component.url.UrlPath;
import org.mb4j.component.url.UrlPathBuilder;
import org.mb4j.component.view.View;

public class ViewUrl {
  public final Class<? extends View> controllerClass;
  public final UrlParams params;

  private ViewUrl(Class<? extends View> controllerClass, UrlParams params) {
    this.controllerClass = controllerClass;
    this.params = params;
  }

  public static ViewUrl of(Class<? extends View> controllerClass) {
    return of(controllerClass, UrlParams.empty());
  }

  public static ViewUrl of(Class<? extends View> controllerClass, UrlPath path) {
    return new ViewUrl(controllerClass, UrlParams.of(path));
  }

  public static ViewUrl of(Class<? extends View> controllerClass, UrlPathBuilder pathBuilder) {
    return ViewUrl.of(controllerClass, pathBuilder.instance());
  }

  public static ViewUrl of(Class<? extends View> controllerClass, UrlParams params) {
    return new ViewUrl(controllerClass, params);
  }

  @Override
  public String toString() {
    return controllerClass.getSimpleName() + "(" + params + ")";
  }

  public ViewUrl withReplacedParam(String name, String value) {
    return new ViewUrl(controllerClass, params.withReplaced(name, value));
  }

  public ViewUrl withDeletedParam(String name) {
    return new ViewUrl(controllerClass, params.withDeleted(name));
  }
}
