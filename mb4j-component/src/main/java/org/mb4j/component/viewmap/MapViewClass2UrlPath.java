package org.mb4j.component.viewmap;

import org.mb4j.component.Controller;
import org.mb4j.component.url.UrlPath;

public interface MapViewClass2UrlPath {

    UrlPath urlPathFor(Class<? extends Controller> viewClass);
}
