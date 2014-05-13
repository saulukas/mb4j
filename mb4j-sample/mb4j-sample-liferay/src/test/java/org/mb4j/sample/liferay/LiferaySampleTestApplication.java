package org.mb4j.sample.liferay;

import com.google.inject.Injector;
import org.mb4j.controller.Request;
import org.mb4j.controller.sitemap.SiteMap;
import org.mb4j.controller.url.ControllerUrl;
import org.mb4j.controller.utils.AttributesMap;
import org.mb4j.liferay.PortletControllerRequest;

public class LiferaySampleTestApplication {
  static Injector injector = LiferaySampleModule.injector();

  public static <T> T inject(Class<T> klass) {
    return injector.getInstance(klass);
  }

  public static Request requestFor(Class<? extends SiteMap> siteMapClass, ControllerUrl url) {
    String path2home = "../path2home/../";
    String path2staticAssets = "../path2staticAssets/../";
    String authToken = "12auth34";
    String namespace = "_namespace_";
    SiteMap siteMap = inject(siteMapClass);
    return PortletControllerRequest.of(
        url,
        path2home,
        path2staticAssets,
        new AttributesMap(),
        namespace,
        authToken,
        siteMap
    );
  }
}
