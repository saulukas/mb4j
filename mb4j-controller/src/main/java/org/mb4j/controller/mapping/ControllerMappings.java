package org.mb4j.controller.mapping;

import java.util.HashSet;
import java.util.Set;
import org.mb4j.controller.Controller;

public class ControllerMappings {
  private final ControllerMounter mounter;
  private final FormMappings formMappings;

  public ControllerMappings(ControllerMounter mounter) {
    this.mounter = mounter;
    Set<Controller> controllers = new HashSet<>();
    mounter.collectControllers(controllers);
    this.formMappings = new FormMappings(controllers);
  }

  public MapUrlPath2Controller urlPath2Controller() {
    return mounter.urlPath2Controller();
  }

  public MapControllerClass2UrlPath controllerClass2UrlPath() {
    return mounter.controllerClass2UrlPath();
  }

  public MapFormClass2Name formClass2Name() {
    return formMappings;
  }

  public MapFormName2Form formName2Form() {
    return formMappings;
  }

  @Override
  public String toString() {
    return mounter.toString() + "\n\n" + formMappings;
  }
}
