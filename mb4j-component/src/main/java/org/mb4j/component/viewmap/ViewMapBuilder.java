package org.mb4j.component.viewmap;

import java.util.Collection;
import org.mb4j.component.url.BufferedUrlPathReader;
import org.mb4j.component.url.UrlPath;
import org.mb4j.component.url.UrlPathString;
import org.mb4j.component.view.View;

public class ViewMapBuilder {
  private static final UrlPath HOME_VIEW_PATH = UrlPath.empty();
  private static final UrlPath DEFAULT_HOME_VIEW_PATH = UrlPathString.urlPathOf("*");
  private final ViewMapNode root = ViewMapNode.createRoot();
  private final ViewMapClasses controllerClasses = new ViewMapClasses();

  private ViewMapBuilder() {
  }

  public static ViewMapBuilder withHomeController(View homeController) {
    return new ViewMapBuilder().mount(HOME_VIEW_PATH, homeController);
  }

  public static ViewMapBuilder withDefaultHomeController(View homeController) {
    return new ViewMapBuilder().mount(DEFAULT_HOME_VIEW_PATH, homeController);
  }

  public ViewMapBuilder mount(UrlPath path, View controller) {
    BufferedUrlPathReader pathReader = BufferedUrlPathReader.of(path);
    root.mount(pathReader, controller);
    controllerClasses.mount(pathReader.processedPath(), controller.getClass());
    return this;
  }

  MapUrlPath2Controller urlPath2Controller() {
    return root;
  }

  MapControllerClass2UrlPath controllerClass2UrlPath() {
    return controllerClasses;
  }

  void collectControllers(Collection<View> result) {
    root.collectControllers(result);
  }

  @Override
  public String toString() {
    return root.toString();
  }

  public String toString(String margin) {
    return root.toString(margin);
  }
}
