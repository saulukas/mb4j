package org.mb4j.view.mount;

import org.mb4j.view.View;
import org.mb4j.view.path.ViewPath;

public interface PathFromViewClassResolver {
  ViewPath viewPathFor(Class<? extends View> viewClass);
}
