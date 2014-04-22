package org.mb4j.controller.mapping;

import org.mb4j.controller.Controller;
import org.mb4j.controller.url.UrlPath;

public interface UrlPath2ControllerResolver {
  Result resolve(UrlPath path);

  class Result {
    public final Controller controller;
    public final UrlPath mountedPath;
    public final UrlPath paramsPath;

    public Result(Controller controller, UrlPath mountedPath, UrlPath paramsPath) {
      this.controller = controller;
      this.mountedPath = mountedPath;
      this.paramsPath = paramsPath;
    }

    public boolean hasController() {
      return controller != null;
    }
  }
}
