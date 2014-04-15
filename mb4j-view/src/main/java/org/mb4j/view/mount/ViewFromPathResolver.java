package org.mb4j.view.mount;

import org.mb4j.view.View;
import org.mb4j.view.path.ViewPath;

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
