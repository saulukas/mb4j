package org.mb4j.liferay;

import org.mb4j.controller.ControllerRequest;
import org.mb4j.controller.mapping.ControllerMappings;
import org.mb4j.controller.url.ControllerUrl;
import org.mb4j.controller.url.ControllerUrl4RequestResolver;
import org.mb4j.controller.url.Url4RequestResolver;
import org.mb4j.controller.utils.Attributes;

public class PortletControllerRequest {
  public static ControllerRequest of(
      ControllerUrl url,
      String path2home,
      String pathToStaticResources,
      Attributes attributes,
      String namespace,
      String authTokenOrNull,
      ControllerMappings mappings
  ) {
    return new ControllerRequest(
        url,
        attributes,
        new Url4RequestResolver(pathToStaticResources),
        new ControllerUrl4RequestResolver(path2home, mappings.controllerClass2UrlPathResolver()),
        new PortletFormData4RequestResolver(namespace, authTokenOrNull, mappings.formClass2NameResolver())
    );
  }
}
