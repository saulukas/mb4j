package org.mb4j.view.mount;

import java.util.HashMap;
import java.util.Map;
import org.mb4j.view.View;
import org.mb4j.view.path.ViewPath;
import static org.mb4j.view.path.ViewPathString.pathStringOf;

class ViewPathMounter implements PathFromViewClassResolver {
  Map<Class<? extends View>, ViewPath> viewClass2path = new HashMap<Class<? extends View>, ViewPath>();

  @Override
  public ViewPath viewPathFor(Class<? extends View> viewClass) {
    ViewPath path = viewClass2path.get(viewClass);
    if (path == null) {
      throw new RuntimeException("View was not mounted: " + viewClass);
    }
    return path;
  }

  void mount(ViewPath path, Class<? extends View> viewClass) {
    ViewPath mountedPath = viewClass2path.get(viewClass);
    if (mountedPath != null) {
      throw new RuntimeException("Attept to mount view class " + viewClass
          + "\n    at path \"" + pathStringOf(path) + "\""
          + "\n    which is already mounded at \"" + pathStringOf(mountedPath) + "\".");
    }
    viewClass2path.put(viewClass, path);
  }
}
