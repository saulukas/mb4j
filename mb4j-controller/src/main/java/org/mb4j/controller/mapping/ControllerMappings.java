package org.mb4j.controller.mapping;

public class ControllerMappings {
  private final ControllerMounter mounter;
  private final FormActionMappings actions;

  public ControllerMappings(InstanceProviderByClass instanceProvider, ControllerMounter mounter) {
    this.mounter = mounter;
    this.actions = new FormActionMappings(mounter.getControllerClasses(), instanceProvider);
  }

  public UrlPath2ControllerResolver urlPath2ControllerResolver() {
    return mounter.urlPath2ControllerResolver();
  }

  public ControllerClass2UrlPathResolver controllerClass2UrlPathResolver() {
    return mounter.controllerClass2UrlPathResolver();
  }

  @Override
  public String toString() {
    return mounter.toString()
        + "\n" + actions;
  }
}
