package org.mb4j.controller.test;

import java.util.HashMap;
import java.util.Map;
import org.mb4j.brick.RawBrick;
import org.mb4j.controller.ControllerRequest;
import org.mb4j.controller.form.FormAction4Request;
import org.mb4j.controller.form.FormData;
import org.mb4j.controller.form.FormData4Request;
import org.mb4j.controller.form.field.FormField;
import org.mb4j.controller.form.field.FormField4Request;
import org.mb4j.controller.form1.Form1;
import org.mb4j.controller.url.ControllerUrl;
import org.mb4j.controller.url.ControllerUrl4Request;
import org.mb4j.controller.url.NamedParams;
import org.mb4j.controller.url.Url4Request;

public class ControllerTesting {
  public static ControllerRequest request4Tests(ControllerUrl url) {
    NamedParams postParameters = NamedParams.empty();
    return new ControllerRequest(url, postParameters, actionParamNameResolver4Tests()) {
      @Override
      public Url4Request resolveUrl(String urlFromHome) {
        return new Url4Request("../path2home/../" + urlFromHome);
      }

      @Override
      public ControllerUrl4Request resolve(ControllerUrl url) {
        return new ControllerUrl4Request("../path2home/../" + url);
      }

      @Override
      public FormData4Request resolve(FormData<?> formData) {
        Map<String, FormField> fieldMap = formData.fieldGroup.asFieldMap();
        Map<String, FormField4Request> fields4Request = new HashMap<>();
        for (Map.Entry<String, FormField> entry : fieldMap.entrySet()) {
          String name = entry.getKey();
          fields4Request.put(name, new FormField4Request(name, entry.getValue()));
        }
        Map<String, FormAction4Request> actions4Request = new HashMap<>();
        for (String name : formData.actionNames) {
          actions4Request.put(name, new FormAction4Request("mb(test)" + name));
        }
        return new FormData4Request(
            new RawBrick("<p>Some form header for testing...</p>"),
            fields4Request,
            actions4Request);
      }
    };
  }

  public static Form1.NameResolver actionParamNameResolver4Tests() {
    return new Form1.NameResolver() {
      @Override
      public String resolvedName(String fieldName) {
        return fieldName;
      }
    };
  }
}
