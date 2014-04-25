package org.mb4j.controller.test;

import java.util.HashMap;
import java.util.Map;
import org.mb4j.brick.RawBrick;
import org.mb4j.controller.ControllerRequest;
import org.mb4j.controller.form.FormAction2;
import org.mb4j.controller.form.FormAction4Request;
import org.mb4j.controller.form.FormData;
import org.mb4j.controller.form.FormData4Request;
import org.mb4j.controller.form.FormField;
import org.mb4j.controller.form.FormField4Request;
import org.mb4j.controller.form1.Form1;
import org.mb4j.controller.mapping.InstanceProviderByClass;
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
      public FormData4Request resolve(FormData formData) {
        Map<String, FormField> fields = formData.getFields();
        Map<String, FormField4Request> fields4Request = new HashMap<>();
        for (Map.Entry<String, FormField> entry : fields.entrySet()) {
          String name = entry.getKey();
          fields4Request.put(name, new FormField4Request(name, entry.getValue()));
        }
        Map<String, FormAction2> actions = formData.getActions();
        Map<String, FormAction4Request> actions4Request = new HashMap<>();
        for (Map.Entry<String, FormAction2> entry : actions.entrySet()) {
          String name = entry.getKey();
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

  public static InstanceProviderByClass instanceProvider4Tests() {
    return new InstanceProviderByClass() {
      @Override
      public <T> T instanceOf(Class<T> klass) {
        try {
          return klass.newInstance();
        } catch (Exception ex) {
          throw new RuntimeException(ex);
        }
      }
    };
  }
}
