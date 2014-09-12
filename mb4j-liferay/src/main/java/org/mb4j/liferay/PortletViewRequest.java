package org.mb4j.liferay;

import org.mb4j.liferay.adapters.PortletFormData4ResponseResolver;
import org.mb4j.component.asset.AssetUrl4ResponseResolver;
import org.mb4j.component.resource.Resources4ResponseResolver;
import org.mb4j.component.utils.Attributes;
import org.mb4j.component.Request;
import org.mb4j.component.ControllerUrl;
import org.mb4j.component.ControllerUrl4ResponseResolver;
import org.mb4j.component.viewmap.ViewMap;

public class PortletViewRequest {
  public static Request of(
      ControllerUrl viewUrl,
      String path2home,
      String path2assets,
      Attributes attributes,
      String namespace,
      String authTokenOrNull,
      Resources4ResponseResolver resourcesResolver,
      ViewMap viewMap
  ) {
    return new Request(
        viewUrl,
        attributes,
        new AssetUrl4ResponseResolver(path2assets),
        new ControllerUrl4ResponseResolver(path2home, viewMap.viewClass2UrlPath()),
        new PortletFormData4ResponseResolver(namespace, authTokenOrNull, viewMap.formClass2Name()),
        resourcesResolver
    );
  }
}
