package org.mb4j.component;

import org.mb4j.component.url.UrlParams;
import org.mb4j.component.url.UrlPath;
import org.mb4j.component.url.UrlPathBuilder;

public class ViewUrl {

    public final Class<? extends View> viewClass;
    public final UrlParams params;

    private ViewUrl(Class<? extends View> viewClass, UrlParams params) {
        this.viewClass = viewClass;
        this.params = params;
    }

    public static ViewUrl of(Class<? extends View> viewClass) {
        return of(viewClass, UrlParams.empty());
    }

    public static ViewUrl of(Class<? extends View> viewClass, UrlPath path) {
        return new ViewUrl(viewClass, UrlParams.of(path));
    }

    public static ViewUrl of(Class<? extends View> viewClass, UrlPathBuilder pathBuilder) {
        return ViewUrl.of(viewClass, pathBuilder.instance());
    }

    public static ViewUrl of(Class<? extends View> viewClass, UrlParams params) {
        return new ViewUrl(viewClass, params);
    }

    @Override
    public String toString() {
        return viewClass.getSimpleName() + "(" + params + ")";
    }

    public ViewUrl withReplacedParam(String name, String value) {
        return new ViewUrl(viewClass, params.withReplaced(name, value));
    }

    public ViewUrl withDeletedParam(String name) {
        return new ViewUrl(viewClass, params.withDeleted(name));
    }
}
