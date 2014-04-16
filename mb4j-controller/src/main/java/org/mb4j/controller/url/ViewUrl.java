package org.mb4j.controller.url;

import org.mb4j.controller.View;
import org.mb4j.controller.ViewParams;
import org.mb4j.controller.path.ViewPath;
import org.mb4j.controller.path.ViewPathBuilder;

public class ViewUrl {
  public final Class<? extends View> viewClass;
  public final ViewParams params;

  private ViewUrl(Class<? extends View> viewClass, ViewParams params) {
    this.viewClass = viewClass;
    this.params = params;
  }

  public static ViewUrl of(Class<? extends View> viewClass) {
    return of(viewClass, ViewParams.empty());
  }

  public static ViewUrl of(Class<? extends View> viewClass, ViewPath path) {
    return new ViewUrl(viewClass, ViewParams.of(path));
  }

  public static ViewUrl of(Class<? extends View> viewClass, ViewPathBuilder pathBuilder) {
    return ViewUrl.of(viewClass, pathBuilder.instance());
  }

  public static ViewUrl of(Class<? extends View> viewClass, ViewParams params) {
    return new ViewUrl(viewClass, params);
  }

  @Override
  public String toString() {
    return viewClass.getSimpleName() + "(" + params + ")";
  }

  public ViewUrl withReplacedParam(String name, String value) {
    return new ViewUrl(viewClass, params.withReplacedParam(name, value));
  }

  public ViewUrl withDeletedParam(String name) {
    return new ViewUrl(viewClass, params.withDeletedParam(name));
  }
}
