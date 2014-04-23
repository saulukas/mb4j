package org.mb4j.controller.test;

import org.mb4j.controller.ControllerRequest;
import org.mb4j.controller.form1.Form;
import org.mb4j.controller.url.ControllerUrl;
import org.mb4j.controller.url.ControllerUrl4Request;
import org.mb4j.controller.url.Url4Request;

public class ControllerTesting {
  public static ControllerRequest request4Tests(ControllerUrl url) {
    return new ControllerRequest(url, actionParamNameResolver4Tests()) {
      @Override
      public Url4Request resolveUrl(String urlFromHome) {
        return new Url4Request("../path2home/../" + urlFromHome);
      }

      @Override
      public ControllerUrl4Request resolve(ControllerUrl url) {
        return new ControllerUrl4Request("../path2home/../" + url);
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
