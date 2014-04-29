package org.mb4j.controller.mapping;

import java.util.HashSet;
import java.util.Set;
import org.mb4j.controller.Controller;

public class ControllerMappings {
  private final ControllerMounter mounter;
  private final FormMappings forms;

  public ControllerMappings(ControllerMounter mounter) {
    this.mounter = mounter;
    Set<Controller> controllers = new HashSet<>();
    mounter.collectControllers(controllers);
    this.forms = new FormMappings(controllers);
  }

  public UrlPath2ControllerResolver urlPath2ControllerResolver() {
    return mounter.urlPath2ControllerResolver();
  }

  public ControllerClass2UrlPathResolver controllerClass2UrlPathResolver() {
    return mounter.controllerClass2UrlPathResolver();
  }

  public FormClass2NameResolver formClass2NameResolver() {
    return forms;
  }

  public FormName2FormResolver formName2FormResolver() {
    return forms;
  }

  @Override
  public String toString() {
    return mounter.toString() + "\n\n" + forms;
  }
}
