package org.mb4j.component.sitemap;

import org.mb4j.component.View;
import org.mb4j.component.url.UrlPath;

public interface MapUrlPath2Controller {
  Result controllerFor(UrlPath path);

  class Result {
    public final View controller;
    public final UrlPath mountedPath;
    public final UrlPath paramsPath;

    public Result(View controller, UrlPath mountedPath, UrlPath paramsPath) {
      this.controller = controller;
      this.mountedPath = mountedPath;
      this.paramsPath = paramsPath;
    }

    public boolean resultIsEmpty() {
      return controller == null;
    }
  }
}
