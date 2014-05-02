package org.mb4j.servlet;

import org.mb4j.controller.ControllerRequest;
import org.mb4j.controller.mapping.ControllerMappings;
import org.mb4j.controller.url.ControllerUrl;
import org.mb4j.controller.url.Url4RequestResolver;

public class ServletControllerRequest {
  public static ControllerRequest of(ControllerUrl url, String path2home, ControllerMappings mappings) {
    return new ControllerRequest(
        url,
        new Url4RequestResolver(path2home),
        new ServletControllerUrl4RequestResolver(path2home, mappings.controllerClass2UrlPathResolver()),
        new ServletFormData4RequestResolver(mappings.formClass2NameResolver()));
  }
}
