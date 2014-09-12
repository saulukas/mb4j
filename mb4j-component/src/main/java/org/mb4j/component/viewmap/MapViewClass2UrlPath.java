package org.mb4j.component.viewmap;

import org.mb4j.component.url.UrlPath;
import org.mb4j.component.Controller;

public interface MapViewClass2UrlPath {
  UrlPath urlPathFor(Class<? extends Controller> viewClass);
}
