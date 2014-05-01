package org.mb4j.servlet.sample;

import com.google.inject.Injector;
import org.mb4j.controller.mapping.ControllerMappings;
import org.mb4j.controller.url.ControllerUrl;
import org.mb4j.servlet.ServletControllerRequest;

public class ServletSampleTestApplication {
  static Injector injector = ServletSampleModule.injector();

  public static <T> T inject(Class<T> klass) {
    return injector.getInstance(klass);
  }

  public static ServletControllerRequest requestFor(ControllerUrl url) {
    String path2home = "../../../";
    return ServletControllerRequest.of(url, path2home, inject(ControllerMappings.class));
  }
}
