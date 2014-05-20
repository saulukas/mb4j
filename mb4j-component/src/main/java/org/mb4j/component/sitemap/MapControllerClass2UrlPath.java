package org.mb4j.component.sitemap;

import org.mb4j.component.view.View;
import org.mb4j.component.url.UrlPath;

public interface MapControllerClass2UrlPath {
  UrlPath urlPathFor(Class<? extends View> controllerClass);
}
