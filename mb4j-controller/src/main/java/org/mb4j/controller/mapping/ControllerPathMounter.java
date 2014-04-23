package org.mb4j.controller.mapping;

import java.util.HashMap;
import java.util.Map;
import org.mb4j.controller.Controller;
import org.mb4j.controller.url.UrlPath;
import static org.mb4j.controller.url.UrlPathString.pathStringOf;

class ControllerPathMounter implements ControllerClass2UrlPathResolver {
  Map<Class<? extends Controller>, UrlPath> controllerClass2path = new HashMap<>();

  @Override
  public UrlPath urlPathFor(Class<? extends Controller> viewClass) {
    UrlPath path = controllerClass2path.get(viewClass);
    if (path == null) {
      throw new RuntimeException("View was not mounted: " + viewClass);
    }
    return path;
  }

  void mount(UrlPath path, Class<? extends Controller> viewClass) {
    UrlPath mountedPath = controllerClass2path.get(viewClass);
    if (mountedPath != null) {
      throw new RuntimeException("Attept to mount view class " + viewClass
          + "\n    at path \"" + pathStringOf(path) + "\""
          + "\n    which is already mounded at \"" + pathStringOf(mountedPath) + "\".");
    }
    controllerClass2path.put(viewClass, path);
  }

  Iterable<Controller> controllers() {
    return
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
