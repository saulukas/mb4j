package org.mb4j.servlet;

import org.mb4j.controller.Request;
import org.mb4j.controller.sitemap.SiteMap;
import org.mb4j.controller.url.ControllerUrl;
import org.mb4j.controller.url.ControllerUrl4ResponseResolver;
import org.mb4j.controller.url.Url4ResponseResolver;
import org.mb4j.controller.utils.Attributes;

public class ServletControllerRequest {
  public static Request of(
      ControllerUrl url,
      String path2home,
      Attributes attributes,
      SiteMap siteMap) {
    return new Request(
        url,
        attributes,
        new Url4ResponseResolver(path2home),
        new ControllerUrl4ResponseResolver(path2home, siteMap.controllerClass2UrlPath()),
        new ServletFormData4ResponseResolver(siteMap.formClass2Name()),
        null
    );
  }
}
