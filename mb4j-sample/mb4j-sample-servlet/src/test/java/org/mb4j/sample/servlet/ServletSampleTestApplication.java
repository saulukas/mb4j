package org.mb4j.sample.servlet;

import com.google.inject.Injector;
import org.mb4j.controller.ControllerRequest;
import org.mb4j.controller.sitemap.SiteMap;
import org.mb4j.controller.url.ControllerUrl;
import org.mb4j.controller.utils.AttributesMap;
import org.mb4j.servlet.ServletControllerRequest;

public class ServletSampleTestApplication {
  static Injector injector = ServletSampleModule.injector();

  public static <T> T inject(Class<T> klass) {
    return injector.getInstance(klass);
  }

  public static ControllerRequest requestFor(ControllerUrl url) {
    String path2home = "../../../";
    return ServletControllerRequest.of(
        url,
        path2home,
        new AttributesMap(),
        inject(SiteMap.class));
  }
}
