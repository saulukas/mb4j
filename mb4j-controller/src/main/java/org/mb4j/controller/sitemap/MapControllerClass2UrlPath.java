package org.mb4j.controller.sitemap;

import org.mb4j.controller.Controller;
import org.mb4j.controller.url.UrlPath;

public interface MapControllerClass2UrlPath {
  UrlPath urlPathFor(Class<? extends Controller> controllerClass);
}
