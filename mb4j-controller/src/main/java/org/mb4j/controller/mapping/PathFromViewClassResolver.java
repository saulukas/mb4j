package org.mb4j.controller.mapping;

import org.mb4j.controller.Controller;
import org.mb4j.controller.path.UrlPath;

public interface PathFromViewClassResolver {
  UrlPath viewPathFor(Class<? extends Controller> viewClass);
}
