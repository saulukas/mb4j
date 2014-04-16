package org.mb4j.controller.mount;

import org.mb4j.controller.View;
import org.mb4j.controller.path.ViewPath;

public interface ViewFromPathResolver {
  Result resolve(ViewPath path);

  class Result {
    public final View view;
    public final ViewPath mountedPath;
    public final ViewPath paramsPath;

    public Result(View view, ViewPath mountedPath, ViewPath paramsPath) {
      this.view = view;
      this.mountedPath = mountedPath;
      this.paramsPath = paramsPath;
    }

    public boolean hasView() {
      return view != null;
    }
  }
}
