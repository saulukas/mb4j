package org.mb4j.controller.mapping;

import org.mb4j.controller.Controller;
import org.mb4j.controller.path.UrlPath;

public interface ControllerClass2UrlPathResolver {
  UrlPath urlPathFor(Class<? extends Controller> controllerClass);
}
