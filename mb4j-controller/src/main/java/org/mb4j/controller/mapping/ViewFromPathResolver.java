package org.mb4j.controller.mapping;

import org.mb4j.controller.Controller;
import org.mb4j.controller.path.UrlPath;

public interface ViewFromPathResolver {
  Result resolve(UrlPath path);

  class Result {
    public final Controller view;
    public final UrlPath mountedPath;
    public final UrlPath paramsPath;

    public Result(Controller view, UrlPath mountedPath, UrlPath paramsPath) {
      this.view = view;
      this.mountedPath = mountedPath;
      this.paramsPath = paramsPath;
    }

    public boolean hasView() {
      return view != null;
    }
  }
}
