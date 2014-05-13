package org.mb4j.controller.sitemap;

import java.util.HashSet;
import java.util.Set;
import org.mb4j.controller.Controller;

public class SiteMap {
  private final SiteMapBuilder builder;
  private final FormMappings formMappings;
  private final ResourceMappings resourceMappings;

  public SiteMap(SiteMapBuilder builder) {
    this.builder = builder;
    Set<Controller> controllers = new HashSet<>();
    builder.collectControllers(controllers);
    this.formMappings = new FormMappings(controllers);
    this.resourceMappings = new ResourceMappings(controllers);
  }

  public MapUrlPath2Controller urlPath2Controller() {
    return builder.urlPath2Controller();
  }

  public MapControllerClass2UrlPath controllerClass2UrlPath() {
    return builder.controllerClass2UrlPath();
  }

  public MapFormClass2Name formClass2Name() {
    return formMappings;
  }

  public MapFormName2Form formName2Form() {
    return formMappings;
  }

  public MapComponentClass2Name componentWithResourcesClass2Name() {
    return resourceMappings;
  }

  @Override
  public String toString() {
    return builder.toString() + "\n\n" + formMappings + "\n" + resourceMappings;
  }
}
