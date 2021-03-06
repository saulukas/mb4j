package org.mb4j.component.viewmap;

import java.util.Collection;
import org.mb4j.component.View;
import org.mb4j.component.url.BufferedUrlPathReader;
import org.mb4j.component.url.UrlPath;
import org.mb4j.component.url.UrlPathString;
import static org.mb4j.component.url.UrlPathString.urlPathOf;

public class ViewMapBuilder {

    public static final UrlPath HOME_VIEW_PATH = UrlPath.empty();
    public static final UrlPath DEFAULT_HOME_VIEW_PATH = UrlPathString.urlPathOf("*");
    private final ViewMapNode root = ViewMapNode.createRoot();
    private final ViewMapClasses viewClasses = new ViewMapClasses();

    private ViewMapBuilder() {
    }

    public static ViewMapBuilder routerBuilder() {
        return new ViewMapBuilder();
    }

    public static ViewMapBuilder routeHomeTo(View homeView) {
        return routerBuilder().route(HOME_VIEW_PATH, homeView);
    }

    public static ViewMapBuilder routeDefaultHomeTo(View homeView) {
        return routerBuilder().route(DEFAULT_HOME_VIEW_PATH, homeView);
    }

    public ViewMapBuilder route(String pathString, View view) {
        return route(urlPathOf(pathString), view);
    }

    public ViewMapBuilder route(UrlPath path, View view) {
        BufferedUrlPathReader pathReader = BufferedUrlPathReader.of(path);
        root.mount(pathReader, view);
        viewClasses.mount(pathReader.processedPath(), view.getClass());
        return this;
    }

    MapUrlPath2View urlPath2View() {
        return root;
    }

    MapViewClass2UrlPath viewClass2UrlPath() {
        return viewClasses;
    }

    void collectViews(Collection<View> result) {
        root.collectViews(result);
    }

    @Override
    public String toString() {
        return root.toString();
    }

    public String toString(String margin) {
        return root.toString(margin);
    }
}
