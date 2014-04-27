package org.mb4j.servlet.sample;

import com.google.inject.Injector;
import org.mb4j.controller.mapping.ControllerMappings;
import org.mb4j.controller.url.ControllerUrl;
import org.mb4j.controller.url.NamedParams;
import org.mb4j.servlet.ServletControllerRequest;

public class ServletSampleTestApplication {
  static Injector injector = ServletSampleModule.injector();

  public static <T> T inject(Class<T> klass) {
    return injector.getInstance(klass);
  }

  public static ServletControllerRequest requestFor(ControllerUrl controllerUrl) {
    String path2home = "../../../";
    ControllerMappings mappings = inject(ControllerMappings.class);
    return new ServletControllerRequest(
        path2home,
        controllerUrl,
        NamedParams.empty(),
        mappings.controllerClass2UrlPathResolver(),
        mappings.formClass2NameResolver());
  }
}
