package org.mb4j.controller.mapping;

import java.util.HashMap;
import java.util.Map;
import org.mb4j.controller.Controller;
import org.mb4j.controller.url.UrlPath;
import static org.mb4j.controller.url.UrlPathString.pathStringOf;

class ControllerPathMounter implements MapControllerClass2UrlPath {
  Map<Class<? extends Controller>, UrlPath> controllerClass2path = new HashMap<>();

  @Override
  public UrlPath urlPathFor(Class<? extends Controller> controllerClass) {
    UrlPath path = controllerClass2path.get(controllerClass);
    if (path == null) {
      throw new RuntimeException("Controller was not mounted: " + controllerClass);
    }
    return path;
  }

  void mount(UrlPath path, Class<? extends Controller> controllerClass) {
    UrlPath mountedPath = controllerClass2path.get(controllerClass);
    if (mountedPath != null) {
      throw new RuntimeException("Attept to mount controller class " + controllerClass
          + "\n    at path \"" + pathStringOf(path) + "\""
          + "\n    which is already mounded at \"" + pathStringOf(mountedPath) + "\".");
    }
    controllerClass2path.put(controllerClass, path);
  }
}
