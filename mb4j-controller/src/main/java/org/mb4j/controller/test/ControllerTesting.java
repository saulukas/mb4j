package org.mb4j.controller.test;

import org.mb4j.controller.ControllerRequest;
import org.mb4j.controller.form.Form;
import org.mb4j.controller.url.ControllerUrl;
import org.mb4j.controller.url.ControllerUrl2StringResolver;
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

  public static ControllerUrl2StringResolver controllerUrlResolver4Tests(final String path2home) {
    return new ControllerUrl2StringResolver() {
      @Override
      public String urlStringOf(ControllerUrl controllerUrl) {
        return path2home + controllerUrl;
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
