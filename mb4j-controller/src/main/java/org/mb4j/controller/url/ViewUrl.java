package org.mb4j.controller.url;

import org.mb4j.controller.Controller;
import org.mb4j.controller.ViewParams;
import org.mb4j.controller.path.UrlPath;
import org.mb4j.controller.path.UrlPathBuilder;

public class ViewUrl {
  public final Class<? extends Controller> viewClass;
  public final ViewParams params;

  private ViewUrl(Class<? extends Controller> viewClass, ViewParams params) {
    this.viewClass = viewClass;
    this.params = params;
  }

  public static ViewUrl of(Class<? extends Controller> viewClass) {
    return of(viewClass, ViewParams.empty());
  }

  public static ViewUrl of(Class<? extends Controller> viewClass, UrlPath path) {
    return new ViewUrl(viewClass, ViewParams.of(path));
  }

  public static ViewUrl of(Class<? extends Controller> viewClass, UrlPathBuilder pathBuilder) {
    return ViewUrl.of(viewClass, pathBuilder.instance());
  }

  public static ViewUrl of(Class<? extends Controller> viewClass, ViewParams params) {
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
