package org.mb4j.controller.mount;

import org.mb4j.controller.View;
import org.mb4j.controller.path.BufferedViewPathReader;
import static org.mb4j.controller.path.BufferedViewPathReader.bufferedReaderOf;
import org.mb4j.controller.path.ViewPath;
import org.mb4j.controller.path.ViewPathString;

public class ViewMounter {
    public static final ViewPath HOME_VIEW_PATH = ViewPath.empty();
    public static final ViewPath DEFAULT_HOME_VIEW_PATH = ViewPathString.viewPath("*");
    private final ViewMounterNode rootNode = ViewMounterNode.createRoot();
    private final ViewPathMounter pathMounter = new ViewPathMounter();

    private ViewMounter() {
    }

    public static ViewMounter withHomeView(View homeView) {
        return new ViewMounter().mount(HOME_VIEW_PATH, homeView);
    }

    public static ViewMounter withDefaultHomeView(View homeView) {
        return new ViewMounter().mount(DEFAULT_HOME_VIEW_PATH, homeView);
    }

    public ViewMounter mount(ViewPath path, View view) {
        BufferedViewPathReader pathReader = bufferedReaderOf(path);
        rootNode.mount(pathReader, view);
        pathMounter.mount(pathReader.processedPath(), view.getClass());
        return this;
    }

    public ViewFromPathResolver viewFromPathResolver() {
        return rootNode;
    }

    public PathFromViewClassResolver pathFromViewClassResolver() {
        return pathMounter;
    }

    @Override
    public String toString() {
        return rootNode.toString();
    }

    public String toString(String margin) {
        return rootNode.toString(margin);
    }
}
