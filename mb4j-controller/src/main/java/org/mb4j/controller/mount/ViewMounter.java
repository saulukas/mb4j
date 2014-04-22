package org.mb4j.controller.mount;

import org.mb4j.controller.Controller;
import org.mb4j.controller.path.BufferedUrlPathReader;
import static org.mb4j.controller.path.BufferedUrlPathReader.bufferedReaderOf;
import org.mb4j.controller.path.UrlPath;
import org.mb4j.controller.path.UrlPathString;

public class ViewMounter {
  public static final UrlPath HOME_VIEW_PATH = UrlPath.empty();
  public static final UrlPath DEFAULT_HOME_VIEW_PATH = UrlPathString.urlPath("*");
  private final ViewMounterNode rootNode = ViewMounterNode.createRoot();
  private final ViewPathMounter pathMounter = new ViewPathMounter();

  private ViewMounter() {
  }

  public static ViewMounter withHomeView(Controller homeView) {
    return new ViewMounter().mount(HOME_VIEW_PATH, homeView);
  }

  public static ViewMounter withDefaultHomeView(Controller homeView) {
    return new ViewMounter().mount(DEFAULT_HOME_VIEW_PATH, homeView);
  }

  public ViewMounter mount(UrlPath path, Controller view) {
    BufferedUrlPathReader pathReader = bufferedReaderOf(path);
    rootNode.mount(pathReader, view);
    pathMounter.mount(pathReader.processedPath(), view.getClass());
    return this;
  }

  public ViewFromPathResolver viewFromPathResolver() {
    return rootNode;
  }

  public PathFromViewClassResolver pathFromViewClassResolver() {
    return pathMounter;
  }

  @Override
  public String toString() {
    return rootNode.toString();
  }

  public String toString(String margin) {
    return rootNode.toString(margin);
  }
}
