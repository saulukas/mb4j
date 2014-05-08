package org.mb4j.liferay;

import org.mb4j.controller.ControllerRequest;
import org.mb4j.controller.mapping.ControllerMappings;
import org.mb4j.controller.resource.BinaryResource;
import org.mb4j.controller.resource.TextResource;
import org.mb4j.controller.url.ControllerUrl;
import org.mb4j.controller.url.ControllerUrl4ResponseResolver;
import org.mb4j.controller.url.Url4ResponseResolver;
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
        new Url4ResponseResolver(pathToStaticResources),
        new ControllerUrl4ResponseResolver(path2home, mappings.controllerClass2UrlPath()),
        new PortletFormData4ResponseResolver(namespace, authTokenOrNull, mappings.formClass2Name())
    ) {
      @Override
      public BinaryResource.Output binaryOutput() {
        throw new UnsupportedOperationException("Not supported yet.");
      }

      @Override
      public TextResource.Output textOutput() {
        throw new UnsupportedOperationException("Not supported yet.");
      }
    };
  }
}
