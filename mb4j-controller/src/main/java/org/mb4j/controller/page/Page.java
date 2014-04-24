package org.mb4j.controller.page;

import org.mb4j.controller.Controller;
import org.mb4j.controller.ControllerRequest;

public abstract class Page extends Controller {
  @Override
  public abstract PageResponse handle(ControllerRequest request);
}
