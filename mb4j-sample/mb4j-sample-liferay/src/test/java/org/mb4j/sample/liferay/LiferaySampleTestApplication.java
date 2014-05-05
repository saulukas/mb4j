package org.mb4j.sample.liferay;

import com.google.inject.Injector;
import org.mb4j.controller.ControllerRequest;
import org.mb4j.controller.mapping.ControllerMappings;
import org.mb4j.controller.url.ControllerUrl;
import org.mb4j.controller.url.ControllerUrl4RequestResolver;
import org.mb4j.controller.url.Url4RequestResolver;
import org.mb4j.controller.utils.AttributesMap;
import org.mb4j.liferay.PortletFormData4RequestResolver;

public class LiferaySampleTestApplication {
  static Injector injector = LiferaySampleModule.injector();

  public static <T> T inject(Class<T> klass) {
    return injector.getInstance(klass);
  }

  public static ControllerRequest requestFor(Class<? extends ControllerMappings> mappingsClass, ControllerUrl url) {
    String path2home = "../path2home/../";
    String authToken = "12auth34";
    String portletNamespace = "_namespace_";
    ControllerMappings mappings = inject(mappingsClass);
    return new ControllerRequest(
        url,
        new AttributesMap(),
        new Url4RequestResolver("../path2staticResources/../"),
        new ControllerUrl4RequestResolver(path2home, mappings.controllerClass2UrlPathResolver()),
        new PortletFormData4RequestResolver(
            portletNamespace,
            authToken,
            mappings.formClass2NameResolver()
        )
    );
  }
}
