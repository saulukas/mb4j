package org.mb4j.controller.test;

import org.mb4j.controller.ControllerRequest;
import org.mb4j.controller.form.Form;
import org.mb4j.controller.url.ControllerUrl;
import org.mb4j.controller.url.ControllerUrl4Request;
import org.mb4j.controller.url.ControllerUrl4RequestResolver;
import org.mb4j.controller.url.StaticUrl4Request;
import org.mb4j.controller.url.StaticUrl4RequestResolver;

public class ControllerTesting {
  public static ControllerRequest request4Tests(ControllerUrl url) {
    return new ControllerRequest(
        url,
        staticUrlResolver4Tests("../path2home/../"),
        controllerUrlResolver4Tests("../path2home/../"),
        actionParamNameResolver4Tests()
    );
  }

  public static StaticUrl4RequestResolver staticUrlResolver4Tests(final String path2home) {
    return new StaticUrl4RequestResolver() {
      @Override
      public StaticUrl4Request resolveStaticUrl(String urlFromHome) {
        return new StaticUrl4Request(path2home + urlFromHome);
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
