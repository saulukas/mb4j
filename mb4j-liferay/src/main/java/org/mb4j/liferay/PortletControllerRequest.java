package org.mb4j.liferay;

import org.mb4j.component.asset.AssetUrl4ResponseResolver;
import org.mb4j.component.resource.Resources4ResponseResolver;
import org.mb4j.component.utils.Attributes;
import org.mb4j.component.view.ViewRequest;
import org.mb4j.component.view.ViewUrl;
import org.mb4j.component.view.ViewUrl4ResponseResolver;
import org.mb4j.component.viewmap.ViewMap;

public class PortletControllerRequest {
  public static ViewRequest of(
      ViewUrl viewUrl,
      String path2home,
      String path2assets,
      Attributes attributes,
      String namespace,
      String authTokenOrNull,
      Resources4ResponseResolver resourcesResolver,
      ViewMap viewMap
  ) {
    return new ViewRequest(
        viewUrl,
        attributes,
        new AssetUrl4ResponseResolver(path2assets),
        new ViewUrl4ResponseResolver(path2home, viewMap.controllerClass2UrlPath()),
        new PortletFormData4ResponseResolver(namespace, authTokenOrNull, viewMap.formClass2Name()),
        resourcesResolver
    );
  }
}
