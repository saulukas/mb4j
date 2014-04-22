package org.mb4j.controller;

import org.mb4j.controller.url.StaticResourceUrlResolver;
import org.mb4j.controller.url.ControllerUrl;
import org.mb4j.controller.url.ViewUrlStringResolver;
import org.mb4j.controller.form.Form;

public class ViewTesting {
  public static ViewRequest request4Tests(ControllerUrl url) {
    return new ViewRequest(
        url,
        staticUrlResolver4Tests("../path2home/../"),
        viewUrlResolver4Tests("../path2home/../"),
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

  public static ViewUrlStringResolver viewUrlResolver4Tests(final String path2home) {
    return new ViewUrlStringResolver() {
      @Override
      public String urlStringOf(ControllerUrl viewUrl) {
        return path2home + viewUrl;
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
