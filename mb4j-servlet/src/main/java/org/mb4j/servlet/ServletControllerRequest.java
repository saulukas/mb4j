package org.mb4j.servlet;

import org.mb4j.component.ViewRequest;
import org.mb4j.component.sitemap.SiteMap;
import org.mb4j.component.url.ControllerUrl;
import org.mb4j.component.url.ControllerUrl4ResponseResolver;
import org.mb4j.component.url.AssetUrl4ResponseResolver;
import org.mb4j.component.utils.Attributes;

public class ServletControllerRequest {
  public static ViewRequest of(
      ControllerUrl url,
      String path2home,
      Attributes attributes,
      SiteMap siteMap) {
    return new ViewRequest(
        url,
        attributes,
        new AssetUrl4ResponseResolver(path2home),
        new ControllerUrl4ResponseResolver(path2home, siteMap.controllerClass2UrlPath()),
        new ServletFormData4ResponseResolver(siteMap.formClass2Name()),
        new ServletResources4ResponseResolver(siteMap.componentWithResourcesClass2Name())
    );
  }
}
