package org.mb4j.servlet;

import org.mb4j.component.ControllerUrl;
import org.mb4j.component.ControllerUrl4ResponseResolver;
import org.mb4j.component.Request;
import org.mb4j.component.asset.AssetUrl4ResponseResolver;
import org.mb4j.component.utils.Attributes;
import org.mb4j.component.viewmap.ViewMap;
import org.mb4j.servlet.adapters.ServletFormData4ResponseResolver;
import org.mb4j.servlet.adapters.ServletResources4ResponseResolver;

public class ControllerRequest {

    public static Request of(
            ControllerUrl viewUrl,
            String path2home,
            Attributes attributes,
            ViewMap viewMap) {
        return new Request(
                viewUrl,
                attributes,
                new AssetUrl4ResponseResolver(path2home),
                new ControllerUrl4ResponseResolver(path2home, viewMap.viewClass2UrlPath()),
                new ServletFormData4ResponseResolver(viewMap.formClass2Name()),
                new ServletResources4ResponseResolver(viewMap.componentWithResourcesClass2Name())
        );
    }
}
