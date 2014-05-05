package org.mb4j.sample.liferay;

import com.google.inject.Injector;
import org.mb4j.controller.ControllerRequest;
import org.mb4j.controller.mapping.ControllerMappings;
import org.mb4j.controller.url.ControllerUrl;
import org.mb4j.controller.utils.AttributesMap;
import org.mb4j.liferay.PortletControllerRequest;

public class LiferaySampleTestApplication {
  static Injector injector = LiferaySampleModule.injector();

  public static <T> T inject(Class<T> klass) {
    return injector.getInstance(klass);
  }

  public static ControllerRequest requestFor(Class<? extends ControllerMappings> mappingsClass, ControllerUrl url) {
    String path2home = "../path2home/../";
    String path2staticResources = "../path2staticResources/../";
    String authToken = "12auth34";
    String namespace = "_namespace_";
    ControllerMappings mappings = inject(mappingsClass);
    return PortletControllerRequest.of(
        url,
        path2home,
        path2staticResources,
        new AttributesMap(),
        namespace,
        authToken,
        mappings
    );
  }
}
