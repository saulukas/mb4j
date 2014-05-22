package org.mb4j.servlet;

import org.mb4j.component.asset.AssetUrl4ResponseResolver;
import org.mb4j.component.utils.Attributes;
import org.mb4j.component.view.ViewRequest;
import org.mb4j.component.view.ViewUrl;
import org.mb4j.component.view.ViewUrl4ResponseResolver;
import org.mb4j.component.viewmap.SiteMap;
import org.mb4j.servlet.adapters.ServletFormData4ResponseResolver;
import org.mb4j.servlet.adapters.ServletResources4ResponseResolver;

public class ServletControllerRequest {
  public static ViewRequest of(
      ViewUrl viewUrl,
      String path2home,
      Attributes attributes,
      SiteMap siteMap) {
    return new ViewRequest(
        viewUrl,
        attributes,
        new AssetUrl4ResponseResolver(path2home),
        new ViewUrl4ResponseResolver(path2home, siteMap.controllerClass2UrlPath()),
        new ServletFormData4ResponseResolver(siteMap.formClass2Name()),
        new ServletResources4ResponseResolver(siteMap.componentWithResourcesClass2Name())
    );
  }
}
