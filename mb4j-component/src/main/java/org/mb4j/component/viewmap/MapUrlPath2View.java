package org.mb4j.component.viewmap;

import org.mb4j.component.url.UrlPath;
import org.mb4j.component.Controller;

public interface MapUrlPath2View {
  Result viewAt(UrlPath path);

  class Result {
    public final Controller view;
    public final UrlPath mappedPath;
    public final UrlPath paramsPath;

    public Result(Controller view, UrlPath mappedPath, UrlPath paramsPath) {
      this.view = view;
      this.mappedPath = mappedPath;
      this.paramsPath = paramsPath;
    }

    public boolean resultIsEmpty() {
      return view == null;
    }
  }
}
