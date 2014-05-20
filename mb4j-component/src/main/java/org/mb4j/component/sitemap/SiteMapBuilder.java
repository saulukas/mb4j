package org.mb4j.component.sitemap;

import java.util.Collection;
import org.mb4j.component.view.View;
import org.mb4j.component.url.BufferedUrlPathReader;
import org.mb4j.component.url.UrlPath;
import org.mb4j.component.url.UrlPathString;

public class SiteMapBuilder {
  private static final UrlPath HOME_CONTROLLER_PATH = UrlPath.empty();
  private static final UrlPath DEFAULT_HOME_CONTROLLER_PATH = UrlPathString.urlPathOf("*");
  private final SiteMapBuilderNode root = SiteMapBuilderNode.createRoot();
  private final SiteMapBuilderControllerClasses controllerClasses = new SiteMapBuilderControllerClasses();

  private SiteMapBuilder() {
  }

  public static SiteMapBuilder withHomeController(View homeController) {
    return new SiteMapBuilder().mount(HOME_CONTROLLER_PATH, homeController);
  }

  public static SiteMapBuilder withDefaultHomeController(View homeController) {
    return new SiteMapBuilder().mount(DEFAULT_HOME_CONTROLLER_PATH, homeController);
  }

  public SiteMapBuilder mount(UrlPath path, View controller) {
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
