package org.mb4j.liferay;

import org.mb4j.controller.Request;
import org.mb4j.controller.sitemap.SiteMap;
import org.mb4j.controller.url.ControllerUrl;
import org.mb4j.controller.url.ControllerUrl4ResponseResolver;
import org.mb4j.controller.url.AssetUrl4ResponseResolver;
import org.mb4j.controller.utils.Attributes;

public class PortletControllerRequest {
  public static Request of(
      ControllerUrl url,
      String path2home,
      String path2assets,
      Attributes attributes,
      String namespace,
      String authTokenOrNull,
      SiteMap siteMap
  ) {
    return new Request(
        url,
        attributes,
        new AssetUrl4ResponseResolver(path2assets),
        new ControllerUrl4ResponseResolver(path2home, siteMap.controllerClass2UrlPath()),
        new PortletFormData4ResponseResolver(namespace, authTokenOrNull, siteMap.formClass2Name()),
        null
    );
  }
}
