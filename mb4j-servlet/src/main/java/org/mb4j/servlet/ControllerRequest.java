package org.mb4j.servlet;

import org.mb4j.component.Request;
import org.mb4j.component.ViewLocator;
import org.mb4j.component.ViewUrlResolver;
import org.mb4j.component.AssetUrlResolver;
import org.mb4j.component.utils.Attributes;
import org.mb4j.component.viewmap.ViewMap;
import org.mb4j.servlet.adapters.ServletFormData4ResponseResolver;
import org.mb4j.servlet.adapters.ServletResourceUrlResolver;

public class ControllerRequest {

    public static Request of(
            ViewLocator viewLocator,
            String path2home,
            Attributes attributes,
            ViewMap viewMap) {
        return new Request(
                viewLocator,
                attributes,
                new AssetUrlResolver(path2home),
                new ViewUrlResolver(path2home, viewMap.viewClass2UrlPath()),
                new ServletFormData4ResponseResolver(viewMap.formClass2Name()),
                new ServletResourceUrlResolver(viewMap.componentNameResolver)
        );
    }
}
