package org.mb4j.controller.mapping;

import java.util.HashMap;
import java.util.Map;
import org.mb4j.controller.Controller;
import org.mb4j.controller.path.UrlPath;
import static org.mb4j.controller.path.UrlPathString.pathStringOf;

class ViewPathMounter implements PathFromViewClassResolver {
  Map<Class<? extends Controller>, UrlPath> viewClass2path = new HashMap<Class<? extends Controller>, UrlPath>();

  @Override
  public UrlPath viewPathFor(Class<? extends Controller> viewClass) {
    UrlPath path = viewClass2path.get(viewClass);
    if (path == null) {
      throw new RuntimeException("View was not mounted: " + viewClass);
    }
    return path;
  }

  void mount(UrlPath path, Class<? extends Controller> viewClass) {
    UrlPath mountedPath = viewClass2path.get(viewClass);
    if (mountedPath != null) {
      throw new RuntimeException("Attept to mount view class " + viewClass
          + "\n    at path \"" + pathStringOf(path) + "\""
          + "\n    which is already mounded at \"" + pathStringOf(mountedPath) + "\".");
    }
    viewClass2path.put(viewClass, path);
  }
}
