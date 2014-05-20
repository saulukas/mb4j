package org.mb4j.component.sitemap;

import java.util.HashMap;
import java.util.Map;
import org.mb4j.component.view.View;
import org.mb4j.component.url.UrlPath;
import static org.mb4j.component.url.UrlPathString.pathStringOf;

class SiteMapBuilderControllerClasses implements MapControllerClass2UrlPath {
  Map<Class<? extends View>, UrlPath> controllerClass2path = new HashMap<>();

  @Override
  public UrlPath urlPathFor(Class<? extends View> controllerClass) {
    UrlPath path = controllerClass2path.get(controllerClass);
    if (path == null) {
      throw new RuntimeException("Controller was not mounted: " + controllerClass);
    }
    return path;
  }

  void mount(UrlPath path, Class<? extends View> controllerClass) {
    UrlPath mountedPath = controllerClass2path.get(controllerClass);
    if (mountedPath != null) {
      throw new RuntimeException("Attept to mount controller class " + controllerClass
          + "\n    at path \"" + pathStringOf(path) + "\""
          + "\n    which is already mounded at \"" + pathStringOf(mountedPath) + "\".");
    }
    controllerClass2path.put(controllerClass, path);
  }
}
