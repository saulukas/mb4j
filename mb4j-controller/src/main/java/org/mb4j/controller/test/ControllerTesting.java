package org.mb4j.controller.test;

import org.mb4j.controller.ControllerRequest;
import org.mb4j.controller.form.Form;
import org.mb4j.controller.url.ControllerUrl;
import org.mb4j.controller.url.ControllerUrl4Request;
import org.mb4j.controller.url.ControllerUrl4RequestResolver;
import org.mb4j.controller.url.StaticResourceUrlResolver;

public class ControllerTesting {
  public static ControllerRequest request4Tests(ControllerUrl url) {
    return new ControllerRequest(
        url,
        staticUrlResolver4Tests("../path2home/../"),
        controllerUrlResolver4Tests("../path2home/../"),
        actionParamNameResolver4Tests()
    );
  }

  public static StaticResourceUrlResolver staticUrlResolver4Tests(final String path2home) {
    return new StaticResourceUrlResolver() {
      @Override
      public String urlForStaticResource(String urlFromHome) {
        return path2home + urlFromHome;
      }
    };
  }

  public static ControllerUrl4RequestResolver controllerUrlResolver4Tests(final String path2home) {
    return new ControllerUrl4RequestResolver() {
      @Override
      public ControllerUrl4Request resolve(ControllerUrl controllerUrl) {
        return new ControllerUrl4Request(path2home + controllerUrl);
      }
    };
  }

  public static Form.NameResolver actionParamNameResolver4Tests() {
    return new Form.NameResolver() {
      @Override
      public String resolvedName(String fieldName) {
        return fieldName;
      }
    };
  }
}
