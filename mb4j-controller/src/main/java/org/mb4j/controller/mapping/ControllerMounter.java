package org.mb4j.controller.mapping;

import java.util.Collection;
import org.mb4j.controller.Controller;
import org.mb4j.controller.url.BufferedUrlPathReader;
import static org.mb4j.controller.url.BufferedUrlPathReader.bufferedReaderOf;
import org.mb4j.controller.url.UrlPath;
import org.mb4j.controller.url.UrlPathString;

public class ControllerMounter {
  public static final UrlPath HOME_CONTROLLER_PATH = UrlPath.empty();
  public static final UrlPath DEFAULT_HOME_CONTROLLER_PATH = UrlPathString.urlPathOf("*");
  private final ControllerMounterNode rootNode = ControllerMounterNode.createRoot();
  private final ControllerPathMounter pathMounter = new ControllerPathMounter();

  private ControllerMounter() {
  }

  public static ControllerMounter withHomeController(Controller homeController) {
    return new ControllerMounter().mount(HOME_CONTROLLER_PATH, homeController);
  }

  public static ControllerMounter withDefaultHomeController(Controller homeController) {
    return new ControllerMounter().mount(DEFAULT_HOME_CONTROLLER_PATH, homeController);
  }

  public ControllerMounter mount(UrlPath path, Controller controller) {
    BufferedUrlPathReader pathReader = bufferedReaderOf(path);
    rootNode.mount(pathReader, controller);
    pathMounter.mount(pathReader.processedPath(), controller.getClass());
    return this;
  }

  MapUrlPath2Controller urlPath2Controller() {
    return rootNode;
  }

  MapControllerClass2UrlPath controllerClass2UrlPath() {
    return pathMounter;
  }

  public void collectControllers(Collection<Controller> result) {
    rootNode.collectControllers(result);
  }

  @Override
  public String toString() {
    return rootNode.toString();
  }

  public String toString(String margin) {
    return rootNode.toString(margin);
  }
}
