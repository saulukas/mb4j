package org.mb4j.component;

import org.mb4j.component.url.UrlParams;
import org.mb4j.component.url.UrlPath;
import org.mb4j.component.url.UrlPathBuilder;

public class ViewLocator {

    public final Class<? extends View> viewClass;
    public final UrlParams params;

    private ViewLocator(Class<? extends View> viewClass, UrlParams params) {
        this.viewClass = viewClass;
        this.params = params;
    }

    public static ViewLocator of(Class<? extends View> viewClass) {
        return of(viewClass, UrlParams.empty());
    }

    public static ViewLocator of(Class<? extends View> viewClass, UrlPath path) {
        return new ViewLocator(viewClass, UrlParams.of(path));
    }

    public static ViewLocator of(Class<? extends View> viewClass, UrlPathBuilder pathBuilder) {
        return ViewLocator.of(viewClass, pathBuilder.instance());
    }

    public static ViewLocator of(Class<? extends View> viewClass, UrlParams params) {
        return new ViewLocator(viewClass, params);
    }

    @Override
    public String toString() {
        return viewClass.getSimpleName() + "(" + params + ")";
    }

    public ViewLocator withReplacedParam(String name, String value) {
        return new ViewLocator(viewClass, params.withReplaced(name, value));
    }

    public ViewLocator withDeletedParam(String name) {
        return new ViewLocator(viewClass, params.withDeleted(name));
    }
}
