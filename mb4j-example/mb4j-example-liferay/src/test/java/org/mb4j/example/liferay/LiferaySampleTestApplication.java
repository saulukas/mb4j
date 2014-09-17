package org.mb4j.example.liferay;

import com.google.inject.Injector;
import org.mb4j.component.Request;
import org.mb4j.component.ResourceUrlResolver;
import org.mb4j.component.ViewUrl;
import org.mb4j.component.utils.AttributesMap;
import org.mb4j.component.viewmap.ViewMap;
import static org.mb4j.example.liferay.SampleSingleViewPortlet.singleViewMapFor;
import org.mb4j.liferay.PortletView;
import org.mb4j.liferay.PortletViewRequest;

public class LiferaySampleTestApplication {

    static Injector injector = LiferaySampleModule.injector();

    public static <T> T inject(Class<T> klass) {
        return injector.getInstance(klass);
    }

    public static Request singleViewRequestFor(ViewUrl viewUrl) {
        return requestFor(singleViewMapFor((Class<? extends PortletView>) viewUrl.viewClass), viewUrl);
    }

    public static Request requestFor(Class<? extends ViewMap> viewMapClass, ViewUrl viewUrl) {
        return requestFor(inject(viewMapClass), viewUrl);
    }

    public static Request requestFor(ViewMap viewMap, ViewUrl viewUrl) {
        String path2home = "../path2home/../";
        String path2assets = "../path2assets/../";
        String authToken = "12auth34";
        String namespace = "_namespace_";
        return PortletViewRequest.of(viewUrl,
                path2home,
                path2assets,
                new AttributesMap(),
                namespace,
                authToken,
                new ResourceUrlResolver(viewMap.componentNameResolver) {
                    @Override
                    protected String resolveResourceUrl(String resourceParamName, String resourceParamValue) {
                        return "./resourceUrl4Tests/?" + resourceParamName + "=" + resourceParamValue;
                    }
                },
                viewMap
        );
    }
}
