package org.mb4j.example.liferay;

import org.mb4j.example.liferay.LiferaySampleModule;
import com.google.inject.Injector;
import org.mb4j.component.view.ViewRequest;
import org.mb4j.component.resource.Resources4ResponseResolver;
import org.mb4j.component.viewmap.SiteMap;
import org.mb4j.component.view.ViewUrl;
import org.mb4j.component.utils.AttributesMap;
import org.mb4j.liferay.PortletControllerRequest;

public class LiferaySampleTestApplication {
  static Injector injector = LiferaySampleModule.injector();

  public static <T> T inject(Class<T> klass) {
    return injector.getInstance(klass);
  }

  public static ViewRequest requestFor(Class<? extends SiteMap> siteMapClass, ViewUrl url) {
    String path2home = "../path2home/../";
    String path2assets = "../path2assets/../";
    String authToken = "12auth34";
    String namespace = "_namespace_";
    SiteMap siteMap = inject(siteMapClass);
    return PortletControllerRequest.of(
        url,
        path2home,
        path2assets,
        new AttributesMap(),
        namespace,
        authToken,
        new Resources4ResponseResolver(siteMap.componentWithResourcesClass2Name()) {
          @Override
          protected String resolveResourceUrl(String resourceParamName, String resourceParamValue) {
            return "./resourceUrl4Tests/?" + resourceParamName + "=" + resourceParamValue;
          }
        },
        siteMap
    );
  }
}
