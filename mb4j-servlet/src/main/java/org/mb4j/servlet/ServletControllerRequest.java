package org.mb4j.servlet;

import org.mb4j.controller.ControllerRequest;
import org.mb4j.controller.mapping.ControllerMappings;
import org.mb4j.controller.resource.BinaryResource;
import org.mb4j.controller.resource.TextResource;
import org.mb4j.controller.url.ControllerUrl;
import org.mb4j.controller.url.ControllerUrl4ResponseResolver;
import org.mb4j.controller.url.Url4ResponseResolver;
import org.mb4j.controller.utils.Attributes;

public class ServletControllerRequest {
  public static ControllerRequest of(
      ControllerUrl url,
      String path2home,
      Attributes attributes,
      ControllerMappings mappings) {
    return new ControllerRequest(
        url,
        attributes,
        new Url4ResponseResolver(path2home),
        new ControllerUrl4ResponseResolver(path2home, mappings.controllerClass2UrlPathResolver()),
        new ServletFormData4ResponseResolver(mappings.formClass2NameResolver())) {
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
