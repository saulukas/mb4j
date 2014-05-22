package org.mb4j.component.viewmap;

import java.util.HashMap;
import java.util.Map;
import org.mb4j.component.url.UrlPath;
import static org.mb4j.component.url.UrlPathString.pathStringOf;
import org.mb4j.component.view.View;

class ViewMapClasses implements MapViewClass2UrlPath {
  Map<Class<? extends View>, UrlPath> viewClass2path = new HashMap<>();

  @Override
  public UrlPath urlPathFor(Class<? extends View> viewClass) {
    UrlPath path = viewClass2path.get(viewClass);
    if (path == null) {
      throw new RuntimeException("View was not mounted: " + viewClass);
    }
    return path;
  }

  void mount(UrlPath path, Class<? extends View> viewClass) {
    UrlPath mountedPath = viewClass2path.get(viewClass);
    if (mountedPath != null) {
      throw new RuntimeException("Attept to mount view class " + viewClass
          + "\n    at path \"" + pathStringOf(path) + "\""
          + "\n    which is already mounted at \"" + pathStringOf(mountedPath) + "\".");
    }
    viewClass2path.put(viewClass, path);
  }
}
