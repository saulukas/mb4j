package org.mb4j.controller.mount;

import org.mb4j.controller.View;
import org.mb4j.controller.path.ViewPath;

public interface PathFromViewClassResolver {
  ViewPath viewPathFor(Class<? extends View> viewClass);
}
