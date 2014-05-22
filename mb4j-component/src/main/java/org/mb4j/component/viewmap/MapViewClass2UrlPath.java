package org.mb4j.component.viewmap;

import org.mb4j.component.url.UrlPath;
import org.mb4j.component.view.View;

public interface MapViewClass2UrlPath {
  UrlPath urlPathFor(Class<? extends View> viewClass);
}
